package lc.core;

import java.util.List;
import java.util.Random;

import math.util.VectorOps;

public class LinearClassifier {
	
	double[] weights;
	public double[] accuracy;
	
	public LinearClassifier(double[] weights) {
		this.weights = weights;
	}
	
	public LinearClassifier(int ninputs) {
		this.weights = new double[ninputs];
	}
	
	
	/**
	 * Update the weights of this LinearClassifer using the given
	 * inputs/output example and learning rate alpha.
	 */
	public void update(double[] x, double y, double alpha){ 
		for (int i = 0; i < x.length; i++) {
			double wi = weights[i]; 
			double xi = x[i];							// Follow the update rule
			weights[i] = wi + alpha*(y - eval(x)) * eval(x)*(1.0 - eval(x)) * xi;	
			//System.out.printf("%.5f\n", weights[i]);
			
		}
	}
	

	/**
	 * Evaluate the given input vector using this LinearClassifier
	 * and return the output value.
	 * This value is: L(w dot x)
	 */
	public double eval(double[] x) {
		double z = VectorOps.dot(this.weights, x); 		// z is still w dot x
		double log = 1.0 / (1.0 + Math.exp(-1 * z) ); 	// 1 / (1 + e^-z)

		return log;
		
	}
	
	/**
	 * Train this LinearClassifier on the given Examples for the
	 * given number of steps, using given learning rate schedule.
	 * ``Typically the learning rule is applied one example at a time,
	 * choosing examples at random (as in stochastic gradient descent).''
	 * See AIMA p. 724.
	 */
	public void train(List<Example> examples, int nsteps, LearningRateSchedule schedule) {
		Random random = new Random();
		int n = examples.size();
		this.accuracy = new double[nsteps+1];
		
		for (int i=1; i <= nsteps; i++) {
			int j = random.nextInt(n);
			Example ex = examples.get(j);
			
			this.update(ex.inputs, ex.output, schedule.alpha(i));
			accuracy[i] = this.trainingReport(examples, i, nsteps);
		}
	}

	/**
	 * Train this LinearClassifier on the given Examples for the
	 * given number of steps, using given constant learning rate.
	 */
	public void train(List<Example> examples, int nsteps, double constant_alpha) {
		Random random = new Random();
		int n = examples.size();
		this.accuracy = new double[nsteps+1];
		
		for (int i=1; i <= nsteps; i++) {
			int j = random.nextInt(n);
			Example ex = examples.get(j);
			
			this.update(ex.inputs, ex.output, constant_alpha);
			accuracy[i] = this.trainingReport(examples, i,  nsteps);
		}
	}
	
	/**
	 * This method is called after each weight update during training.
	 * Subclasses can override it to gather statistics or update displays.
	 */
	protected double trainingReport(List<Example> examples, int stepnum, int nsteps) {
		double acc = squaredErrorPerSample(examples);
		System.out.println(stepnum + "\t" + acc);
		return acc;
	}
	
	
	/**
	 * Return the squared error per example for this Linear classifier
	 * using the L2 (squared error) loss function.
	 */
	public double squaredErrorPerSample(List<Example> examples) {
		double sum = 0.0;
		for (Example ex : examples) {
			double result = eval(ex.inputs);
			double error = ex.output - result;
			sum += error*error;
		}
		return 1- (sum / examples.size());
	}

	

}
