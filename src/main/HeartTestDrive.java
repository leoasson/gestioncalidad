package main;

import Controller.ControllerInterface;
import Controller.HeartController;
import Model.HeartModel;
  
public class HeartTestDrive {

    public static void main (String[] args) {
		HeartModel heartModel = HeartModel.getInstance();
        ControllerInterface model = new HeartController(heartModel,1);
    }
}
