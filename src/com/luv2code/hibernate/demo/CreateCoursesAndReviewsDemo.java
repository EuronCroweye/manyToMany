package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;

public class CreateCoursesAndReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

	//		Course tempCourse=session.get(Course.class, 15);
			
			// create a course
			Course tempCourse= new Course("Pacman - How to score One Milion Points");
		//	session.delete(tempCourse);
			//add some reviews
			tempCourse.addReview(new Review("Great course... loved it!"));
			tempCourse.addReview(new Review("Yea this was what I was looking for!"));
			tempCourse.addReview(new Review("What a bumb course"));
			
			// save the course ... and leverage the cascade all :-)
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			
			session.save(tempCourse);
			
			
			
			
			
			
			
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {

			// add clean up code
			session.close();

			factory.close();
		}
	}

}
