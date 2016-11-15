package View;
    
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import Controller.BeatController;
import Controller.BridgeCraneController;
import Controller.ControllerInterface;
import Controller.HeartController;
import Model.BeatModelInterface;
import Model.BridgeCraneAdapter;

public class BridgeCraneView extends DJView implements MatrizObserver {
    JPanel[][] posiciones;
    JButton upBPMButton;
    JButton downBPMButton;
    JLabel positionNumber;
    JFrame paredes;
    int type;
    boolean flag = false;
    
    public BridgeCraneView(ControllerInterface controller, BeatModelInterface model) {	
		
    	super(controller,model);
    	verificar();
    	if(type == 3)
    	model.registerObserver((MatrizObserver)this);	
    }
    
    public void createView() {
    	if(type == 1 || type ==2)
    	{
    		super.createView();
    	}
    	else
    	{
    		JFrame.setDefaultLookAndFeelDecorated(false);
        	viewPanel = new JPanel(new GridLayout(1, 2));
            viewFrame = new JFrame("View");              
            viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            viewFrame.setSize(new Dimension(100, 80));
            bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);
    		beatBar = new BeatBar();
    		beatBar.setValue(0);
            JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
    		bpmPanel.add(beatBar);
            bpmPanel.add(bpmOutputLabel);
            viewPanel.add(bpmPanel);
            viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
            viewFrame.pack();
            viewFrame.setVisible(true);

            viewFrame.setLocation(148,200);

    	}
		
	}
  
    public void createView2()
    {
    	int filas=8;
    	int columnas=8;
    	
    	JFrame.setDefaultLookAndFeelDecorated(false);
    	posiciones = new JPanel[filas][columnas]; 
		JPanel	deposito = new JPanel(new GridLayout(filas,columnas));
		for(int i=0;i<filas;i++)
    		for(int j=0;j<columnas;j++)
    		{
    			posiciones[i][j]=new JPanel(new GridLayout(1,1));
    			posiciones[i][j].setBackground(Color.GRAY);
    			deposito.add(posiciones[i][j]);
    		}
		
		positionNumber = new JLabel("Posicion de la grua: ", SwingConstants.LEFT);
		JSplitPane general = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		general.setBottomComponent(deposito);
		general.setTopComponent(positionNumber);

		
    	paredes = new JFrame("Deposito");
    	paredes.setSize(new Dimension(1000, 800));
    	paredes.getContentPane().add(general, BorderLayout.CENTER);
    	updateMatriz(filas,columnas);
        paredes.pack();
        paredes.setVisible(true);
        paredes.setSize(500, 500);
        paredes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paredes.setLocation(310,200);
    }
    
    public void createControls() {
    	if(type == 1 || type==2)
    	{
    		super.createControls();
    	}
    	else
    	{
    		JFrame.setDefaultLookAndFeelDecorated(false);
            controlFrame = new JFrame("Control");
            controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            controlFrame.setSize(new Dimension(100, 80));

            controlPanel = new JPanel(new GridLayout(1, 2));

            menuBar = new JMenuBar();
            menu = new JMenu("DJ Control");
            
            startMenuItem = new JMenuItem("Start");
            menu.add(startMenuItem);       
            startMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    controller.start();
                }
            });
            
            stopMenuItem = new JMenuItem("Stop");
            menu.add(stopMenuItem);      
            stopMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    controller.stop();
                }
            });
            
            JMenuItem exit = new JMenuItem("Quit");
            exit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    System.exit(0);
                }
            });
            menu.add(exit);
            menuBar.add(menu);
            
            controlFrame.setJMenuBar(menuBar);

            bpmTextField = new JTextField(2);
            bpmLabel = new JLabel("Password:", SwingConstants.RIGHT);
            setBPMButton = new JButton("Set");
            setBPMButton.setSize(new Dimension(10,40));
            increaseBPMButton = new JButton(">>");
            decreaseBPMButton = new JButton("<<");
            upBPMButton = new JButton("^");
            downBPMButton = new JButton("v");
            setBPMButton.addActionListener(this);
            increaseBPMButton.addActionListener(this);
            decreaseBPMButton.addActionListener(this);
            upBPMButton.addActionListener(this);
            downBPMButton.addActionListener(this);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
     
    		buttonPanel.add(decreaseBPMButton);
    		buttonPanel.add(increaseBPMButton);

            JPanel enterPanel = new JPanel(new GridLayout(1, 2));
            enterPanel.add(bpmLabel);
            enterPanel.add(bpmTextField);
            JPanel insideControlPanel = new JPanel(new GridLayout(5, 1));
            insideControlPanel.add(enterPanel);
            insideControlPanel.add(setBPMButton);
            insideControlPanel.add(upBPMButton);
            insideControlPanel.add(buttonPanel);
            insideControlPanel.add(downBPMButton);
            controlPanel.add(insideControlPanel);
            
            bpmLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

            controlFrame.getRootPane().setDefaultButton(setBPMButton);
            controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

            controlFrame.pack();
            controlFrame.setVisible(true);
            
            controlFrame.setLocation(150,269);
    	}
    	
    }

    public void actionPerformed(ActionEvent event) {
		super.actionPerformed(event);
		if (event.getSource() == upBPMButton) {
				controller.up();
		} else if (event.getSource() == downBPMButton) {

				controller.down();
		}
    }

	public void updateBPM() {
		
		super.updateBPM();
		if (model != null) {
			int bpm = model.getBPM();
			if(bpm == -10){
				if (bpmOutputLabel != null) {
        			bpmOutputLabel.setText("Disponible");
				}				
			} else if(bpm == -20){
				if (bpmOutputLabel != null) {
        			bpmOutputLabel.setText("Cargando");
				}
			} 
		}
	}
	
	public void updateMatriz(int filas, int columnas){
		int [][] matriz = new int[filas][columnas];
		BridgeCraneAdapter m = (BridgeCraneAdapter) model;
		matriz = m.getMatriz();
		
		for (int i=0; i<filas; i++)
			for (int j=0; j<columnas; j++)
			{
				if (matriz[i][j]==1)
					{
						posiciones[i][j].setBackground(new Color(255,255,255));
						positionNumber.setText("Posicion de la grua: " + "Fila: " + i + ", Columna: " + j);
					}
				else if (matriz[i][j] == 2)
				{
					posiciones[i][j].setBackground(new Color(90,58,7));
				}
				else if (matriz[i][j] == 3)
				{
					posiciones[i][j].setBackground(Color.RED);
				}
				else if (matriz[i][j] == 4)
				{
					posiciones[i][j].setBackground(Color.GREEN);
				}
				else 
					posiciones[i][j].setBackground(Color.GRAY);			
			}	
	}
	
	public void verificar()
	{
		try
		{
			BeatController b = (BeatController) controller;
			type = 1;
		}
		catch(ClassCastException e){}
	
		try
		{
			HeartController h = (HeartController) controller;
			type = 2;
		}
		catch(ClassCastException r) {}
		
		try
		{
			BridgeCraneController c = (BridgeCraneController) controller;
			type = 3;
		}
		catch(ClassCastException t) {}
		
	}
}
