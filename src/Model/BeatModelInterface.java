package Model;

import View.BPMObserver;
import View.BeatObserver;
import View.MatrizObserver;
  
public interface BeatModelInterface {
	void initialize();
  
	void on();
  
	void off();
  
    void setBPM(int bpm);
  
	int getBPM();
  
	void registerObserver(BeatObserver o);
  
	void removeObserver(BeatObserver o);
  
	void registerObserver(BPMObserver o);
  
	void removeObserver(BPMObserver o);
	
	void registerObserver(MatrizObserver o);
	
	void removeObserver(MatrizObserver o);

	int[][] getMatriz();
}
