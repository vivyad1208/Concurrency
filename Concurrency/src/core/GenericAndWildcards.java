package core;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class GenericAndWildcards {

	public static void main(String[] args) {
		// Will Accept on Number - Wrapper Objects
		List<Double> nList = new LinkedList<>();
		nList.add(new Double("3.2"));
		nList.add(3d);

		// Upper bounded wild-cards
		// Only ways to add the elements to this kind of Generic List is, through
		// Constructors
		LinkedList<? extends Number> lowerBoundedlist = new LinkedList<>(nList);
		// list.add(1d); // This does not work

		// Lower Bounded wild-cards
		LinkedList<? super Double> upperBoundedlist = new LinkedList<>(lowerBoundedlist);
		upperBoundedlist.add(2d); // This does work

		boolean reverseSort = true;
		Collections.sort(nList, new GenericAndWildcards().new Sort(reverseSort));
		System.out.println(nList);

		listOperation(lowerBoundedlist, upperBoundedlist);
	}

	private static void listOperation(final LinkedList<?>... list) {
		// list = new LinkedList[]{ new LinkedList<>() }; // This does not work as the parameter copy here
		// final and could not be changed.

		for (List<?> l : list)
			System.out.println(l);
	}


	private class Sort implements Comparator<Number> {
		private boolean reverse = false;

		private Sort() { }

		private Sort(boolean reverse) {
			this.reverse = reverse;
		}

		@Override
		public int compare(Number o1, Number o2) {
			// Reverse sort by reversing the position of the parameters.
			if(reverse)
				return sort(o2, o1);
			return sort(o1, o2);
		}

		public int sort(Number o1, Number o2) {
			double d1 = o1.doubleValue();
			double d2 = o2.doubleValue();
			double ans = d1 - d2;
			if(-1<ans && ans<1) {
				if(ans==0) {
					return 0;
				}
				return (0<ans && ans<1) ? 1 : -1;
			}
			return (int)ans;
		}
	}
}
