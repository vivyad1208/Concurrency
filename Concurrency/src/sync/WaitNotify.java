package sync;

public class WaitNotify {
	public final int i;

	public WaitNotify() {
		i=0;
	}

	public static void main(String[] args) throws InterruptedException {
		Product product = new Product();
		new Producer(product);
		double d = new Consumer(product).d;
		Thread.currentThread().sleep(2000);
		System.out.println(d);
	}
}

class Consumer implements Runnable {
	Product product;
	double d;
	Consumer(Product product) {
		this.product = product;
		new Thread(this,"Consumer").start();
	}
	public void run() {
		while(product.get()<=99) {
			try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
			product.get();
		}
	}
}

class Producer implements Runnable {
	Product product;
	Producer(Product product) {
		this.product = product;
		new Thread(this,"Producer").start();
	}
	public void run() {
		int i=0;
		while(i<100) {
			try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }
			product.put(++i);
		}
	}
}

class Product {
	int i;
	boolean putting = false;
	public synchronized int get() {
		if(!putting) {
			try { wait(); } catch(InterruptedException ex) {}
		}
		System.out.println("Get: "+i);
		putting = false;
		notify();
		return i;
	}
	public synchronized void put(int i) {
		if(putting) {
			try { wait(); } catch(InterruptedException ex) {}
		}
		putting = true;
		this.i = i;
		System.out.println("Put: "+i);
		notify();
	}
}
