package lc.core;

import java.util.List;
import java.util.Random;

import math.util.VectorOps;

public class PerceptronClassifier {
	
	double[] weights;
	public double[] accuracy;
	
	public PerceptronClassifier(double[] weights) {
		this.weights = weights;
	}
	
	public PerceptronClassifier(int ninputs) {
		this.weights = new double[ninputs];
	}
	
	
	/**
	 * Update the weights of this PerceptronClassifier using the given
	 * inputs/output example and learning rate alpha.
	 */
	public void update(double[] x, double y, double alpha){
		for (int i = 0; i < x.length; i++) {
			double wi = weights[i]; 
			double xi = x[i];
			weights[i] = wi + alpha * (y - eval(x)) * xi;	// Follow the update rule
		}
	}

	/**
	 * Threshold the given value using this PerceptronClassifier
	 * threshold function.
	 */
	public double threshold(double z){
		if (z >= 0) {  return 1; }
		return 0;
	}

	/**
	 * Evaluate the given input vector using this PerceptronClassifier
	 * and return the output value.
	 * This value is: Threshold(w dot x)
	 */
	public double eval(double[] x) {
		return threshold(VectorOps.dot(this.weights, x));
	}
	
	/**
	 * Train this PerceptronClassifier on the given Examples for the
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
			accuracy[i] = this.trainingReport(examples, i,
					nsteps);
		}
	}

	/**
	 * Train this PerceptronClassifier on the given Examples for the
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
			accuracy[i] = this.trainingReport(examples, i, nsteps);
		}
	}
	
	/**
	 * This method is called after each weight update during training.
	 * Subclasses can override it to gather statistics or update displays.
	 */
	protected double trainingReport(List<Example> examples, int stepnum, int nsteps) {
		double acc = accuracy(examples);
		System.out.println(stepnum + "\t" + acc);
		return acc;
	}
	
	
	/**
	 * Return the squared error per example for this PerceptronClassifier
	 * using the L2 (squared error) loss function.
	 */
	public double squaredErrorPerSample(List<Example> examples) {
		double sum = 0.0;
		for (Example ex : examples) {
			double result = eval(ex.inputs);
			double error = ex.output - result;
			sum += error*error;
		}
		return sum / examples.size();
	}

	/**
	 * Return the proportion of the given Examples that are classified
	 * correctly by this PerceptronClassifier.
	 * This is probably only meaningful for classifiers that use
	 * a hard threshold. Use with care.
	 */
	public double accuracy(List<Example> examples) {
		int ncorrect = 0;
		for (Example ex : examples) {
			double result = eval(ex.inputs);
			if (result == ex.output) {
				ncorrect += 1;
			}
		}
		return (double)ncorrect / examples.size();
	}

}
