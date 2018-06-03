package thread;

/**
 * Program OUTPUT : <br/><br/>
 * 
 * Run 3   <br/>
 * Run 2   <br/>
 * Run 1   <br/>
 * Joining Thread 2 In Run 3   <br/>
 * Joining Thread 1 In Run 2   <br/>
 * Joining Thread 3 In Main    <br/>

 * @author Vivek
 *
 */
public class ThreadsJoin {

	public static void main(String[]str) throws InterruptedException {
		// Thread tm = Thread.currentThread();

		RunClass run1 = new RunClass("Run 1");
		Thread t1 = new Thread(run1, "Thread 1");

		RunClass run2 = new RunClass("Run 2", t1);
		Thread t2 = new Thread(run2, "Thread 2");

		RunClass run3 = new RunClass("Run 3", t2);
		Thread t3 = new Thread(run3, "Thread 3");

		t3.start();
		Thread.sleep(100);
		t2.start();
		Thread.sleep(100);
		t1.start();
		Thread.sleep(1000);

		t3.join();
		System.out.println("Joining Thread 3 In Main");
	}

	private static class RunClass implements Runnable {

		private String runnableName;
		private Thread thread;

		public RunClass(String runnableName) {
			this.runnableName = runnableName;
		}

		public RunClass(String threadName, Thread thread) {
			this.runnableName = threadName;
			this.thread = thread;
		}

		public void run() {
			try {
				System.out.println(runnableName);
				if(thread!=null) {
					thread.join();
					Thread.sleep(500);
					System.out.println("Joining "+thread.getName()+" In "+runnableName);
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
