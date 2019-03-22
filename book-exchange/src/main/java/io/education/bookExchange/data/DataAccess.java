package io.education.bookExchange.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataAccess {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	private DataAccess() {}
	public static EntityManager getEntityManager() {
		if(emf == null) {
			emf =
	                Persistence.createEntityManagerFactory("$objectdb/book-exchange.odb");
		}
		if(em == null) {
			em = emf.createEntityManager();
		}
		return em;
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		em.close();
		emf.close();
		super.finalize();
		
	}
	
}
