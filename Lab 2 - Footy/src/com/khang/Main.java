package com.khang;
import java.util.concurrent.Semaphore;

public class Main {
	
	public static void main (String[] args) {

		Semaphore chairPermit = new Semaphore(2);

		ProducerConsumerImpl knowledge = new ProducerConsumerImpl();

		Teacher professor = new Teacher(knowledge, chairPermit);

		Student student1 = new Student("student1", 1, professor, knowledge, chairPermit);
		Student student2 = new Student("student2", 2, professor, knowledge, chairPermit);
		Student student3 = new Student("student3", 3, professor, knowledge, chairPermit);
		Student student4 = new Student("student4", 4, professor, knowledge, chairPermit);
		Student student5 = new Student("student5", 5, professor, knowledge, chairPermit);
		Student student6 = new Student("student6", 6, professor, knowledge, chairPermit);
		Student student7 = new Student("student7", 7, professor, knowledge, chairPermit);
		Student student8 = new Student("student8", 8, professor, knowledge, chairPermit);
		Student student9 = new Student("student9", 9, professor, knowledge, chairPermit);

		professor.start();
		student1.start();
		student2.start();
		student3.start();
		student4.start();
		student5.start();
		student6.start();
		student7.start();
		student8.start();
		student9.start();
	}

}