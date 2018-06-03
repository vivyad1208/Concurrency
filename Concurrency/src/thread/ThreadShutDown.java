package thread;

/**
 * @author Vivek
 *
 */
public class ThreadShutDown {
	public static void main(String[] args) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		runtime.addShutdownHook(new MyThread());
		System.out.println("Now main sleeping... press ctrl+c to exit");
		Thread.sleep(3300);
	}
}


class MyThread extends Thread {

	@Override
	public void run() {
		System.out.println("shut down hook task completed..");
	}

	
	
}