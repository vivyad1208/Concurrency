package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
