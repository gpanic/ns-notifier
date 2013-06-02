package com.nileshop.notifier.ws.resources;

import com.nileshop.notifier.ws.entities.Test;
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
        Test t = new Test();
        t.setTest("aaaaa");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ns-notifier");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
        return "TEST";
    }
}
