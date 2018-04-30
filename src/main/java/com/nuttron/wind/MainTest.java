package com.nuttron.wind;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuttron.wind.dao.entity.Breed;
import com.nuttron.wind.dao.entity.Dog;

public class MainTest {

	public static void main(String[] args) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		// accessing JBoss's Transaction can be done differently but this one works
		// nicely
		TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

		// build the EntityManagerFactory as you would build in in Hibernate ORM
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ogm-jpa-tutorial");

		final Logger logger = LoggerFactory.getLogger(MainTest.class);

		// Persist entities the way you are used to in plain JPA
		tm.begin();
		logger.info("About to store dog and breed");
		EntityManager em = emf.createEntityManager();
		Breed collie = new Breed();
		collie.setName("Collie");
		//em.persist(collie);
		Dog dina = new Dog();
		dina.setName("Dina");
		
		collie.getDogs().add(dina);
		em.persist(collie);
		Long dinaId = dina.getId();
		em.flush();
		em.close();
		tm.commit();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+dinaId);
		// Retrieve your entities the way you are used to in plain JPA
		tm.begin();
		logger.info("About to retrieve dog and breed");
		em = emf.createEntityManager();
		dina = em.find(Dog.class, dinaId);
		logger.info("Found dog %s of breed %s", dina.getName(),collie.getDogs().iterator().next().getName());
		em.flush();
		em.close();
		tm.commit();

		emf.close();
	}
}
