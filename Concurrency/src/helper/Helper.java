package helper;

public class Helper {

	public static void printThreadInfo(Thread t) {
		String daemon = t.isDaemon()?"Deomon":"Normal";
		String state = t.isAlive()?"Alive":"Dead";
		System.out.println(t+" *** "+daemon+" *** "+state+" ***");
	}

	public static void printThreadInfo(Thread t, String msg) {
		String daemon = t.isDaemon()?"Deomon":"Normal";
		String state = t.isAlive()?"Alive":"Dead";
		System.out.println(msg+" *** "+t+" *** "+daemon+" *** "+state+" ***");
	}

	public static void print(Object obj) {
		System.out.print(obj);
	}

	public static void println(Object obj) {
		System.out.println(obj);
	}
}
