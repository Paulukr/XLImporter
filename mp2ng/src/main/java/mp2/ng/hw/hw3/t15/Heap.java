package mp2.ng.hw.hw3.t15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Level;

// junit TestSuite TesstRunner @RunWith @includeCategory @SuiteClasses @Theory @DataPoint @DataPoints
// sgml -> xml + html
//xml dtd or xsd structures, xsh
// valid: conforms xsd, conventional: conforms standard
// SAX parser for one run
// DOM parser for multiple runs
//marshaling, demarshaling analog to Serialising, object -> xml

//json: js object notation
// cons
//		Hasn't standard validation procedure
//		Hasn't attributes

public class Heap<T extends Comparable<? super T>> {

	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Heap.class.getName());
	static{
		logger.setLevel(Level.ERROR);
	}
	List<T> list = new ArrayList<>();
	{
		list.add(null);
	}

	void push(T element) {
		logger.log(Level.INFO, "\n add: " + element);
		list.add(element);
		int k = list.size() - 1;
		pullUp(k);
	}

	void pullUp(int k) {
		logger.log(Level.INFO, "pullup:");
		T current = list.get(k);
		logger.log(Level.INFO, toString());
		while (k > 1 && current.compareTo(parent(k)) < 0) {
			swapParent(k);
			k >>= 1;
			logger.log(Level.INFO, toString());
		}

	}

	void pullDown(int k) {
		T current = list.get(k);
		Loop:
		while (2 * k < list.size()) {
			if((2*k+1 < list.size()) && left(k).compareTo(right(k)) > 0){
				if (current.compareTo(right(k)) > 0){ 
					swapRight(k);
					k = k*2+1;
				}
				else 
					break Loop;
			}else {
				if (current.compareTo(left(k)) > 0){
					swapLeft(k);
					k *= 2;
				}
				else 
					break Loop;
			}
			logger.log(Level.INFO, toString());
		}

	}

	T pop() {

		if (list.size() == 1) {
			return null;
		}
		T result = list.get(1);
		int last = list.size() - 1;
		logger.log(Level.INFO, "\npop:" + result);

		list.set(1, list.get(last));
		list.remove(last);

		if (list.size() > 2)
			pullDown(1);

		return result;
	}

	final T parent(int k) {
		return list.get(k / 2);
	}

	final T left(int k) {
		return list.get(k * 2);
	}

	final T right(int k) {
		return list.get(k * 2 + 1);
	}

	final void swapParent(int k) {
		swap(k, k / 2);
	}

	final void swapLeft(int k) {
		swap(k, 2 * k);
	}

	final void swapRight(int k) {
		swap(k, 2 * k + 1);
	}

	final void swap(int k, int l) {
		T temp = list.get(k);
		list.set(k, list.get(l));
		list.set(l, temp);
	}

	public static <E extends Comparable<? super E>> void sort(E[] array) {
		Heap<E> heap = new Heap<>();
		for (int i = 0; i < array.length; i++) 
			heap.push(array[i]);
		for (int i = 0; i < array.length; i++) 
			array[i] = heap.pop();
	}
	
	public static void main(String[] args) {
//		logger.setLevel(Level.INFO);
		Integer[] arr = new Integer[] { 3, 1, 6, 5, 2, 4, 9, 7, 8};
		Heap<Integer> heap = new Heap<>();
		Random r = new Random();
		 for (int i = 0; i < 20; i++) {
		 heap.push(r.nextInt(30));
		 }
		for (Integer integer : arr) {
			heap.push(integer);
		}
		System.out.println();
		System.out.println(Arrays.deepToString(heap.list.toArray()));
		System.out.println(heap.toTreeString());
		
//		logger.setLevel(Level.ERROR);
		logger.setLevel(Level.INFO);
		while (heap.list.size() > 1) {
			System.out.println(heap.pop());
		}
	}

	@Override
	public String toString() {
		return Arrays.deepToString(list.toArray());
	}

	public String toTreeString() {
		StringBuilder result = new StringBuilder();
		int index = 1;
		int level = 0;
		int levelSize = 1;
		for (int i = 1; i < list.size(); i <<= 1) {
			for (int j = 0; j < levelSize; j++) {
				if(i + j >= list.size())
					break;
				result.append(list.get(i + j) + " ");
			}
			result.append("\n");
			levelSize <<= 1; 
		}
		return result.toString();
	}

}
/*
 * try { throw new ArrayIndexOutOfBoundsException(); } catch
 * (ArrayIndexOutOfBoundsException e) { logger.log(Level.ERROR,
 * "Exception occur", e); }
 */
