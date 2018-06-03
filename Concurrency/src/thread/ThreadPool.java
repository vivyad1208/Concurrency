package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i=0; i<5;) {
			Runnable thread = new WorkerThread("Thread " + ++i);
			executor.execute(thread);
		}
		executor.shutdown();

		do {
			if(executor.isTerminated())
				System.out.println(" -- All Thread Shutdown --");
		}
		while(!executor.isTerminated());

		System.out.println(" -- Finished All Threads --");
	}
}


class WorkerThread implements Runnable {

	private String message;

	public WorkerThread(String message) {
		this.message = message;
	}

	public void run() {
		String tName = Thread.currentThread().getName();
		System.out.println(tName+" (Start) message="+message);
		processMessage();
		System.out.println(tName+" (End)");
	}

	private void processMessage() {
		try {
			Thread.sleep(2000);
		}
		catch(InterruptedException ex ) {
			System.out.println(ex);
		}
	}


}
