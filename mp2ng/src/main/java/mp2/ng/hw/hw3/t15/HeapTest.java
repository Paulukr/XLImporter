package mp2.ng.hw.hw3.t15;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import mp2.util.HeapCS;

public class HeapTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSort() {
		final int size = 100;
		Random random = new Random();
		Integer[] array = random.ints(size).boxed().toArray(Integer[]::new);
		
		Integer[] expectedArray = Arrays.copyOf(array, array.length);
		Arrays.sort(expectedArray);
		
	      HeapCS<Integer> tmp = new HeapCS<Integer>();
	      Integer[] a = {4,7,7,7,5,0,2,3,5,1};
	      tmp.heapSort(a);
	      
//	      tmp.heapSort(array);
		Heap.sort(array);
		
		
		assertArrayEquals(expectedArray, array);

	      System.out.println(Arrays.toString(a));
	}

}
