package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * From Java Docs:
 * This class provides thread-local variables.
 *  
 * These variables differ from their normal counterparts in that each thread that accesses one
 * (via its get or set method) has its own, independently initialized copy of the variable.
 * ThreadLocal instances are typically private static fields in classes that wish to associate
 * state with a thread (e.g., a user ID or Transaction ID).
 * 
 * @author vivekyadav
 *
 */
public class ThreadLocalDemo {

	// Atomic integer containing the next thread ID to be assigned
	private static final AtomicInteger nextId = new AtomicInteger(0);

	// Thread local variable containing each thread's ID
	// JAVA 8 :: anonymous class declaration.
	// private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(nextId::getAndIncrement)

	// Thread local variable containing each thread's ID
	private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return nextId.getAndIncrement();
		}

		@Override
		public Integer get() {
			return initialValue();
		}
	};
	 

	// Returns the current thread's unique ID, assigning it if necessary
	public static int get() {
		return threadId.get();
	}

	public static void main(String[] args) {
		System.out.println(get());
		System.out.println(get());
		System.out.println(get());
		System.out.println(get());
	}
}
