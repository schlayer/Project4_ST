package math.util;

public class VectorOps {
	
	/**
	 * Return dot-product of vectors x and y:
	 * x dot y = sum_i x[i]*y[i]
	 */
	static public double dot(double[] x, double[] y) {
		double sum = 0.0;
		for (int i=0; i < x.length; i++) {
			sum += x[i]*y[i];
		}
		return sum;
	}

	/**
	 * Return cross-product of vectors x and y:
	 * x cross y = sum_i x[i]*y[i]
	 
	static public double cross(double[] x, double[] y) {
		double sum = 0.0;
		for (int i=0; i < x.length; i++) {
			sum += x[i]*y[i];
		}
		return sum;
	}
	*/
	
}
