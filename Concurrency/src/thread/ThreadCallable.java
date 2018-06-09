package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadCallable {

	public static void main(String[] args) throws Exception {
		ThreadCallable t = new ThreadCallable();
		Future<Integer> fSquare = t.calculate(5);
		System.out.println(fSquare.get());
		t.executor.shutdown();
	}

	private ExecutorService executor = Executors.newSingleThreadExecutor();

	public Future<Integer> calculate(Integer input) {
		return executor.submit(new Callable<Integer>() {
			public Integer call() throws InterruptedException {
				Thread.sleep(1000);
	            return input * input;
			}
		});
	}
}
