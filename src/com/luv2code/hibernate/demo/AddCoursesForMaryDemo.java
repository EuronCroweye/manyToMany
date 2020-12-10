package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForMaryDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();
			
			// get the student mary from database
			int studentId=3;
			Student tempStudent = session.get(Student.class, studentId);
			
			System.out.println("\nLoad student: "+ tempStudent);
			System.out.println("Course: "+tempStudent.getCourses());
			
			// create more courses
			Course tempCurse1= new Course("Rubic's Cube - How to Speed Cube");
			Course tempCurse2= new Course("Atari 2600 - Game Development");
			
			// add student to courses
			tempCurse1.addStudent(tempStudent);
			tempCurse2.addStudent(tempStudent);
			
			// save the courses
			System.out.println("\nSaving the courses ..");
			session.save(tempCurse1);
			session.save(tempCurse2);
			
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
