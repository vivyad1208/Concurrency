package core;

import java.util.ArrayList;
import java.util.List;

public class Pool {

	public static void main(String[] args) throws Exception {
		Integer i = 129;
		Integer j = 129;
		List<Integer> listInt = new ArrayList<>();
		listInt.add(i);
		listInt.add(j);
		System.out.println(i.hashCode());
		System.out.println(j.hashCode());
		// Match the integers
		if(i==j)
			System.out.println("Integer -> Match");
		else
			System.out.println("Integer -> No Match");
		// Match the integers in the list
		if(listInt.get(0).intValue() == listInt.get(1))
			System.out.println("List -> Match");
		else
			System.out.println("List -> No Match");
	}
}
