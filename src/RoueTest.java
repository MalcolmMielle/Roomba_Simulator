import static org.junit.Assert.*;

import org.junit.Test;


public class RoueTest {

	@Test
	public void testGet() {
		Roue r1 =new Roue();
		assertEquals (r1.getDistance(), 0, 0.000001);	
	}
	
	public void testSet(){
		Roue r1=new Roue();
		r1.setDistance(11.2);
		assertEquals (r1.getDistance(), 11.2,0.0000001);
	}

	@Test
	public void testClone() {
		Roue r = new Roue();
		r.setDistance(12);
		Roue r2 = (Roue) r.clone();
		System.out.print(r2.getDistance());
		assertTrue(r2.getDistance()==12);
		assertTrue(r2.getClass()==r.getClass());
		assertFalse(r == r2);
	}

}
