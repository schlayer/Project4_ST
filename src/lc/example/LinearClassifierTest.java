package lc.example;

import java.io.IOException;
import java.util.List;
import lc.core.Example;
import lc.core.LearningRateSchedule;
import lc.core.LinearClassifier;
import lc.core.PerceptronClassifier;
import lc.display.ClassifierDisplay;
import lc.display.XYPlotCanvas;


public class LinearClassifierTest {

	static String filename = "src/lc/example/earthquake-clean.data.txt";
	static int nsteps = 10000;
	static double alpha = 0.1;

	/**
	 * Train a LinearClassifier on a file of examples and
	 * print its accuracy after each training step.
	 */
	public static void main(String[] argv) throws IOException {
		if (argv.length < 3) {
			System.out.println("usage: java LinearClassifierTest "
					+ "data-filename nsteps alpha");
			System.out.println("       specify alpha=0 to use decaying "
					+ "learning rate schedule (AIMA p725)");
			//System.exit(-1);
			System.out.println("\n\tUsing default values!!!\n");
		}
		else {
			filename = argv[0];
			nsteps = Integer.parseInt(argv[1]);
			alpha = Double.parseDouble(argv[2]);
		}
	
		System.out.println("filename: " + filename);
		System.out.println("nsteps: " + nsteps);
		System.out.println("alpha: " + alpha);
		
		ClassifierDisplay display = new ClassifierDisplay("LinearClassifier: " + filename);
		List<Example> examples = Data.readFromFile(filename);
		
		int ninputs = examples.get(0).inputs.length; 
		
		LinearClassifier classifier = new LinearClassifier(ninputs) ;
		if (alpha > 0) {
			classifier.train(examples, nsteps, alpha);
		} else {
			LearningRateSchedule sch = new LearningRateSchedule();
			classifier.train(examples, 100000, sch);
		}
		
		double[] accuracyReport = classifier.accuracy;
		int steps = accuracyReport.length;
		for (int s = 1; s < steps; s += nsteps/display.getWidth()) {
			display.addPoint(1.0*s/steps, accuracyReport[s]);
		}
		
		
	}

}
