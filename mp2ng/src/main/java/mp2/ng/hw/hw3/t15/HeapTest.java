package mp2.ng.hw.hw3.t15;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

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

		Heap.sort(array);
			
		assertArrayEquals(expectedArray, array);

	}

}
