package lc.core;

public class LearningRateSchedule {
	
	/**
	 * Return the learning rate alpha for the given iteration t.
	 * (AIMA p. 725).
	 */
	public double alpha(int t) {
		return (1000.0/(1000.0 + t));
	}

	public LearningRateSchedule(){
		
	}
	

}
