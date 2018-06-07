package sync;

/**
 * OUTPUT:
 * 
	Thread 1 entered the static method.
	RunInstance entered the static method.
	Thread 1 exiting the static method.
	RunInstance exiting the static method.
	Thread 4 entered the static method.
	Thread 4 exiting the static method.
	RunStaticDefault entered the static method.
	RunStaticDefault exiting the static method.
	Exiting Main.
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
		// Even after sharing different resource the thread has to wait.
		// 'static synchronized' implements lock on the class hence monitor takes the entire class

		RunStatic resource1 = new RunStatic("Thread 1");
		Thread t1 = new Thread(resource1);

		RunStaticDefault resource2 = new RunStaticDefault();
		Thread t2 = new Thread(resource2);

		RunInstance resource3 = new RunInstance();
		Thread t3 = new Thread(resource3);

		RunStatic resource4 = new RunStatic("Thread 4");
		Thread t4 = new Thread(resource4);

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		t4.join();
		System.out.println("Exiting Main.");
	}

	static synchronized void staticMessage(RunStatic runInstance) {
		String threadName = runInstance.getThreadName();
		System.out.println(threadName+" entered the static method.");
		//try { Thread.sleep(1000); } catch (InterruptedException ex) { ex.printStackTrace(); }
		System.out.println(threadName+" exiting the static method.");
		//try { Thread.sleep(1000); } catch (InterruptedException ex) { ex.printStackTrace(); }
	}

	static synchronized void staticMessage(RunStaticDefault runInstance) {
		System.out.println("RunStaticDefault entered the static method.");
		//try { Thread.sleep(1000); } catch (InterruptedException ex) { ex.printStackTrace(); }
		System.out.println("RunStaticDefault exiting the static method.");
		//try { Thread.sleep(1000); } catch (InterruptedException ex) { ex.printStackTrace(); }
	}

	synchronized void objectMessage(RunInstance runInstance) {
		System.out.println("RunInstance entered the static method.");
		//try { Thread.sleep(1000); } catch (InterruptedException ex) { ex.printStackTrace(); }
		System.out.println("RunInstance exiting the static method.");
		//try { Thread.sleep(1000); } catch (InterruptedException ex) { ex.printStackTrace(); }
	}
}


final class RunStatic implements Runnable {

	private String threadName;

	public RunStatic(String threadName) {
		this.threadName = threadName;
	}

	public String getThreadName() {
		return threadName;
	}

	public void run() {
		StaticSyncMethod.staticMessage(this);
	}
}


final class RunStaticDefault implements Runnable {

	public void run() {
		StaticSyncMethod.staticMessage(this);
	}
}


final class RunInstance implements Runnable {

	public void run() {
		// Synchronization at Object level.
		// This one will run differently compared to the other ones.
		new StaticSyncMethod().objectMessage(this);
	}
}
