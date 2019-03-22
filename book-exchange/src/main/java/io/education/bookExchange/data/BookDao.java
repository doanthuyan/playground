package io.education.bookExchange.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class BookDao {
	public List<Book> getAllBooks() {
		EntityManager em = DataAccess.getEntityManager();
        
        TypedQuery<Book> query = em.createQuery("Select b from Book b", Book.class);
        List<Book> result = query.getResultList();
        return result;
    }
	public boolean addBook(Book b) {
		boolean ok = true;
		
		List<Book> list = getAllBooks();
		for(Book book:list) {
			if(book.compareTo(b) == 0) {
				ok = false;
				break;
			}
		}
		if(ok) {
			EntityManager em = DataAccess.getEntityManager();
			em.getTransaction().begin();
			em.persist(b);
			em.getTransaction().commit();
		}
		return ok;
	}
}
