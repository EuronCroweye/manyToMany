package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
		
			//create a student object
			System.out.println("Creating new student object...");
			Student theStudent=new Student("Daffy","Duck","Duf@Du.com");
			
			// start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student");
			System.out.println(theStudent);
			session.save(theStudent);
			
			// commit transaction
			 session.getTransaction().commit();
			 
			 // MY NEW CODE
			 
			 // find out the student's id primary key
			 System.out.println("Saved student. Generated id: "+ theStudent.getId());
			 
			 // now get the new session and start the transaction
			 session = factory.getCurrentSession();
			 session.beginTransaction();
			 
			 // retrieve student based on the id: primary key
			 System.out.println("\nGetting student with id: "+ theStudent.getId());
			 
			 Student myStudent=session.get(Student.class, theStudent.getId());
			 
			 System.out.println("Get complete: "+ myStudent);
			 
			 // commit the transaction
			 session.getTransaction().commit();
			 
			 System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
