/* this class looses out of seen all necessary classes from main directory after IDE restart
package mp2ng.hw2;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

import mp2.ng.hw.hw2.t7.p1.City;
import mp2.ng.hw.hw2.t7.p1.District;
import mp2.ng.hw.hw2.t7.p1.Region;
import mp2.ng.hw.hw2.t7.p1.State;

public class StateTest {
	static class StackWithSum extends Stack<District>{ 
		private static final long serialVersionUID = 7484795328940043589L;
		double sum;
		@Override
		public synchronized District pop() {
			District temp = super.pop();
			sum += temp.getArea();
			return temp;
		}
	}
	Stack<City> cityPull;
	StackWithSum districtPull;
	Stack<Region> regionPull;
	State state;
	int testArea;
	final int regionCount = 5;
//	List<String> testLocalCapitals = new ArrayList<>();
	String[] testLocalCapitals = new String[regionCount];
	@Before
	public void setUp() throws Exception {
		cityPull = new Stack<>();
		for(int i = 1; i < 200; i++)
			cityPull.push(new City("City" + i));
		
		districtPull = new StackWithSum();
		for(int i = 1; i <= 40; i++)
			districtPull.push(new District("District" + i, cityPull.pop(), i));
		
		regionPull = new Stack<>();
		for(int i = 1; i <= regionCount; i++){
			Region newRegion = new Region("Region" + i, districtPull.pop());
			regionPull.push(newRegion);

			for(int j = 0; j < 4; j++)
				newRegion.addDistrict(districtPull.pop());
		}
		
		state = new State("State", new City("NewCity"));
		
		Region temp;
		for(int i = 1; i <= regionCount; i++){
			temp = regionPull.pop();
			state.addRegion(temp);
			testLocalCapitals[i-1] = temp.getCapitalName();
		}
	}
	
	@Test
	public void testPrintCapital() {
		assertEquals("NewCity", state.getCapital().toString());
	}

	@Test
	public void testPrintQuantityOfRegions() {
		assertEquals(regionCount, state.getLocalsCount());
	}

	@Test
	public void testArea() {
		assertEquals(districtPull.sum, state.getArea(), 0.001);
	}

	@Test
	public void testPrintRegionCenters() {
		org.junit.Assert.assertArrayEquals(testLocalCapitals, state.getLocalCapitals());
	}

}
*/