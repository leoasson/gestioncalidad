package Model;

import java.util.*;

import View.BPMObserver;
import View.BeatObserver;

public class HeartModel implements HeartModelInterface, Runnable 
{
	private static HeartModel singleton;
	private static int cuenta;
	
	ArrayList<BeatObserver> beatObservers = new ArrayList<BeatObserver>();
	ArrayList<BPMObserver> bpmObservers = new ArrayList<BPMObserver>();
	int time = 1000;
	int time2 = 1000;
    int bpm = 90;
	Random random = new Random(System.currentTimeMillis());
	Thread thread;

	private HeartModel() {
		thread = new Thread(this);
		thread.start();
	}

	public static HeartModel getInstance()
	{
		if(singleton==null)
			singleton = new HeartModel();
		else
			cuenta++;
		return singleton;
	}
	
	public void clear()
	{
		beatObservers.clear();
		System.out.println(beatObservers.size());
		bpmObservers.clear();
		System.out.println(bpmObservers.size());
	}
	
	public void run() {
			for(;;) {
			notifyBPMObservers();
			
			try {
				Thread.sleep(1);
				time2--;
				if(time2==0)
				{
					int change = random.nextInt(10);
					if (random.nextInt(2) == 0) {
						change = 0 - change;
					}
					int rate = 60000/(time + change);
					if (rate < 120 && rate > 50) {
						time += change;
						notifyBeatObservers();
					}
					time2 = time;
				}
			} catch (Exception e) {}
		}
	}
	
	public int getHeartRate() {
		return 60000/time;
	}
	
	public int getCuenta()
	{
		return cuenta;
	}

	public void registerObserver(BeatObserver o) {
		beatObservers.add(o);
	}

	public void removeObserver(BeatObserver o) {
		int i = beatObservers.indexOf(o);
		if (i >= 0) {
			beatObservers.remove(i);
		}
	}

	public void notifyBeatObservers() {
		for(int i = 0; i < beatObservers.size(); i++) {
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
}
