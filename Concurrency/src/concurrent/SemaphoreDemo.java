package concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	static final int LOOP_COUNT = 99;
	static final int MAX_PERMIT = 2;
	static final int sleep = 1000;

	static final Semaphore semaphore = new Semaphore(MAX_PERMIT);

	public static void main(String[] args) {
		for (int i = 0; i < LOOP_COUNT; i++) {
			final int count = i;
			new Thread(() -> {
				String str = "";
				if(semaphore.tryAcquire())
					str += Num.getNum();
				else
					str += "*** Not acquired";

				try { Thread.sleep(sleep); } catch(InterruptedException ex) { ex.printStackTrace(); }

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

		static int getFixedNum() {
			try { Thread.sleep(sleep); } catch(InterruptedException ex) { ex.printStackTrace(); }
			return 999;
		}
		
	}

}
