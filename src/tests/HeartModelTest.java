package tests;

import static org.junit.Assert.*;
import Model.HeartModel;
import org.junit.Test;

public class HeartModelTest {

	@Test
	public void singletonTest() 
	{
		HeartModel h1 = HeartModel.getInstance();
		HeartModel h2 = HeartModel.getInstance();
		assertEquals(h1, h2);
	}
	
	@Test
	public void instancesTest() 
	{
		HeartModel h1 = HeartModel.getInstance();
		int inicial = h1.getCuenta();
		HeartModel.getInstance();	
		int fin = h1.getCuenta();
		assertEquals(inicial+1, fin);
	}

}
