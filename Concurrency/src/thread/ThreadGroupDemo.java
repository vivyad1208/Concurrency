package thread;

import static helper.Helper.printThreadInfo;

public class ThreadGroupDemo {

	public static void main(String[] args) throws InterruptedException {
		Thread t = null;
		Thread mainThread = Thread.currentThread();
		ThreadGroup threadGroup = new ThreadGroup("Demo Thread Group");
		for(int i=1; i<5; i++) {
			String msg = "$$$ Viveks Runnable "+i;
			t = new Thread(threadGroup, new DemoThread(i,mainThread), msg);
			t.start();
		}

		checkThreadGroupActiveCount(threadGroup);
		
		t.join();
	}


	private static void checkThreadGroupActiveCount(ThreadGroup threadGroup) throws InterruptedException {
		String msg = "[Thread Group] Active Count After (";
		int wait = 0;
		int[]waitingAddition = {0, 1000, 2, 500};
		for (int i : waitingAddition) {
			wait += i;
			//Thread.sleep(i);
			System.out.println(msg+wait+") -> "+threadGroup.activeCount());
			
		}
	}


	public static class DemoThread implements Runnable {
		int count;
		private Thread mainThread;

		DemoThread(int count, Thread mainThread ) {
			// EXECUTED UNDER THE MAIN
			// Thread.currentThread() -> RETURNS THE MAIN THREAD
			this.count = count;
			this.mainThread = mainThread;
			System.out.println("[Running] "+count+", WaitTime:"+(1000+count));
		}

		public void run() {
			// EXECUTED NOT UNDER THE MAIN THREAD
			// NEW THREAD IS STARTED FROM THIS POINT ONWARDS
			// Thread.currentThread() -> RETURNS THE NEW THREAD
			// A NEW THREAD IS VISIBLE IN THE DEBUG
			try { Thread.sleep(count); } catch (InterruptedException e) { e.printStackTrace(); }
			printThreadInfo(Thread.currentThread(),"[Group Thread]");
			try { Thread.sleep(1000+count); } catch (InterruptedException e) { e.printStackTrace(); }
			System.out.println("[Terminating] "+count);
			printThreadInfo(mainThread,"[Main Thread]");
		}
	}
}
