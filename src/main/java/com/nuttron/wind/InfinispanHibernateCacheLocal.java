package com.nuttron.wind;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;

import com.nuttron.wind.dao.entity.Breed;
import com.nuttron.wind.dao.entity.Dog;
 
/**
 * Example on how to use Infinispan as Hibernate cache provider
 * in a standalone, single-node, environment.
 *
 * This example assumes that the JPA persistence configuration file
 * is present in the default lookup location: META-INF/persistence.xml
 *
 * Run with these properties to hide Hibernate messages:
 * -Dlog4j.configurationFile=src/main/resources/log4j2-tutorial.xml
 */
public class InfinispanHibernateCacheLocal {
 
   private static EntityManagerFactory emf;
 
   public static void main(String[] args) throws Exception {
      // Create JPA persistence manager
      emf = Persistence.createEntityManagerFactory("events");
 
      SecondLevelCacheStatistics DogCacheStats;
      SecondLevelCacheStatistics BreedCacheStats;
      Statistics stats;
 
      // Persist 3 entities, stats should show 3 second level cache puts
      persistEntities();
      DogCacheStats = getCacheStatistics(Dog.class.getName());
      printfAssert("Dog entity cache puts: %d (expected %d)%n", DogCacheStats.getPutCount(), 3);
 
      // Find one of the persisted entities, stats should show a cache hit
      findEntity(1L);
      DogCacheStats = getCacheStatistics(Dog.class.getName());
      printfAssert("Dog entity cache hits: %d (expected %d)%n", DogCacheStats.getHitCount(), 1);
 
      // Update one of the persisted entities, stats should show a cache hit and a cache put
      updateEntity(1L);
      DogCacheStats = getCacheStatistics(Dog.class.getName());
      printfAssert("Dog entity cache hits: %d (expected %d)%n", DogCacheStats.getHitCount(), 1);
      printfAssert("Dog entity cache puts: %d (expected %d)%n", DogCacheStats.getPutCount(), 1);
 
      // Find the updated entity, stats should show a cache hit
      findEntity(1L);
      DogCacheStats = getCacheStatistics(Dog.class.getName());
      printfAssert("Dog entity cache hits: %d (expected %d)%n", DogCacheStats.getHitCount(), 1);
 
 
      // Evict entity from cache
      evictEntity(1L);
 
      // Reload evicted entity, should come from DB
      // Stats should show a cache miss and a cache put
      findEntity(1L);
      DogCacheStats = getCacheStatistics(Dog.class.getName());
      printfAssert("Dog entity cache miss: %d (expected %d)%n", DogCacheStats.getMissCount(), 1);
      printfAssert("Dog entity cache puts: %d (expected %d)%n", DogCacheStats.getPutCount(), 1);
 
      // Remove cached entity, stats should show a cache hit
      deleteEntity(1L);
      DogCacheStats = getCacheStatistics(Dog.class.getName());
      printfAssert("Dog entity cache hits: %d (expected %d)%n", DogCacheStats.getHitCount(), 1);
 
      // Add a fictional delay so that there's a small enough gap for the
      // query result set timestamp to be later in time than the update timestamp.
      // Deleting an entity triggers an invalidation Dog which updates the timestamp.
      Thread.sleep(100);
 
      // Query entities, expect:
      // * no cache hits since query is not cached
      // * a query cache miss and query cache put
      queryEntities();
      stats = getStatistics();
      printfAssert("Query cache miss: %d (expected %d)%n", stats.getQueryCacheMissCount(), 1);
      printfAssert("Query cache put: %d (expected %d)%n", stats.getQueryCachePutCount(), 1);
 
      // Repeat query, expect:
      // * two cache hits for the number of entities in cache
      // * a query cache hit
      queryEntities();
      stats = getStatistics();
      printfAssert("Dog entity cache hits: %d (expected %d)%n", stats.getSecondLevelCacheHitCount(), 2);
      printfAssert("Query cache hit: %d (expected %d)%n", stats.getQueryCacheHitCount(), 1);
 
      // Update one of the persisted entities, stats should show a cache hit and a cache put
      updateEntity(2L);
      DogCacheStats = getCacheStatistics(Dog.class.getName());
      printfAssert("Dog entity cache hits: %d (expected %d)%n", DogCacheStats.getHitCount(), 1);
      printfAssert("Dog entity cache puts: %d (expected %d)%n", DogCacheStats.getPutCount(), 1);
 
      // Repeat query after update, expect:
      // * no cache hits or puts since entities are already cached
      // * a query cache miss and query cache put, because when an entity is updated,
      //   any queries for that type are invalidated
      queryEntities();
      stats = getStatistics();
      printfAssert("Query cache miss: %d (expected %d)%n", stats.getQueryCacheMissCount(),1);
      printfAssert("Query cache put: %d (expected %d)%n", stats.getQueryCachePutCount(), 1);
 
 
      // Save cache-expiring entity, stats should show a second level cache put
      saveExpiringEntity();
      BreedCacheStats = getCacheStatistics(Breed.class.getName());
      printfAssert("Breed entity cache puts: %d (expected %d)%n", BreedCacheStats.getPutCount(), 1);
 
      // Find expiring entity, stats should show a second level cache hit
      findExpiringEntity(4L);
      BreedCacheStats = getCacheStatistics(Breed.class.getName());
      printfAssert("Breed entity cache hits: %d (expected %d)%n", BreedCacheStats.getHitCount(), 1);
 
      // Wait long enough for entity to be expired from cache
      Thread.sleep(1100);
 
      // Find expiring entity, after expiration entity should come from DB
      // Stats should show a cache miss and a cache put
      findExpiringEntity(4L);
      BreedCacheStats = getCacheStatistics(Breed.class.getName());
      printfAssert("Breed entity cache miss: %d (expected %d)%n", BreedCacheStats.getMissCount(), 1);
      printfAssert("Breed entity cache put: %d (expected %d)%n", BreedCacheStats.getPutCount(), 1);
 
      // Close persistence manager
      emf.close();
   }
 
