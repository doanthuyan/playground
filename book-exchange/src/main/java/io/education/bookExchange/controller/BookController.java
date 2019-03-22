package io.education.bookExchange.controller;


import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.education.bookExchange.data.Book;
import io.education.bookExchange.data.BookDao;
import io.education.bookExchange.data.DataAccess;
import io.education.bookExchange.util.DataUtil;
import io.javalin.Handler;

public class BookController {
	private static BookController bookController;
	private BookDao bookDao;
	private BookController() {
		bookDao = new BookDao();
	}
	public static BookController getInstance() {
		if(bookController ==null) bookController = new BookController();
		return bookController;
	}
	@Deprecated
	public void saveBook(Book b) {
		EntityManager em = DataAccess.getEntityManager();
		em.getTransaction().begin();
		em.persist(b);
		em.getTransaction().commit();
	}
	private List<Book> getAllBooks(){
		return bookDao.getAllBooks();
	}
	private boolean addBook(Book b) {
		return bookDao.addBook(b);
	}
	public static Handler fetchAllBooks = ctx -> {
        
        ObjectMapper mapper = new ObjectMapper();
        ctx.result(mapper.writeValueAsString( BookController.getInstance().getAllBooks()));
    };
    public static Handler addBook = ctx -> {
        //Book(String author, String country, String imageLink, String language, String link, String title, int pages,int year, int amountInStock) 
    	
        ObjectMapper mapper = new ObjectMapper();
        
        ctx.formParamMap().forEach((k,v)->System.out.println("param : " + k + " val : " + v));
        Book book = mapper.convertValue(DataUtil.getInstance().standardizeMapForJackson(ctx.formParamMap()), Book.class);
        ctx.result(mapper.writeValueAsString( BookController.getInstance().addBook(book)));
    };
}
