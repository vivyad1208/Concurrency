package sync;

public class SynchronizedStatement {


	public static void main(String[] args) {
		int i=0;
		Resource resource = new Resource();
		new Call(resource, "Hello", ++i);
		new Call(resource, "Synchronized", ++i);
		try {
			new Call(resource, "World", ++i).thread.join();
		}
		catch(InterruptedException ex) {
			System.err.println("Main Thread has been Interruped.");
		}
	}


	private static class Call implements Runnable {
		String message;
		Resource resource;
		Thread thread;
		int num;

		Call(Resource resource, String message, int num) {
			this.message = message;
			this.resource = resource;
			this.num = num;
			thread = new Thread(this);
			thread.start();
		}

		public void run() {
			System.out.println("Thread "+num+" Outside Sync");
			synchronized (resource) {
				resource.use(message);
			}
		}
	}


	private static class Resource {
		Resource(){}

		void use(String pass) {
			try {
				System.out.print("["+pass);
				Thread.sleep(2000);
				System.out.println("]");
			}
			catch (Exception e) {
				System.err.println("Resource Interrupted");
			}
		}
	}

}
