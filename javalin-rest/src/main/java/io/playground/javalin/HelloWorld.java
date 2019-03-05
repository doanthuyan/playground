package io.playground.javalin;
import io.javalin.Javalin;
import io.playground.javalin.data.Point;
import io.playground.javalin.data.Repos;

import javax.persistence.*;
import java.util.*;
public class HelloWorld {
	public static void main(String[] args) {
		//System.setProperty("objectdb.home", "D:\\3.Learning\\playground\\db");
        Javalin app = Javalin.create()
        		.enableStaticFiles("/public")
        		.start(7000);
        app.get("/", ctx -> ctx.result("Hello World!"+playAroundObjectDb()));
        app.get("/repos", ctx -> ctx.result("Repositories" + playAroundRepos()));
    }
	
	private static String playAroundObjectDb() {
		String resp = "";
		// Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/javalin-rest.odb");
        EntityManager em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());
        resp= resp.concat("Total Points: " + q1.getSingleResult());
        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());
        resp= resp.concat("\nAverage X: " + q2.getSingleResult());
        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query =
            em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println(p);
            resp= resp.concat("\n" + p.toString());
        }

        // Close the database connection:
        em.close();
        emf.close();
        return resp;
	}
	private static String playAroundRepos() {
		String resp = "";
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("$objectdb/javalin-rest.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Repos r1 = new Repos("playground", "https://github.com/doanthuyan/playground");
        em.persist(r1);
        em.getTransaction().commit();
        TypedQuery<Repos> query = em.createQuery("Select r from Repos r", Repos.class);
        List<Repos> result = query.getResultList();
        for (Repos r:result) {
        	System.out.println(r);
        	resp = resp.concat("\n"+r.toString());
        }
        em.close();
        emf.close();
        return resp;
	}
}
