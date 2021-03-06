package Model;

import java.util.ArrayList;
import java.util.Random;

import View.BPMObserver;
import View.BeatObserver;
import View.MatrizObserver;

public class BridgeCraneModel implements BridgeCraneModelInterface, Runnable {
	
	ArrayList<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
	ArrayList<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
	ArrayList<MatrizObserver> matrizObservers = new ArrayList<MatrizObserver>();
	int bpm = -10;
	int [][] matriz;
	int filas = 8;
	int columnas = 8;
	int variable_a;
	int variable_b;
	int contador = 10;
	Thread thread;

	public BridgeCraneModel () 
	{
		matriz = new int [filas][columnas];
		inicializarMatriz();
		thread = new Thread(this);
		thread.start();
	}

	public void run()
	{

		for( ; ; )
		{
			try
			{
				notifyBPMObservers();
				notifyMatrizObserver();
				
			}
			catch(NullPointerException e)
			{
				e.printStackTrace();
			};

			try
			{
				Thread.sleep(50);
				contador--;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			};
			
			if(contador == 0)
			{
				if(bpm == -20)
				 {
					notifyBeatObservers();
				 }
				contador = 10;
			}
		}
	}
	
	public void clear()
	{
		beatObservers.clear();
		bpmObservers.clear();
	}
	
	
	public int[][] getMatriz()
	{
		return matriz;
	}
	
	public void registerObserver(BeatObserver o) {
		beatObservers.add(o);
	}

	public void removeObserver(BeatObserver o)
	{
		int i = beatObservers.indexOf(o);
		if (i >= 0) 
		{
			beatObservers.remove(i);
		}
	}

	public void notifyBeatObservers() 
	{
		for(int i = 0; i < beatObservers.size(); i++) 
		{
			BeatObserver observer = (BeatObserver)beatObservers.get(i);
			observer.updateBeat();
		}
	}

	public void registerObserver(BPMObserver o) {
		bpmObservers.add(o);
	}

	public void removeObserver(BPMObserver o) {
		int i = bpmObservers.indexOf(o);
		if (i >= 0) {
			bpmObservers.remove(i);
		}
	}
	
	public void notifyBPMObservers() {
		for(int i = 0; i < bpmObservers.size(); i++) {
			BPMObserver observer = (BPMObserver)bpmObservers.get(i);
			observer.updateBPM();
		}
	}
	
	public void registerObserver(MatrizObserver o)
	{
		matrizObservers.add(o);
	}
	
	public void removeObserver(MatrizObserver o)
	{
		int i = matrizObservers.indexOf(o);
		if (i >= 0) {
			matrizObservers.remove(i);
		}
	}

	public void notifyMatrizObserver()
	{
		for(int i = 0; i < matrizObservers.size(); i++)
		{
			MatrizObserver observer = (MatrizObserver)matrizObservers.get(i);
			observer.updateMatriz(filas,columnas);
		}
	}
	
	public void inicializarMatriz()
	{
		Random random=new Random();
		variable_a = 3;//a=random.nextInt(8);
		variable_b = 3;//b=random.nextInt(8);
		
		for (int i=0; i<filas; i++)
			for (int j=0; j<columnas; j++)
			{
				if (i == variable_a && j == variable_b)
					matriz[i][j] = 1;			
				else 
					matriz[i][j] = 0;				
			}
		
		matriz[0][0] = 2;//matriz[c][d] = 2;
		matriz[3][7] = 2;//matriz[e][f] = 2;
		matriz[7][1] = 2;//matriz[g][h] = 2;
		matriz[7][7] = 2;//matriz[e][g] = 2;
		matriz[3][5] = 2;//matriz[f][c] = 2;
		matriz[6][2] = 2;//matriz[e][d] = 2;
	} 
	
	public void cambiarMatriz()
	{
		for (int i = 0; i < filas; i++)
			for (int j = 0; j < columnas; j++)
			{
				if (matriz[i][j] == 3)
					matriz[i][j] = 2;	
				else if (matriz[i][j] != 2 && matriz[i][j] != 4 && matriz[i][j] != 5 )
					matriz[i][j] = 0;		
				else if(matriz[i][j] == 4 && (i != variable_a || j != variable_b))
				{
					matriz[i][j] = 0;
					matriz[variable_a][variable_b] = 4;
				}
			}
		
		if(matriz[variable_a][variable_b] == 0)
		matriz[variable_a][variable_b] = 1;
		else if(matriz[variable_a][variable_b] == 3)
		matriz[variable_a][variable_b] = 2;
		else if(matriz[variable_a][variable_b] != 4)
		matriz[variable_a][variable_b] = 3;	
		else if(matriz[variable_a][variable_b] == 5)
		matriz[variable_a][variable_b] = 3;	
	}
	
	
	public void irDerecha()
	{
		if(variable_b != columnas-1 && bpm != -20)
		{
			if((matriz[variable_a][variable_b+1] == 2 && matriz[variable_a][variable_b] == 4) == false)
				variable_b++;
		}
		cambiarMatriz();
	}
	
	public void irIzquierda()
	{
		if(variable_b != 0 && bpm != -20)
		{
			if((matriz[variable_a][variable_b-1] == 2 && matriz[variable_a][variable_b] == 4) == false)
				variable_b--;
		}
		cambiarMatriz();
	}
	
	public void irArriba()
	{
		if(variable_a != 0 && bpm != -20)
		{
			if((matriz[variable_a-1][variable_b] == 2 && matriz[variable_a][variable_b] == 4) == false)
				variable_a--;
		}
		cambiarMatriz();
	}
	
	public void irAbajo()
	{
		if(variable_a!=filas-1 && bpm != -20)
		{
			if((matriz[variable_a+1][variable_b] == 2 && matriz[variable_a][variable_b] == 4) == false)
				variable_a++;
		}
		cambiarMatriz();
	}

	public void setBPM(int i) 
	{
		bpm = i;
		if(bpm == -20 && matriz[variable_a][variable_b] == 3)
		{
			matriz[variable_a][variable_b] = 4;
		}
		else if(bpm == -20 && matriz[variable_a][variable_b] == 4)
		{
			matriz[variable_a][variable_b] = 5;
		}
		cambiarMatriz();
	}

	public int getBPM() 
	{
		return bpm;
	}
	
	public int getFilaActual()
	{
		return variable_a;
	}
	
	public int getColumnaActual()
	{
		return variable_b;
	}

}
