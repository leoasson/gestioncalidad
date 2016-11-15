package main;

import Controller.BridgeCraneController;
import Controller.ControllerInterface;
import Model.BridgeCraneModel;

public class ExchangableTestDrive {

	public static void main (String[] args) 
	{
		BridgeCraneModel bridgeCraneModel = new BridgeCraneModel();
        ControllerInterface model = new BridgeCraneController(bridgeCraneModel,2);
	}

}
