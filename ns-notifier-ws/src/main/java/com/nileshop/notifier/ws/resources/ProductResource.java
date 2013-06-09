package com.nileshop.notifier.ws.resources;

import com.nileshop.notifier.ws.entities.Category;
import com.nileshop.notifier.ws.entities.Product;
import java.util.Date;
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

@Path("products")
public class ProductResource {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ns-notifier");
    
    @GET
    @Path("{id}")
    @Produces("application/json; charset=UTF-8")
    public Response read(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        Product p = em.find(Product.class, id);
        em.close();
        if (p != null) {
            return Response.ok(p).build();
        } else {
            return Response.status(404).build();
        }
    }
    
    @GET
    @Produces("application/json; charset=UTF-8")
    public Response list() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Product> query = em.createQuery("SELECT e FROM Product e", Product.class);
        List<Product> c = query.getResultList();
        em.close();
        return Response.ok(c).build();
    }
    
    @GET
    @Path("/new/{timestamp}")
    @Produces("application/json; charset=UTF-8")
    public Response listNew(@PathParam("timestamp") Long timestamp) {
        Date d = new Date(timestamp);
        EntityManager em = emf.createEntityManager();
        TypedQuery<Product> query = em.createQuery("SELECT e FROM Product e WHERE e.added > :timestamp", Product.class);
        query.setParameter("timestamp", d);
        List<Product> c = query.getResultList();
        em.close();
        return Response.ok(c).build();
    }
    
    @GET
    @Path("insert_demo")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertDemo() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Category c = em.find(Category.class, 101);
        Product p = new Product("2,5\" SSD disk Samsung 840 TLC Basic 120 GB, SATA III (MZ-7TD120BW)",
                94.58,
                "2,5-palčni SSD disk Samsung kapacitete 120 GB bo navdušil vse, ki prisegajo na visoke hitrosti prenosa podatkov. Poleg tega se disk lahko pohvali tudi z izjemno tanko zasnovo, zelo dolgo življenjsko dobo in majhno porabo električne energije.",
                0.3,
                "Samsung",
                c,
                "http://img11.mimovrste.com/98/27/379827_0.jpg",
                new Date()
                );
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        return Response.ok("Product added").build();
    }
    
    @GET
    @Path("insert_category_demo")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertDemoCategory() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Category c = new Category();
        c.setName("Racunalništvo");
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        return Response.ok("Category added").build();
    }

}
