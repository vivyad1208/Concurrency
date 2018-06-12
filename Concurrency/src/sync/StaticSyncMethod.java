package sync;

/**
 * OUTPUT:
	Thread 1 exiting the static method.
	Thread 4 exiting the static method.
	Thread 3 RunInstance exiting the object method
	Thread 2 RunStatic ***Static Str*** exiting the static method.
	Thread 5 RunInstance exiting the object method new.
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
		StaticSyncMethod staticSyncMethod = new StaticSyncMethod();

		// Even after sharing different resource the thread has to wait.
		// 'static synchronized' implements lock on the class hence monitor takes the entire class

		Thread t1 = new Thread(new RunStatic(), "Thread 1");

		Runnable r = () -> { StaticSyncMethod.staticMessage("***Static Str***"); };
		Thread t2 = new Thread( r, "Thread 2" );

		//System.out.println(t2.getState());

		// Synchronization at Object level.
		// This one will run differently compared to the static ones.
		Thread t3 = new Thread(() -> staticSyncMethod.objectMessage(), "Thread 3");

		Thread t4 = new Thread(new RunStatic(), "Thread 4");

		// Synchronization at Object level.
		Thread t5 = new Thread(() -> staticSyncMethod.objectMessageNew(), "Thread 5");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

		//System.out.println(t1.getState());
		//System.out.println(t2.getState());

		t4.join();
		t5.join();

		System.out.println("Exiting Main.");
	}

	static synchronized void staticMessage(RunStatic runInstance) {
		System.out.println(Thread.currentThread().getName()+" exiting the static method.");
	}

	static synchronized void staticMessage(String str) {
		System.out.println(Thread.currentThread().getName()+" RunStatic "+str+" exiting the static method.");
	}

	synchronized void objectMessage() {
		System.out.println(Thread.currentThread().getName()+" RunInstance exiting the object method");
	}

	synchronized void objectMessageNew() {
		System.out.println(Thread.currentThread().getName()+" RunInstance exiting the object method new.");
	}
}


final class RunStatic implements Runnable {

	public void run() {
		StaticSyncMethod.staticMessage(this);
	}
}

