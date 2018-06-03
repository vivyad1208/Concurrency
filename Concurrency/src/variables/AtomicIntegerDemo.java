package variables;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

	private static AtomicCounter counter = new AtomicCounter();

	public enum Status {

		VERYHIGH(100), HIGH(80), MIDDLE(60), LOW(40), VERYLOW(20), UP(true), DOWN(false);

		private int priority;
		private boolean state;
		private Status status;

		private Status(int p){
			priority = p;
		}
		public int getPriorityValue() {
			return priority;
		}

		private Status(boolean s){
			state = s;
		}
		public boolean getStateValue() {
			return state;
		}

		private Status(Status s){
			status = s;
		}
		public Status getStatusValue() {
			return status;
		}

		public static Status getRandomPriority() {
			Random random = new Random();
			int randomNumber = random.nextInt(100);

			if(randomNumber>Status.HIGH.getPriorityValue())
				return Status.VERYHIGH;
			if(randomNumber>Status.MIDDLE.getPriorityValue())
				return Status.HIGH;
			if(randomNumber>Status.LOW.getPriorityValue())
				return Status.MIDDLE;
			if(randomNumber>Status.VERYLOW.getPriorityValue())
				return Status.LOW;
			return Status.VERYLOW;
		}

		public static Status getRandomState() {
			Random random = new Random();
			boolean randomState = random.nextBoolean();
			if(randomState)
				return UP;
			return DOWN;
		}
	}

	private static class AtomicCounter {
		private AtomicInteger atomicInt = new AtomicInteger();
		public int value() {
			return atomicInt.get();
		}
		public void increment() {
			atomicInt.incrementAndGet();
		}
		public void decrement() {
			atomicInt.decrementAndGet();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int max = 1;
		Thread[] thread = new Thread[max];
		for (int i = 0; i < max; i++) {
			thread[i] = new Thread(new DemoThread());
		}
		for (int i = 0; i < max; i++) {
			thread[i].start();
			thread[i].join();
			System.out.println("Counter Value: "+counter.value());
		}

		System.out.println(list);
		int sum = 0;
		for(Integer i:list)
			sum += i;
		System.out.println(sum);
	}

	public static ArrayList<Integer> list = new ArrayList<>();

	private static class DemoThread implements Runnable {
		public void run() {
			boolean state = Status.getRandomState().getStateValue();
			int priority = Status.getRandomPriority().getPriorityValue();
			int number = priority*(state ?1:-1);
			System.out.println("Number: "+number);
			list.add(number);
			for(int i=0; i<priority; i++)
				if(state)
					counter.increment();
				else
					counter.decrement();
		}
	}

}
