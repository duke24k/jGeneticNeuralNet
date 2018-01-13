package ewimberley.ml.ann.gnn.classifier;

import java.util.List;

public class ClassificationGenticNeuralNetworkWorker implements Runnable {

	private ClassificationGenticNeuralNetwork original, mutant;

	private double[][] data;

	private String[] classLabels;

	private List<Integer> trainingIndices;

	private double error;

	public ClassificationGenticNeuralNetworkWorker(ClassificationGenticNeuralNetwork network, double[][] data, String[] labels, List<Integer> trainingIndices) {
		this.original = network;
		this.data = data;
		this.trainingIndices = trainingIndices;
		this.classLabels = labels;
	}

	public void run() {
		mutant = new ClassificationGenticNeuralNetwork(original);
		mutant.mutate();
		double averageOriginalError = 0.0;
		double averageMutantError = 0.0;
		for (Integer trainingIndex : trainingIndices) {
			if (original.getAverageError() == -1.0) {
				//this may be memoized
				averageOriginalError += original.error(data[trainingIndex], classLabels[trainingIndex]);
			}
			averageMutantError += mutant.error(data[trainingIndex], classLabels[trainingIndex]);
		}
		// calculate original average error
		if (original.getAverageError() == -1.0) {
			averageOriginalError /= trainingIndices.size();
			original.setAverageError(averageOriginalError);
		}
//		System.out.println("Original avg error is: " + original.getAverageError());
		// calculate mutant average error
		averageMutantError /= trainingIndices.size();
//		System.out.println("Mutant avg error is: " + averageMutantError);
		mutant.setAverageError(averageMutantError);
	}

	public double getError() {
		return error;
	}

	public ClassificationGenticNeuralNetwork getOriginal() {
		return original;
	}

	public ClassificationGenticNeuralNetwork getMutant() {
		return mutant;
	}

}