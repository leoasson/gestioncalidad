package Model;

import View.BPMObserver;
import View.BeatObserver;
import View.MatrizObserver;

public class BridgeCraneAdapter implements BeatModelInterface {
	BridgeCraneModelInterface bridgeCrane;
	
	public BridgeCraneAdapter(BridgeCraneModelInterface bridgeCrane){
		this.bridgeCrane = bridgeCrane;
	}

	public void initialize() {}

	public void on() {}

	public void off() {}

	public int getBPM() {
		return bridgeCrane.getBPM();
	}

	public void registerObserver(BeatObserver o) {
		bridgeCrane.registerObserver(o);
	}

	public void removeObserver(BeatObserver o) {
		bridgeCrane.removeObserver(o);
	}

	public void registerObserver(BPMObserver o) {
		bridgeCrane.registerObserver(o);
	}

	public void removeObserver(BPMObserver o) {
		bridgeCrane.removeObserver(o);
	}


	public void registerObserver(MatrizObserver o) {
		bridgeCrane.registerObserver(o);	
	}

	public void removeObserver(MatrizObserver o) {
		bridgeCrane.removeObserver(o);
	}
	
	public int[][] getMatriz(){
		return bridgeCrane.getMatriz();
	}

	public void setBPM(int bpm) 
	{
		bridgeCrane.setBPM(bpm);
		
	}

}

