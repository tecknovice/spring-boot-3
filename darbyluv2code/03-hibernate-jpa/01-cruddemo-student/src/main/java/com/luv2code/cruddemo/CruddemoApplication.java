package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner ->{
//			createStudent(studentDAO);
			readStudent(studentDAO);
//			queryAllStudent(studentDAO);
//			queryByLastName(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);
//			deleteAllStudent(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {
		System.out.println("Delete all students");
		int rows = studentDAO.deleteAll();
		System.out.println("rows: "+ rows);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId=2;
		studentDAO.delete(studentId);
		System.out.println("deleted");
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentId = 1;
		Student student = studentDAO.findById(1);
		student.setFirstName("Kevin");
		studentDAO.update(student);
		System.out.println("Updated student " + student);
	}

	private void queryByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Doe");
		for (Student student: students){
			System.out.println(student);
		}
	}

	private void queryAllStudent(StudentDAO studentDAO) {
		System.out.println("query all student");
		List<Student> students = studentDAO.findAll();

		System.out.println("display all students");
		for(Student student: students){
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		//create
		System.out.println("create student");
		Student tempStudent = new Student("John","Doe","john@email.com");

		//save
		System.out.println("save student");
		studentDAO.save(tempStudent);

		//display id of saved student
		Integer id = tempStudent.getId();
		System.out.println("saved student id: " +id);

		//retrieve student based on id
		System.out.println("retrieve student based on id");
		Student student = studentDAO.findById(id);

		//display student
		System.out.println("display student");
		System.out.println(student);
	}

	private void createStudent(StudentDAO studentDAO) {
		//create
		System.out.println("creating new student object");
		Student tempStudent = new Student("Hung","Nguyen","hung@email.com");

		//save
		studentDAO.save(tempStudent);

		//display id of saved student
		System.out.println("Saved student id:" + tempStudent.getId());
	}
}
