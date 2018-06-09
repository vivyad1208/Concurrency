package core;

public class Pool {

	public static void main(String[] args) throws Exception {
		Integer i = Integer.valueOf("-128");
		Integer j = new Integer("-128");
		System.out.println(i.hashCode());
		System.out.println(j.hashCode());
		if(i == j)
			System.out.println("Match");
		else
			System.out.println("No Match");
	}
}
