package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <b> Thread Pool:</b><br/>
 * Java Thread pool represents a group of worker threads that are waiting for the job and reuse many times.
 * <br/>
 * In case of thread pool, a group of fixed size threads are created.
 * A thread from the thread pool is pulled out and assigned a job by the service provider.
 * After completion of the job, thread is contained in the thread pool again.
 * <br/>
 * It saves time because there is no need to create new thread.
 * 
 * <br/><br/>
 * 
 * <b><i> {@linkjava.util.concurrent.ThreadPoolExecutor} extends {@linkjava.util.concurrent.AbstractExecutorService} </i></b> <br/>
 * <b><i> {@linkjava.util.concurrent.AbstractExecutorService} implements {@linkjava.util.concurrent.ExecutorService} </i></b>
 * 
 * <br/><br/>
 * 
 * <b> {@link java.util.concurrent.ThreadPoolExecutor} Variables:</b><br/>
 * corePoolSize (int): Threads that can reside in the pool. <br/>
 * largestPoolSize (int): Number of threads in the pool. <br/>
 * maximumPoolSize (int): Maximum number of threads that can reside in the pool. <br/>
 * workers (HashSet<E>): Contains the reference of the Thread within the pool. <br/>
 * 
 * <br/><br/>
 * 
 * <b> {@link java.util.concurrent.Executor} Methods:</b><br/>
 * execute(Runnable): Adds the thread in the pool incrementing the largestPoolSize variables. <br/>
 * 
 * @author Vivek
 *
 */
public class ThreadPool {

	public static void main(String[] args) throws InterruptedException {

		final int totalThreads = 5;

		// New Thread Pool with 5 threads
		ExecutorService executor = Executors.newFixedThreadPool(totalThreads);

		for(int i=0; i<totalThreads;) {
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
