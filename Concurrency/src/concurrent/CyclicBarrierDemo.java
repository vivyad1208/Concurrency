package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

	private static final int MATRICES[][] = { { 1 }, { 2, 2 }, { 3, 3, 3 }, { 4, 4, 4, 4 }, { 5, 5, 5, 5, 5 } };
	private static final int TIMES = MATRICES.length;
	private static final int ROW_SUM[] = new int[TIMES];

	public static void main(String[] args) {

		CyclicBarrier barrier = new CyclicBarrier( TIMES,
			// This Runnable will run after all the thread are reached on the barrier
			() -> {
				int sum = 0;
				for(int i=0; i<TIMES; i++) {
					sum += ROW_SUM[i];
				}
				System.out.println(sum);
			}
		);

		for(int i=0; i<TIMES; i++) {
			new Thread(new Calculate(barrier, i)).start();
		}

	}
	
	private static class Calculate implements Runnable {
		private CyclicBarrier barrier;
		private int row;

		public Calculate(CyclicBarrier barrier, int row) {
			this.barrier = barrier;
			this.row = row;
		}

		public void run() {
			int sum = 0;
			int[] matrices = MATRICES[row];
			int len = matrices.length;
			for(int i=0; i<len; i++) {
				sum += matrices[i];
			}
			ROW_SUM[row] = sum;
			try {
				// Thread awaits on the barrier
				barrier.await();
			}
			catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
	
}