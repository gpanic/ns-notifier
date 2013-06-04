package com.nileshop.notifier.ws;

import com.nileshop.notifier.ws.entities.Category;
import com.nileshop.notifier.ws.entities.Product;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBHelper {
    
    private static List<Category> categories;
    private static List<Product> products;
    
    public static void main(String [] args) {
        categories = categories();
        products = products();
        seed();
    }

    public static void seed() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ns-notifier");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (Category c : categories) {
            em.persist(c);
        }
        for (Product p : products) {
            em.persist(p);
        }
        em.getTransaction().commit();
        em.close();
    }
    
    private static List<Category> categories() {
        ArrayList<Category> c = new ArrayList<Category>();
        c.add(new Category("Računalništvo"));
        c.add(new Category("Audio-video"));
        c.add(new Category("Knjige"));
        c.add(new Category("Telefonija"));
        return c;
    }
    
    private static List<Product> products() {
        if (categories == null) {
            return null;
        }
        ArrayList<Product> p = new ArrayList<Product>();
        p.add(new Product("Prenosnik Lenovo IdeaPad B590 2,5 GHz (59-369898)",
                426.00,
                "IdeaPad B590 res izstopa, ko govorimo o prenosnikih za vsakdanjo rabo. To potrjuje 15,6\" - 39,6 cm HD LED zaslon, 4 GB pomnilnika DDR3 in 500 GB trdi disk, ki zagotavlja, da boste imeli dovolj prostora na za shranjevanje podatkov.",
                2.4,
                "Lenovo",
                categories.get(0),
                "http://img22.mimovrste.com/23/36/422336_0.jpg",
                new Date()
                ));
        p.add(new Product("Monitor LED 24\" Dell UltraSharp U2412M",
                259.00,
                "24-palčni LED IPS zaslon, primeren za pisarniške uporabnike. Enkratna slika, ločljivost 1920 x 1200 točk, velikost pike 0,277 mm. Monitor je ekološko orientiran. K izredni prilagodljivosti pripomore po višini nastavljivo stojalo, zaslon pa lahko tudi nagibate ali obračate.",
                4.2,
                "Dell",
                categories.get(0),
                "http://img11.mimovrste.com/38/99/243899_0.jpg",
                new Date()
                ));
        p.add(new Product("LED LCD TV sprejemnik Samsung 46F6100 (3D)",
                789.00,
                "Samsung 46F6100 je kakovosten LED LCD TV sprejemnik z velikostjo zaslona 116 cm (46\"). Sprejemnik odlikujejo pametne in napredne funkcije, tehnologija Wide Color Enhancer Plus, FULL HD 1080p ločljivost, 3D pretvornik, HDMI in CI razširitvena reža, vgrajen USB predvajalnik",
                2.4,
                "Samsung",
                categories.get(1),
                "http://img22.mimovrste.com/5/16/420516_0.jpg",
                new Date()
                ));
        p.add(new Product("A Dance With Dragons: Part 1 Dreams and Dust (A Song of Ice and Fire, Book 5)",
                5.67,
                "Pesem ledu in ognja (v izvirniku angleško A Song of Ice and Fire) je zbirka epskih fantazijskih romanov, ki jo je napisal ameriški pisatelj in scenarist George R. R. Martin.",
                1.1,
                "Harper Voyager",
                categories.get(2),
                "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRhcu-PP2UYgOKElfyXSPktASQgQ6xKAwCkiq6SFwgZQFCMTu-lHA",
                new Date()
                ));
        p.add(new Product("Samsung Galaxy S III 16GB",
                690,
                "Pametni telefon Samsung Galaxy ACE, svetovno priznanega proizvajalca Samsung, se ponaša z 8,9-cm zaslon na dotik, ki premore ločljivost 320 x 480 točk. Vgrajen je tudi fotoaparat s 5 MP, samodejnim ostrenjem in  snemanjem videoposnetkov.",
                0.3,
                "Samsung",
                categories.get(3),
                "http://img11.mimovrste.com/16/97/311697_0.jpg",
                new Date()
                ));
        p.add(new Product("GSM telefon Samsung Galaxy S4, Black Mist",
                688.49,
                "Mobilni telefon Samsung Galaxy S4 s 5-palčnim Full HD Super AMOLED zaslonom, kamero s 13 milijoni slikovnih točk s simultanim dvojnim zajemanjem slike, Easy Mode načinom za enostavno uporabo ter Air Gesture in Air View podporo, ki omogočata, da uporabljate vaš zaslon na dotik, brez dotikov.",
                0.3,
                "Samsung",
                categories.get(3),
                "http://img22.mimovrste.com/62/6/426206_0.jpg",
                new Date()
                ));
        p.add(new Product("Procesor Intel Core i7 3770K 3,5 GHz Box, 1155 (BX80637I73770KSR0PL)",
                342.39,
                "Štirijedrni Intel Core i7 3770K prihaja iz nove generacije procesorjev, ki temeljijo na Ivy Bridge tehnologiji. Tiktaka pri frekvenci 3,5 GHz, ki bo poskrbela za brezhibno odzivnost vašega računalnika, tudi ko bo na vašem namizju teklo več zahtevnih aplikacij.",
                0.8,
                "Intel",
                categories.get(0),
                "http://img11.mimovrste.com/95/49/319549_0.jpg",
                new Date()
                ));
        p.add(new Product("2,5\" SSD disk Samsung 840 TLC Basic 120 GB, SATA III (MZ-7TD120BW)",
                94.58,
                "2,5-palčni SSD disk Samsung kapacitete 120 GB bo navdušil vse, ki prisegajo na visoke hitrosti prenosa podatkov. Poleg tega se disk lahko pohvali tudi z izjemno tanko zasnovo, zelo dolgo življenjsko dobo in majhno porabo električne energije.",
                0.3,
                "Samsung",
                categories.get(0),
                "http://img11.mimovrste.com/98/27/379827_0.jpg",
                new Date()
                ));
        p.add(new Product("Slušalke Logitech G430 (981-000537)",
                74.02,
                "Še ene izjemne slušalke proizvajalca Logitech odlikuje jasen in čist 7.1 prostorski zvok, USB povezava, mikrofon s sistemom odpravljanja šumov, nepozabljivo udobje, možnost 90 ° obračanja blaznic, na kablu nameščena kontrolna ploščica za nadzor glasnosti in mikrofona ter še mnogo več.",
                1.2,
                "Logitech",
                categories.get(1),
                "http://img22.mimovrste.com/89/32/428932_0.jpg",
                new Date()
                ));
        return p;
    }

}
