package com.wissen.app;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wissen.app.entity.Course;
import com.wissen.app.util.HibernateUtil;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;

@SpringBootApplication
public class SpringBootHibernateApplication {
	private static final String LOCK_TIMEOUT_PROPERTY = "javax.persistence.lock.timeout";
	private static final String LOCK_WAIT_TIME = "0";
	private static final String COURSE_ID_FIELD = "courseId";

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHibernateApplication.class, args);
	}

	@PostConstruct
	private void postInit() {
		Session session1 = getNewSession();
		Session session2 = getNewSession();
		Session session3 = getNewSession();

		setLockTimeoutToZero(session1);
		setLockTimeoutToZero(session2);
		setLockTimeoutToZero(session3);

		Transaction tx = session1.beginTransaction();
		Query query = createLocalQuery(session1);
		query.setLockMode(LockModeType.PESSIMISTIC_READ);
		System.out.println("Result list 1: " + query.getResultList());

		Transaction tx2 = session2.beginTransaction();
		Query query2 = createLocalQuery(session2);
		query2.setLockMode(LockModeType.PESSIMISTIC_READ);
		System.out.println("Result list 2: " + query2.getResultList());

		Transaction tx3 = session3.beginTransaction();
		Query query3 = session3.createQuery("from Course where id = :courseId", Course.class);
		query3.setParameter(COURSE_ID_FIELD, 2);
		query3.setLockMode(LockModeType.PESSIMISTIC_WRITE);
		System.out.println("Result list 3: " + query3.getResultList());

		tx.commit();
		tx2.commit();
		tx3.commit();

		session1.close();
		session2.close();
		session3.close();
	}

	private Session getNewSession() {
		return HibernateUtil.getSession();
	}

	private Session setLockTimeoutToZero(Session session) {
		session.setProperty(LOCK_TIMEOUT_PROPERTY, LOCK_WAIT_TIME);
		return session;
	}

	private Query createLocalQuery(Session session) {
		Query query = session.createQuery("from Course where id = :courseId", Course.class);
		query.setParameter(COURSE_ID_FIELD, 1);
		return query;
	}

}
