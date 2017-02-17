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


/*
 * SAX parser Simple API for XML
 *  event model
 *   5 events
 *  start document
 *  end document
 *  open tag
 *  close tag
 *  startDocument, endDocument, startElement, and endElement
 *  
 *  
 *  xlink type = "extended"
 *  
 *  https://docs.oracle.com/database/121/ADXDK/adx_j_xqj.htm#ADXDK99930
 *   xquery tool for generation java classes from xsd file for xml parsing
 *   
 *   marshaling JAXBContext
 */

public class Heap<T extends Comparable<? super T>> {

	private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Heap.class.getName());
	static{
		logger.setLevel(Level.ERROR);
	}
	private List<T> list = new ArrayList<>();
	{
		list.add(null);
	}
	
	public T get(int i){
		return list.get(i);
	}
	
	public int size() {
		return list.size() - 1;
	}
	
	public boolean isEmpty() {
		if (list.size() > 1) 		
			return true;
		return false;
	}

	public T peek() {
		if(list.size() > 1)
			return get(1);
		return null;
	}
	public void push(T element) {
		logger.log(Level.INFO, "\n add: " + element);
		list.add(element);
		int k = list.size() - 1;
		pullUp(k);
	}

	private void pullUp(int k) {
		logger.log(Level.INFO, "pullup:");
		T current = list.get(k);
		logger.log(Level.INFO, toString());
		while (k > 1 && current.compareTo(parent(k)) < 0) {
			swapParent(k);
			k >>= 1;
			logger.log(Level.INFO, toString());
		}

	}

	private void pullDown(int k) {
		int current = k;
		T value = list.get(k);
		for (int branch = k << 1; branch < list.size(); current = branch, branch <<= 1) {

			if (branch + 1 < list.size() && (get(branch).compareTo(get(branch + 1)) > 0)) // "left branch > right branch"
				branch++;// then right is target, else left

			if (value.compareTo(get(branch)) > 0)
				swap(current, branch);
			else
				break;
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
	
	@SuppressWarnings("unchecked")
	public T[] toArray(T[] a) {
		if (a.length < size())
			a = (T[]) new Object[size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = pop();
		}
		return a;
	}
	
	private final T parent(int k) {
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
//		logger.setLevel(Level.INFO);
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
