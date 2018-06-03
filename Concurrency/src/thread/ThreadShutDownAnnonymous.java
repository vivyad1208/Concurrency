package thread;
/**
 * @author Vivek
 *
 */
public class ThreadShutDownAnnonymous {

	public static void main(String[] args) throws Exception {

		Runtime runtime = Runtime.getRuntime();
		runtime.addShutdownHook( new Thread(new Runnable() {
			public void run() {
				System.out.println("Shut Down hook task completed..");
			}
		}));

		System.out.println("Now main sleeping... press ctrl+c to exit");

		Thread.sleep(3000);
	}
}

