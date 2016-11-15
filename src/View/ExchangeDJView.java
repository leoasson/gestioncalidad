package View;
    
import java.awt.event.*;

import javax.swing.*;

import Controller.BeatController;
import Controller.BridgeCraneController;
import Controller.ControllerInterface;
import Controller.HeartController;
import Model.BeatModel;
import Model.BeatModelInterface;
import Model.BridgeCraneModel;
import Model.HeartModel;

public class ExchangeDJView extends BridgeCraneView 
{
    public ExchangeDJView(ControllerInterface controller, BeatModelInterface model) 
    {	
		super(controller,model);
    }
    
    public void createView() 
    {
    	super.createView();
        JMenuBar select = createMenu();
        viewFrame.setJMenuBar(select);
        viewFrame.pack();
	}
    
    public void createControls() 
    {
    	super.createControls();
    	if(type == 3)
        controlFrame.setLocation(150,292);

	}
    

	public JMenuBar createMenu()
	{
        JMenuBar select = new JMenuBar();
        JMenu menuSelect = new JMenu("Model");
        
        JMenuItem model1 = new JMenuItem("DJ");
        
        if(type==1)
        	model1.setEnabled(false);
        
        menuSelect.add(model1);       
        model1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
            {
				if(type!=1)
				{
					if(type == 3)
					{
						JFrame p = getGalpon();
						p.setVisible(false);
					}	
					ExchangeModel(1);
					JFrame b = getBar();
					JFrame c = getControl();
					b.setVisible(false);
					c.setVisible(false);
					model.off();
										
				}
            }
        });
        
        JMenuItem model2 = new JMenuItem("Heart");
        
        if(type==2)
        	model2.setEnabled(false);
        
        menuSelect.add(model2);      
        model2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				if(type!=2)
				{
					if(type == 3)
					{
						JFrame p = getGalpon();
						p.setVisible(false);
					}	
					ExchangeModel(2);
					JFrame b = getBar();
					JFrame c = getControl();
					b.setVisible(false);
					c.setVisible(false);									
					model.off();
					
			        
				}
            }
        });
        
        JMenuItem model3 = new JMenuItem("BridgeCrane");
        
        if(type==3)
        	model3.setEnabled(false);
        
        menuSelect.add(model3);
        model3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) 
			{
				if(type!=3)
				{
					ExchangeModel(3);
					JFrame b = getBar();
					JFrame c = getControl();
					b.setVisible(false);
					c.setVisible(false);
					model.off();
									}
            }
        });
        
        select.add(menuSelect);
        
        return select;
	}
	
	public JFrame getBar()
	{
		return viewFrame;
	}
	
	public JFrame getControl()
	{
		return controlFrame;
	}
	
	public JFrame getGalpon()
	{
		return paredes;
	}
	
	public void ExchangeModel(int a)
	{
		if(a==1)
		{
			BeatModel beatModel1 = new BeatModel();
			ControllerInterface c1 = new BeatController(beatModel1,2);
		}
		else if(a==2)
		{
			HeartModel heartModel1 = HeartModel.getInstance();
	        ControllerInterface c2 = new HeartController(heartModel1,2);
		}
		else if(a==3)
		{
			BridgeCraneModel bridgeCraneModel = new BridgeCraneModel();
	        ControllerInterface c3 = new BridgeCraneController(bridgeCraneModel,2);
		}
	}
}
