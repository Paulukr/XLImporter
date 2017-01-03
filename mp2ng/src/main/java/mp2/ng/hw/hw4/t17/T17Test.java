package mp2.ng.hw.hw4.t17;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class T17Test {
	final double precision = 1e-10;
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEval() {

		assertEquals((1+2)*4+5*(3+6), RPN.eval("(1.0+2)*4+5*(3+6)"), precision);
		assertEquals((1.0+2)*4+5*(3+6), RPN.eval("(1.0+2)*4+5*(3+6)"), precision);
		boolean exception = false;
		try {
			RPN.eval("(1.0+2)*4+5*(3+6  we434fesf   )");
		} catch (NumberFormatException e) {
			e.getMessage();
			exception = true;
		}
		assertEquals(true, exception);
		
		exception = false;
		try {
			RPN.eval("");
		} catch (NumberFormatException e) {
			e.getMessage();
			exception = true;
		}
		assertEquals(true, exception);
	}

}
