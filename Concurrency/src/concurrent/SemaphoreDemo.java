package concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Example of the Semaphore implementation in Java.
 * 
 * semaphore.tryAcquire() -> Acquires a permit from this semaphore, only if one is available at the time of invocation.
 * semaphore.release() -> Releases a permit, returning it to the semaphore.
 * 
 * @author Vivek
 *
 */
public class SemaphoreDemo {

	static final int LOOP_COUNT = 15;
	static final int MAX_PERMIT = 1;
	static final int sleep = 500;

	static final Semaphore semaphore = new Semaphore(MAX_PERMIT);

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < LOOP_COUNT; i++) {
			final int count = i;
			Thread.sleep(200);
			new Thread(() -> {
				String str = "";
				if(semaphore.tryAcquire()) {
					str += Num.getNum();
					semaphore.release();
				}
				else {
					str += "*** Not acquired";
				}

				if(count<9)
					System.out.println(" "+(1+count) +": "+str);
				else
					System.out.println((1+count) +": "+str);

			}).start();
		}
	}

	private static class Num {
		static Random r = new Random();

		static int getNum() {
			try { Thread.sleep(sleep); } catch(InterruptedException ex) { ex.printStackTrace(); }
			return r.nextInt(100);
		}
		
	}

}
