package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory =new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		//create session
		Session session= factory.getCurrentSession();
		
		try {
			//use session object to save the Java object
		
			//create 3 student object
			System.out.println("Creating new student object...");
			Student theStudent1=new Student("John","Doe","John@Doe.com");
			Student theStudent2=new Student("Mary","Public","Mary@Public.com");
			Student theStudent3=new Student("Bonita","Appelbaum","Bonita@Appelbaum.com");
			
			// start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student");
			session.save(theStudent1);
			session.save(theStudent2);
			session.save(theStudent3);
			
			// commit transaction
			 session.getTransaction().commit();
			 
			 System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
