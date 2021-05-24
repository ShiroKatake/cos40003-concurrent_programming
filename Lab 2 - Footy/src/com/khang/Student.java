package com.khang;
import java.util.concurrent.Semaphore;

public class Student extends Thread{

	private String name; // name to print
	private long time; //seconds
	Semaphore chairPermit;
	ProducerConsumerImpl pc;
	Teacher professor;

	public Student(String name, long timeVisit, Teacher professor, ProducerConsumerImpl pc, Semaphore chairPermit){
		super(name);
		this.name = name;
		this.pc = pc;
		this.chairPermit = chairPermit;
		this.time = timeVisit;
		this.professor = professor;
	}

	@Override public void run() {
		try{
			Thread.sleep(time * 1000);
			if (chairPermit.availablePermits() > 0) {
				chairPermit.acquire();
				System.out.println(name + " just sat down;");
				pc.get();
				chairPermit.release();
			}
			else {
				System.out.println("No available seat, " + name + " just left;");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}