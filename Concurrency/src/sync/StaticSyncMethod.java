package sync;

/**
 * OUTPUT:
 * 
 * Thread 1 entered the static method.
 * Thread 1 exiting the static method.
 * RunInstanceDefault entered the static method.
 * RunInstanceDefault exiting the static method.
 * Thread 2 entered the static method.
 * Thread 2 exiting the static method.
 * 
 * 
 * EXPLAINATION:
 * 
 * If you observe the output closely, the execution is in very synchronized manner.
 * Hence concluding that the synchronized static locks the entire class.
 * 
 * @author Vivek
 *
 */
public class StaticSyncMethod {

	public static void main(String[] args) throws InterruptedException {
		RunInstance resource1 = new RunInstance("Thread 1");
		Thread t1 = new Thread(resource1);

		RunInstance resource2 = new RunInstance("Thread 2");
		Thread t2 = new Thread(resource2);

		RunInstanceDefault resource3 = new RunInstanceDefault();
		Thread t3 = new Thread(resource3);

		t1.start();
		t2.start();
		t3.start();

		t3.join();
	}

	synchronized public static void message(RunInstanceDefault runInstance) {
		System.out.println("RunInstanceDefault entered the static method.");
		try { Thread.sleep(1000); } catch (InterruptedException ex) { ex.printStackTrace(); }
		System.out.println("RunInstanceDefault exiting the static method.");
	}

	synchronized public static void message(RunInstance runInstance) {
		String threadName = runInstance.getThreadName();
		System.out.println(threadName+" entered the static method.");
		try { Thread.sleep(1000); } catch (InterruptedException ex) { ex.printStackTrace(); }
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
		StaticSyncMethod.message(this);
	}
}

final class RunInstanceDefault implements Runnable {

	public void run() {
		StaticSyncMethod.message(this);
	}
}
