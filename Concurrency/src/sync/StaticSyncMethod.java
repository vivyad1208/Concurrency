package sync;

public class StaticSyncMethod {

	public static void main(String[] args) throws InterruptedException {
		RunInstance resource1 = new RunInstance("Thread 1");
		Thread t1 = new Thread(resource1);

		RunInstance resource2 = new RunInstance("Thread 2");
		Thread t2 = new Thread(resource2);

		RunInstance resource3 = new RunInstance("Thread 3");
		Thread t3 = new Thread(resource3);

		t1.start();
		t2.start();
		t3.start();

		t3.join();
	}

	public static synchronized void message(RunInstance runInstance) throws InterruptedException {
		String threadName = runInstance.getThreadName();
		System.out.println(threadName+" entered the static method.");
		Thread.sleep(1000);
		System.out.println(threadName+" exiting the static method.");
	}
}

final class RunInstance implements Runnable {

	private String threadName;

	public RunInstance(String threadName) {
		this.threadName = threadName;
	}

	public String getThreadName() {
		return threadName;
	}

	public void run() {
		try {
			StaticSyncMethod.message(this);
		}
		catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}