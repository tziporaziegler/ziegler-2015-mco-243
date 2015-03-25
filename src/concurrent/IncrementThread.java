package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/** increase a counter 10,000,000 times using multiple threads
 * problem: if just do counter = counter + 1 (counter++), have times where two threads get call
 * counter at same exact moment and then increase it by the same 1.
 * ex: if counter = 88 > 2 threads call counter = counter + 1 at same time > bother do counter = 88
 * + 1 > set counter to = 89 = end up skipping on incrementation so won't get correct final count
 * solution: must synchronize the threads so only one thread actually increases counter at a time */

public class IncrementThread extends Thread {
	// use AtomicInteger as opposed to Integer(which just wraps a primitive number) or int
	// since AtomicInteger comes with synchronized methods
	// doesn't need to be static since passing in the same Object to all the thread instances and
	// all just referencing same Object that was created in main
	private AtomicInteger counter;

	// use countDownLatch to make sure all the threads finish before you print the final number
	private CountDownLatch latch;

	// Other methods to synchronize threads:
	// static Object lock = new Object(); ... synchronized(lock){}
	// public static synchronized method() {} - same as synchronized(this){}
	// static Semaphore semaphore = new Semaphore(1, true); ...
	// semaphore.accept(); (insert all code) semaphore.release();

	public IncrementThread(AtomicInteger counter, CountDownLatch latch) {
		this.latch = latch;
		this.counter = counter;
	}

	public void run() {
		for (int i = 0; i < 1000000; i++) {
			// critical section
			// the AtomicInteger incrementAndGet method has build in synchronization
			counter.incrementAndGet();
		}
		latch.countDown();
	}

	public static void main(String args[]) {
		CountDownLatch latch = new CountDownLatch(10);
		AtomicInteger counter = new AtomicInteger(0);
		for (int i = 0; i < 10; i++) {
			new IncrementThread(counter, latch).start();
		}
		try {
			latch.await();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(counter);
	}
}