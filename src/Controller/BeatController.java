package Controller;

import Model.BeatModelInterface;
import View.DJView;
import View.ExchangeDJView;
  
public class BeatController implements ControllerInterface {
	BeatModelInterface model;
	DJView view;
	ExchangeDJView exchangeView;
	int tipo;
   
	public BeatController(BeatModelInterface model, int tipo) {
		this.model = model;
		this.tipo = tipo;
		
		if(tipo == 1)
		{
			view = new DJView(this, model);
	        view.createView();
	        view.createControls();
			view.disableStopMenuItem();
			view.enableStartMenuItem();
		}
		else
		{
			exchangeView = new ExchangeDJView(this, model);
			exchangeView.createView();
			exchangeView.createControls();
			exchangeView.disableStopMenuItem();
			exchangeView.enableStartMenuItem();
		}
		
		model.initialize();
	}
  
	public void start() {
		model.on();
		if(tipo == 1)
		{
			view.disableStartMenuItem();
			view.enableStopMenuItem();	
		}
		else
		{
			exchangeView.disableStartMenuItem();
			exchangeView.enableStopMenuItem();
		}
		
	}
  
	public void stop() {
		model.off();
		if(tipo == 1)
		{
			view.disableStopMenuItem();
			view.enableStartMenuItem();
		}
		else
		{
			exchangeView.disableStopMenuItem();
			exchangeView.enableStartMenuItem();
		}

		
	}
    
	public void increaseBPM() {
        int bpm = model.getBPM();
        model.setBPM(bpm + 1);
	}
    
	public void decreaseBPM() {
        int bpm = model.getBPM();
        
        if(bpm-1 >= 0)	
        model.setBPM(bpm - 1);
  	}
  
 	public void setBPM(int bpm) {
 		if(bpm>=0)	
		model.setBPM(bpm);
	}

	@Override
	public void up() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void down() {
		// TODO Auto-generated method stub
		
	}
}
