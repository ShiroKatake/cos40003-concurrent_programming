package com.khang;

import java.util.LinkedList;
import java.util.Queue; 
import java.util.Random; 
import java.util.concurrent.Semaphore; 

/** * Java Program to demonstrate how to use Lock and Condition variable in Java by * solving Producer consumer problem. Locks are more flexible way to provide * mutual exclusion and synchronization in Java, a powerful alternative of * synchronized keyword. * * @author Javin Paul */ 
public class Main {
	public static void main(String[] args) { 
		// Object on which producer and consumer thread will operate 
		ProducerConsumerImpl sharedObject = new ProducerConsumerImpl(); 
		// creating producer and consumer threads 
		Producer p1 = new Producer(sharedObject); 
		Producer p2 = new Producer(sharedObject); 
		Consumer c1 = new Consumer(sharedObject);
		Consumer c2 = new Consumer(sharedObject); 
		// starting producer and consumer threads 
		p1.start(); 
		c1.start();
		c2.start();
	} 
} 

class ProducerConsumerImpl { 
	// producer consumer problem data 
	private static final int CAPACITY = 20; 
	private final Queue<Integer> queue = new LinkedList<Integer>(); 
	private final Random theRandom = new Random(); 
	// semaphore 
	
	private final Semaphore empty = new Semaphore(CAPACITY); 
	private final Semaphore full = new Semaphore(0);
	private final Semaphore mutex = new Semaphore(1);
	
	public void put() throws InterruptedException { 
		
		try { 
			empty.acquire();
			//mutex.acquire();
			int number = theRandom.nextInt(); 
			boolean isAdded = queue.offer(new Integer(number)); 
			if (isAdded) { 
				System.out.printf("%s added %d into queue %n", Thread .currentThread().getName(), number); 
				// signal consumer thread that, buffer has element now 
				//mutex.release();
				full.release(); 
			}else {
				//mutex.release();
			} 
		} catch(InterruptedException e) { 
			e.printStackTrace(); 
		} 
	} 
	
	public void get() throws InterruptedException { 

		try { 
			full.acquire();
			//mutex.acquire();
			Integer value = (Integer)queue.poll(); 
			if (value != null) { 
				System.out.printf("%s consumed %d from queue %n", Thread .currentThread().getName(), value); 
				// signal producer thread that, buffer may be empty now 
				//mutex.release();
				empty.release();
			}else {
				//mutex.release();
			} 
		} catch(InterruptedException e) { 
			e.printStackTrace(); 
		} 
	} 
} 

class Producer extends Thread { 
	ProducerConsumerImpl pc; 
	
	public Producer(ProducerConsumerImpl sharedObject) { 
		super("PRODUCER"); 
		this.pc = sharedObject; 
	} 
	@Override public void run() { 
		try { 
			for(int i=0; i<4; i++)
				pc.put(); 
		} 
		catch (InterruptedException e) { 
			e.printStackTrace(); 
		} 
	} 
} 


class Consumer extends Thread { 
	ProducerConsumerImpl pc; 
	public Consumer(ProducerConsumerImpl sharedObject) { 
		super("CONSUMER"); 
		this.pc = sharedObject; 
	} 
	@Override public void run() { 
		try { 
			for(int i=0; i<4; i++)
				pc.get(); 
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		} 
		
	}
}

