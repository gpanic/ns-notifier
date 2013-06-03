package com.nileshop.notifier.ws.resources;

import com.nileshop.notifier.ws.entities.Category;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("categories")
public class CategoryResource {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ns-notifier");
    
    @GET
    @Path("{id}")
    @Produces("application/json; charset=UTF-8")
    public Response read(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        Category c = em.find(Category.class, id);
        em.close();
        if (c != null) {
            return Response.ok(c).build();
        } else {
            return Response.status(404).build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Category> query = em.createQuery("SELECT e FROM Category e", Category.class);
        List<Category> c = query.getResultList();
        em.close();
        return Response.ok(c).build();

    }

}
