package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.BridgeCraneModel;

public class DirectionTest 
{

	@Test
	public void goRightTest() 
	{
		BridgeCraneModel bridge = new BridgeCraneModel();
		
		while(bridge.getColumnaActual()==7)
		{
			bridge = new BridgeCraneModel();
		}

		int columnaInicial = bridge.getColumnaActual();
		bridge.irDerecha();
		int columnaFinal = bridge.getColumnaActual();
		
		assertEquals(columnaInicial+1,columnaFinal);
	}
	
	@Test
	public void goLeftTest() 
	{
		BridgeCraneModel bridge = new BridgeCraneModel();
		
		while(bridge.getColumnaActual()==0)
		{
			bridge = new BridgeCraneModel();
		}

		int columnaInicial = bridge.getColumnaActual();
		bridge.irIzquierda();
		int columnaFinal = bridge.getColumnaActual();
		
		assertEquals(columnaInicial-1,columnaFinal);
	}
	
	@Test
	public void goUpTest() 
	{
		BridgeCraneModel bridge = new BridgeCraneModel();
		
		while(bridge.getFilaActual()==0)
		{
			bridge = new BridgeCraneModel();
		}

		int columnaInicial = bridge.getFilaActual();
		bridge.irArriba();
		int columnaFinal = bridge.getFilaActual();
		
		assertEquals(columnaInicial-1,columnaFinal);
	}
	
	@Test
	public void goDownTest() 
	{
		BridgeCraneModel bridge = new BridgeCraneModel();
		
		while(bridge.getFilaActual()==7)
		{
			bridge = new BridgeCraneModel();
		}

		int columnaInicial = bridge.getFilaActual();
		bridge.irAbajo();
		int columnaFinal = bridge.getFilaActual();
		
		assertEquals(columnaInicial+1,columnaFinal);
	}

}
