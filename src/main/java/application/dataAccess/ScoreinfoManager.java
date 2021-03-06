package application.dataAccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Order;

import application.entities.Scoreinfo;

public class ScoreinfoManager {
	protected SessionFactory sessionFactory;
	 
    public void setup() {
        // code to load Hibernate Session factory
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    	        .configure() // configures settings from hibernate.cfg.xml
    	        .build();
    	try {
    	    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    	} catch (Exception ex) {
    	    StandardServiceRegistryBuilder.destroy(registry);
    	}
    }
 
    public void exit() {
        // code to close Hibernate Session factory
    	sessionFactory.close();
    	
    }
 
    public void create(String user_name, int score) {  	
        // code to save scoreinfo
    	Scoreinfo scoreinfo = new Scoreinfo();
        scoreinfo.setUser_name(user_name);
        scoreinfo.setScore(score);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.save(scoreinfo);
     
        session.getTransaction().commit();
        session.close();
    }
    
 // code to get the top 10 best scoreinfo
    
    @SuppressWarnings("unchecked")
    public List<Scoreinfo> getAll() {
    	Session session = sessionFactory.openSession();  // Open Hibernate Session 	
    	List<Scoreinfo> myList = new ArrayList<Scoreinfo>(session.createCriteria(Scoreinfo.class) // Create list with all scoreinfos
    			.addOrder(Order.asc("score")) // Set ascending order based on scores
    			.setMaxResults(10) // Order max 10 scoreinfos
    			.list());  // array to list
    	session.close();
    	
    	return myList;
    }
    
    public void read(int id) {
        // code to get one scoreinfo
    	Session session = sessionFactory.openSession();
   	 
        Scoreinfo scoreinfo = session.get(Scoreinfo.class, id);
     
        System.out.println("Nimimerkki: " + scoreinfo.getUser_name());
        System.out.println("Tulos: " + scoreinfo.getScore());

     
        session.close();
    }
 
    public void update(int id, String user_name, int score) {
        // code to modify a scoreinfo
    	Scoreinfo scoreinfo = new Scoreinfo();
        scoreinfo.setId(id);
        scoreinfo.setUser_name(user_name);
        scoreinfo.setScore(score);

     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.update(scoreinfo);
     
        session.getTransaction().commit();
        session.close();
    }
 
    public void delete(int id) {
        // code to remove a scoreinfo
        Scoreinfo scoreinfo = new Scoreinfo();
        scoreinfo.setId(id);
     
        Session session = sessionFactory.openSession();
        session.beginTransaction();
     
        session.delete(scoreinfo);
     
        session.getTransaction().commit();
        session.close();
    }

    /* For simple testing of the DB connection
    
	public static void main(String[] args) {
    	ScoreinfoManager manager = new ScoreinfoManager();
        manager.setup();
        manager.create();
        manager.read();
        manager.delete();
        manager.exit();

	}*/

}
