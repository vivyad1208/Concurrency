package sync;

public class SynchronizedMethod {

	public static void main(String[] args) {
		Resource resource = new Resource();
		Call $1 = new Call(resource, "Hello");
		Call $2 = new Call(resource, "Synchronized");
		Call $3 = new Call(resource, "World");
		try {
			$1.thread.join();
			$2.thread.join();
			$3.thread.join();
		}
		catch(InterruptedException ex) {
			System.err.println("Main Thread has been Interruped.");
		}
	}


	private static class Call implements Runnable {
		String message;
		Resource resource;
		Thread thread;

		Call(Resource resource, String message) {
			this.message = message;
			this.resource = resource;
			thread = new Thread(this);
			thread.start();
		}

		public void run() {
			resource.use(message);
		}
	}


	private static class Resource {

		Resource() { }

		synchronized void use(String pass) {
			try {
				System.out.print("["+pass);
				System.out.println("]");
			}
			catch (Exception e) {
				System.err.println("Resource Interrupted");
			}
		}
	}

}
