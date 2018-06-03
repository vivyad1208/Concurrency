package thread;

import static helper.Helper.*;

public class ThreadJoin extends Thread {

	public ThreadJoin() {
		super();
	}

	public ThreadJoin(String str) {
		super(str);
	}

	public void run() {
		/*System.out.print(this+": ");
		for(int i=0; i<5; i++)
			System.out.print(i+", ");
		System.out.println();*/
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ThreadJoin t1 = new ThreadJoin("One: ");
		ThreadJoin t2 = new ThreadJoin("Two: ");
		ThreadJoin t3 = new ThreadJoin("Three: ");

		t1.setDaemon(true);
		t1.start();
		t1.join();
		printThreadInfo(t1);

		t2.start();
		printThreadInfo(t2);
		t2.join();
		printThreadInfo(t2);

		t3.start();
		t3.join();

		printThreadInfo(t3);
	}
}
