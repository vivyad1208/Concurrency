package core;

public class Pool {

	public static void main(String[] args) throws Exception {
		Double i = Double.valueOf(2);
		Double j = Double.valueOf(2);
		System.out.println(i.hashCode());
		System.out.println(j.hashCode());
		if(i == j)
			System.out.println("Match");
		else
			System.out.println("No Match");
	}
}
