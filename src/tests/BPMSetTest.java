package tests;

import static org.junit.Assert.*;
import Model.BridgeCraneModel;

import org.junit.Test;

public class BPMSetTest 
{

	@Test
	public void test() 
	{
		int expected = 50;
		BridgeCraneModel bridge = new BridgeCraneModel();
		bridge.setBPM(expected);
		int real = bridge.getBPM();		
		assertEquals(expected, real);
	}

}
