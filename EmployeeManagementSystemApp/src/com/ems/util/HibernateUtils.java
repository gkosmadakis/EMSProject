package com.ems.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.ems.dao.BaseDaoImpl;

public class HibernateUtils {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static final Logger logger = Logger.getLogger(HibernateUtils.class);

	// Hibernate 5:
	private static SessionFactory buildSessionFactory() {

		try {

			// Create the ServiceRegistry from hibernate.cfg.xml
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
					.configure("hibernate.cfg.xml").build();

			// Create a metadata sources using the specified service registry.
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build(); 

			return metadata.getSessionFactoryBuilder().build();

		} catch (Throwable ex) {         

			logger.error("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}

	}


	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	public static Session getSession() {

		Session session = null;

		if (sessionFactory != null) {

			session = sessionFactory.openSession();

		}

		return session;
	}
	
	
}
