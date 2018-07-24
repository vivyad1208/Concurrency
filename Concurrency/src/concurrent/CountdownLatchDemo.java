package concurrent;

import java.util.concurrent.CountDownLatch;

public class CountdownLatchDemo {

	private static final int COUNT = 10;

	private static class Work implements Runnable {
		CountDownLatch Latch_start;
		CountDownLatch Latch_stop;
		String name;

		Work(CountDownLatch Latch_start, CountDownLatch Latch_stop, String name) {
			this.Latch_start = Latch_start;
			this.Latch_stop = Latch_stop;
			this.name = name;
		}

		public void run() {
			try {
				Latch_start.await();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println("Running: " + name);
			Latch_stop.countDown();
		}
	}

	public static void main(String args[]) {
		CountDownLatch Signal_start = new CountDownLatch(1);
		CountDownLatch Signal_stop = new CountDownLatch(COUNT);

		for (int i = 0; i < COUNT; i++) {
			new Thread(new Work(Signal_start, Signal_stop, Integer.toString(i))).start();
		}
		System.out.println("Go");
		Signal_start.countDown();
		try {
			Signal_stop.await();
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println("Done");
	}
}
