package com.nileshop.notifier.ws.resources;

import com.nileshop.notifier.ws.entities.Category;
import com.nileshop.notifier.ws.entities.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class TestResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        Category c = new Category();
        c.setName("Kategorija A");
        Product p = new Product();
        p.setCategory(c);
        p.setDescription("description");
        p.setManufacturer("manufacturer");
        p.setName("name");
        p.setPrice(2.5);
        p.setImage("image");
        p.setWeight(29.30);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ns-notifier");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(c);
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        return "TEST";
    }
}
