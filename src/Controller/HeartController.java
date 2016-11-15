package Controller;

import Model.HeartAdapter;
import Model.HeartModel;
import Model.HeartModelInterface;
import View.DJView;
import View.ExchangeDJView;
  
public class HeartController implements ControllerInterface {
	HeartModelInterface model;
	DJView view;
	ExchangeDJView exchangeView;
  
	public HeartController(HeartModelInterface model, int tipo) {
		this.model = model;
		
		if(tipo == 1)
		{
			view = new DJView(this, new HeartAdapter(model));
			view.createView();
			view.createControls();
			view.disableStopMenuItem();
			view.disableStartMenuItem();	
		}
		else //Cuando se permite el cambio de modelo en tiempo de ejecucion, la vista tiene un nuevo menu
		{
			exchangeView = new ExchangeDJView(this, new HeartAdapter(model));
			exchangeView.createView();
			exchangeView.createControls();
			exchangeView.disableStopMenuItem();
			exchangeView.disableStartMenuItem();
		}		
	}
  
	public void start() {
	}
 
	public void stop() {}
    
	public void increaseBPM() 
	{
		HeartModel.getInstance();
	}
    
	public void decreaseBPM() {}
  
 	public void setBPM(int bpm) {}

	@Override
	public void up() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void down() {
		// TODO Auto-generated method stub
		
	}
}



