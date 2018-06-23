package core;

import java.util.LinkedList;

public class GenericAndWildcards {

	public static void main(String[] args) {
		// Will Accept on Number - Wrapper Objects
		LinkedList<Number> nList = new LinkedList<>();
		nList.add(new Double(3.2));
		LinkedList<? extends Number> list = nList;
		System.out.println(list);
	}
}
