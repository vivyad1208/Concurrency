package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <b> Callable: </b><br/>
 * The {@link Callable} interface is similar to Runnable, in that both are designed for classes
 * whose instances are potentially executed by another thread.
 * A Runnable, however, does not return a result and cannot throw a checked exception.
 * This interface use Generic to define the return type of Object.
 * <br/>
 * The {@link Executors} class contains utility methods to convert from other common forms to Callable classes.
 * Executors class provide useful methods to execute Java Callable in a thread pool.
 * Since callable tasks run in parallel, we have to wait for the returned Object.
 * 
 * <br/><br/>
 * 
 * <b> Future: </b><br/>
 * Java Callable tasks returns {@link Future} object. Using Java Future object,
 * we can find out the status of the Callable task and get the returned Object.
 * It provides get() method that can wait for the Callable to finish and then return the result.
 * Java Future provides cancel() method to cancel the associated Callable task.
 * <br/>
 * There is an overloaded version of get() method where we can specify the time to wait for the result,
 * it’s useful to avoid current thread getting blocked for longer time.
 * There are isDone() and isCancelled() methods to find out the current status of associated Callable task.
 * 
 * <br/>
 * 
 * @author Vivek
 *
 */
public class ThreadCallable {

	public static void main(String[] args) throws Exception {
		ThreadCallable t = new ThreadCallable();
		Future<Integer> fSquare = t.calculateSquare(5);
		System.out.println(fSquare.get());
		t.executor.shutdown();
	}

	private ExecutorService executor = Executors.newSingleThreadExecutor();

	public Future<Integer> calculateSquare(Integer input) {
		return executor.submit(new Callable<Integer>() {
			public Integer call() throws InterruptedException {
				Thread.sleep(1000);
	            return input * input;
			}
		});

		/*
		// USING JAVA 8
		return executor.submit(() -> {
			Thread.sleep(1000);
            return input * input;
		});
		*/
	}
}
