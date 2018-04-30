package com.nuttron.wind;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import com.nuttron.wind.util.PojoGenerator;

import javassist.CannotCompileException;
import javassist.NotFoundException;

public class OGMonTheFly {
	public static void main(String[] args) throws NotSupportedException, SystemException, SecurityException,
			IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException {
		// accessing JBoss's Transaction can be done differently but this one works
		// nicely
		TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

		// build the EntityManagerFactory as you would build in in Hibernate ORM
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ogm-jpa-tutorial");

		final Logger logger = LoggerFactory.getLogger(MainTest.class);
		
		Map<String, Class<?>> props = new HashMap<String, Class<?>>();
		props.put("id", Integer.class);
		props.put("value", String.class);

		Class<?> clazz = PojoGenerator.generate("com.nuttron.wind.OGMEntity", props);
		Object obj = clazz.newInstance();

		// Persist entities the way you are used to in plain JPA
		tm.begin();
		logger.info("About to store dog and breed");
		EntityManager em = emf.createEntityManager();
		em.persist(obj);
		em.flush();
		em.close();
		tm.commit();

		emf.close();
	}
}
