package main;

import Controller.BeatController;
import Controller.ControllerInterface;
import Model.BeatModel;
import Model.BeatModelInterface;
  
public class DJTestDrive {

    public static void main (String[] args) {
        BeatModelInterface model = new BeatModel();
		ControllerInterface controller = new BeatController(model,1);
    }
}
