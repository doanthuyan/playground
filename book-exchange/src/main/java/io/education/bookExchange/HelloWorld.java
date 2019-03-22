package io.education.bookExchange;

import static io.javalin.apibuilder.ApiBuilder.get;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.education.bookExchange.controller.BookController;
import io.education.bookExchange.data.Book;
import io.education.bookExchange.data.DataAccess;
import io.javalin.Javalin;
import io.javalin.core.util.FileUtil;
import io.javalin.staticfiles.Location;



public class HelloWorld {
	public static void main(String[] args) {
		System.setProperty("objectdb.home", "D:\\3.Learning\\playground\\db");
        Javalin app = Javalin.create()
        		.enableStaticFiles("/public")
        		.enableStaticFiles("uploads", Location.EXTERNAL)
        		.start(7000);
        //addBooks();
        app.get("/", ctx -> ctx.result("Hello World!" + getBooks()));
        app.get("/api/books", BookController.fetchAllBooks);
        app.post("/api/book/add", BookController.addBook);
        //app.post("/upload") { ctx ->
        //ctx.uploadedFiles("files").forEach { (contentType, content, name, extension) ->
        //    FileUtil.streamToFile(content, "upload/$name")
        //}
        app.post("/api/book/upload/cover", ctx -> {
             
                FileUtil.streamToFile(ctx.uploadedFile("file").getContent(), "uploads/" + ctx.uploadedFile("file").getName());
            
        });
    }
        
	
	private static String getBooks() {
		String resp = "";
        
        EntityManager em = DataAccess.getEntityManager();
        
        TypedQuery<Book> query = em.createQuery("Select b from Book b", Book.class);
        List<Book> result = query.getResultList();
        for (Book r:result) {
        	System.out.println(r);
        	resp = resp.concat("\n"+r.toString());
        }
        
        return resp;
	}
	private static void addBooks() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Book> myObjects = mapper.readValue(new File("D:/3.Learning/playground/book-exchange/data/books.json"), new TypeReference<List<Book>>(){});
			for(Book b:myObjects) {
				System.out.println(b.toString());
				BookController.getInstance().saveBook(b);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
