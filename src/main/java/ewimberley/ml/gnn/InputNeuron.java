package ewimberley.ml.gnn;

import java.text.DecimalFormat;

public class InputNeuron<H> extends Neuron<H> {

	private double input;
	
	public InputNeuron(NeuralNetwork<H> network, InputNeuron<H> toClone) {
		super(network, toClone);
		this.input = toClone.getInput();
	}
	
	public InputNeuron(NeuralNetwork<H> network) {
		super(network);
	}

	@Override
	public double activation() {
		return input;
	}

	public double getInput() {
		return input;
	}

	public void setInput(double input) {
		this.input = input;
	}
	
	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(Neuron.NUM_DECIMALS_TO_STRING);
		String output = "Input neuron " + Neuron.truncatedUUID(getUuid()) + " and next [";
		boolean first = true;
		for (String nextId : next) {
			if (!first) {
				output += ", ";
			} else {
				first = false;
			}
			output += truncatedUUID(nextId) + "::" + df.format(nextWeights.get(nextId));
		}
		output += "]\t<-\t" + input;
		return output;
	}

}