   private static void persistEntities() {
      try (Session em = createEntityManagerWithStatsCleared()) {
         EntityTransaction tx = em.getTransaction();
         try {
            tx.begin();
 
            Dog d1 = new Dog();
            d1.setName("tiger1");
            
            Dog d2 = new Dog();
            d2.setName("tiger2");
            
            Dog d3 = new Dog();
            d3.setName("tiger3");
            
            em.persist(d1);
            em.persist(d2);
            em.persist(d3);
         } catch (Throwable t) {
            tx.setRollbackOnly();
            throw t;
         } finally {
            if (tx.isActive()) tx.commit();
            else tx.rollback();
         }
      }
   }
 
   private static Session createEntityManagerWithStatsCleared() {
      EntityManager em = emf.createEntityManager();
      emf.unwrap(SessionFactory.class).getStatistics().clear();
      // JPA's EntityManager is not AutoCloseable,
      // but Hibernate's Session is,
      // so unwrap it to make it easier to close after use.
      return em.unwrap(Session.class);
   }
 
   private static void findEntity(long id) {
      try (Session em = createEntityManagerWithStatsCleared()) {
         Dog Dog = em.find(Dog.class, id);
         System.out.printf("Found entity: %s%n", Dog);
      }
   }
 
   private static void updateEntity(long id) {
      try (Session em = createEntityManagerWithStatsCleared()) {
         EntityTransaction tx = em.getTransaction();
         try {
            tx.begin();
 
            Dog Dog = em.find(Dog.class, id);
            Dog.setName("Caught a Snorlax!!");
 
            System.out.printf("Updated entity: %s%n", Dog);
         } catch (Throwable t) {
            tx.setRollbackOnly();
            throw t;
         } finally {
            if (tx.isActive()) tx.commit();
            else tx.rollback();
         }
      }
   }
 
   private static void evictEntity(long id) {
      try (Session em = createEntityManagerWithStatsCleared()) {
         em.getEntityManagerFactory().getCache().evict(Dog.class, id);
      }
   }
 
   private static void deleteEntity(long id) {
      try (Session em = createEntityManagerWithStatsCleared()) {
         EntityTransaction tx = em.getTransaction();
         try {
            tx.begin();
 
            Dog Dog = em.find(Dog.class, id);
            em.remove(Dog);
         } catch (Throwable t) {
            tx.setRollbackOnly();
            throw t;
         } finally {
            if (tx.isActive()) tx.commit();
            else tx.rollback();
         }
      }
   }
 
   private static void queryEntities() {
      try (Session em = createEntityManagerWithStatsCleared()) {
         TypedQuery<Dog> query = em.createQuery("from Dog", Dog.class);
         query.setHint("org.hibernate.cacheable", Boolean.TRUE);
         List<Dog> Dogs = query.getResultList();
         System.out.printf("Queried Dogs: %s%n", Dogs);
      }
   }
 
   private static void saveExpiringEntity() {
      try (Session em = createEntityManagerWithStatsCleared()) {
         EntityTransaction tx = em.getTransaction();
         try {
            tx.begin();
 
            Breed b = new Breed();
            b.setName("Labrador");
            em.persist(b);
         } catch (Throwable t) {
            tx.setRollbackOnly();
            throw t;
         } finally {
            if (tx.isActive()) tx.commit();
            else tx.rollback();
         }
      }
   }
 
   private static void findExpiringEntity(long id) {
      try (Session em = createEntityManagerWithStatsCleared()) {
         Breed Breed = em.find(Breed.class, id);
         System.out.printf("Found expiring entity: %s%n", Breed);
      }
   }
 
   private static SecondLevelCacheStatistics getCacheStatistics(String regionName) {
      return emf.unwrap(SessionFactory.class).getStatistics()
            .getSecondLevelCacheStatistics(regionName);
   }
 
   private static Statistics getStatistics() {
      return emf.unwrap(SessionFactory.class).getStatistics();
   }
 
   private static void printfAssert(String format, long actual, long expected) {
      System.out.printf(format, actual, expected);
      if (expected != actual) {
         emf.close();
         throw new AssertionError("Expected: " + expected + ", actual: " + actual);
      }
   }
 
}
 