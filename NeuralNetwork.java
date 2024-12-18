import java.util.LinkedList;

public class NeuralNetwork {
    private Node[] hiddenLayer;
    private Node[] outputNodes;
    private Node[] inputNodes;
    private double bias;

    public NeuralNetwork() {
        inputNodes = new Node[4];
        hiddenLayer = new Node[4];
        outputNodes = new Node[3];
        bias = 0.0;

        for (int i = 0; i < 4; i++) {
            inputNodes[i] = new Node();
            hiddenLayer[i] = new Node();
            if (i < 3) {
                outputNodes[i] = new Node();
            }
        }
    }

    public void clearNodeValues() {
        for (int i = 0; i < 4; i++) {
            inputNodes[i].setValue(0.0);
            hiddenLayer[i].setValue(0.0);
            if (i < 3) {
                outputNodes[i].setValue(0.0);
            }
        }
    }

    public void setInputs(Node[] nodes) {
        for (int i = 0; i < 4; i++) {
            inputNodes[i] = nodes[i];
        }
    }

    public void train(LinkedList<String[]> datapoints) {
        int index = 0, count = 0;
        double sum = 0, sig;
        while (index < datapoints.size()) {
            // Assigning next set of inputs
            for (int i = 1; i < 5; i++) {
                inputNodes[i - 1].setValue(Double.parseDouble(datapoints.get(index)[i]));
            }

            // Calculating n1 and activation for each node in hidden layer
            sum = 0;
            for (int k = 0; k < hiddenLayer.length; k++) { // Loop through hidden layer nodes
                sig = 0.0;
                for (int i = 0; i < inputNodes.length; i++) { // Loop through input nodes to calc
                    for (int j = 0; j < 4; j++) {
                        sum += (inputNodes[j].getWeight()[i] * inputNodes[j].getValue());
                    }
                }
                sum += bias;
                sig = sigmoid(sum);
                hiddenLayer[k].setValue(sig);
            }

            // Calculating output and activation for each node in output nodes
            sum = 0;
            for (int k = 0; k < 3; k++) { // Loop through output nodes
                sig = 0.0;
                for (int i = 0; i < hiddenLayer.length; i++) { // Loop through hidden layer nodes to calc
                    for (int j = 0; j < 3; j++) {
                        sum += (hiddenLayer[j].getWeight()[i] * hiddenLayer[j].getValue());
                    }
                }
                sum += bias;
                sig = sigmoid(sum);
                outputNodes[k].setValue(sig);
            }

            // Output node[0] is L
            // Output node[1] is B
            // Output node[2] is R
            int target = 0;
            switch (datapoints.get(index)[0]) {
                case "L":
                    target = 0;
                    break;
                case "B":
                    target = 1;
                    break;
                case "R":
                    target = 2;
                    break;
            }

            double ErrorTerms[] = new double[7];
            // [0] = OutputNode[0], [1] = OutputNode[1], [2] = OutputNode[2]
            //
            // [3] = HiddenLayer[0], [4] = HiddenLayer[1],
            // [5] = HiddenLayer[2], [6] = HiddenLayer[3]
            for (int i = 0; i < 3; i++) {
                if (i == target) {
                    ErrorTerms[i] = errorCalcOutput(outputNodes[i].getValue(), 1);
                } else {
                    ErrorTerms[i] = errorCalcOutput(outputNodes[i].getValue(), 0);
                }
            }

            // Hiddenlayer calc
            for (int i = 0; i < 4; i++) {
                ErrorTerms[i + 3] = errorCalcHidden(hiddenLayer[i].getValue(),
                        weightedSum(hiddenLayer[i].getWeight(), ErrorTerms[0], ErrorTerms[1], ErrorTerms[2]));
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    hiddenLayer[i].setWeight(
                            delta(hiddenLayer[i].getValue(), ErrorTerms[j], hiddenLayer[i].getWeight()[j]), j);
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    inputNodes[i].setWeight(
                            delta(inputNodes[i].getValue(), ErrorTerms[j], inputNodes[i].getWeight()[j]), j);
                }
            }
            count = calculateAnswer(target, count);
            this.clearNodeValues();
            index++;
        }

        System.out.println("------------------------------ Weights of inputs ------------------------------");
        for (int i = 0; i < 4; i++) {
            System.out.println("Node " + (i + 1));
            for (int j = 0; j < 4; j++) {
                System.out.print(inputNodes[i].getWeight()[j] + "\t");
            }
            System.out.println("");
            System.out.println("");
        }

        System.out.println("------------------------------ Weights of hidden layer ------------------------------");
        for (int i = 0; i < 4; i++) {
            System.out.println("Node " + (i + 1));
            for (int j = 0; j < 3; j++) {
                System.out.print(hiddenLayer[i].getWeight()[j] + "\t");
            }
            System.out.println("");
            System.out.println("");
        }

        System.out.println("------------------------------ Bias ------------------------------");
        System.out.println(bias);
        System.out.println("");

        System.out.println("---------------------- Accuracy(%) of training set ----------------------");
        double c = count, s = datapoints.size();
        double acc = (c / s) * 100;
        System.out.println(acc + "%");
        System.out.println("");
    }

    public void test(LinkedList<String[]> datapoints) {
        int index = 0, count = 0;
        double sum = 0, sig;
        while (index < datapoints.size()) {
            // Assigning next set of inputs
            for (int i = 1; i < 5; i++) {
                inputNodes[i - 1].setValue(Double.parseDouble(datapoints.get(index)[i]));
            }

            // Calculating n1 and activation for each node in hidden layer
            sum = 0;
            for (int k = 0; k < hiddenLayer.length; k++) { // Loop through hidden layer nodes
                sig = 0.0;
                for (int i = 0; i < inputNodes.length; i++) { // Loop through input nodes to calc
                    for (int j = 0; j < 4; j++) {
                        sum += (inputNodes[j].getWeight()[i] * inputNodes[j].getValue());
                    }
                }
                sum += bias;
                sig = sigmoid(sum);
                hiddenLayer[k].setValue(sig);
            }

            // Calculating output and activation for each node in output nodes
            sum = 0;
            for (int k = 0; k < 3; k++) { // Loop through output nodes
                sig = 0.0;
                for (int i = 0; i < hiddenLayer.length; i++) { // Loop through hidden layer nodes to calc
                    for (int j = 0; j < 3; j++) {
                        sum += (hiddenLayer[j].getWeight()[i] * hiddenLayer[j].getValue());
                    }
                }
                sum += bias;
                sig = sigmoid(sum);
                outputNodes[k].setValue(sig);
            }

            int target = 0;
            switch (datapoints.get(index)[0]) {
                case "L":
                    target = 0;
                    break;
                case "B":
                    target = 1;
                    break;
                case "R":
                    target = 2;
                    break;
            }

            count = calculateTestAnswer(target, count);
            this.clearNodeValues();
            index++;
        }

        System.out.println("------------------------ Accuracy(%) of test set ------------------------");
        double c = count, s = datapoints.size();
        double acc = (c / s) * 100;
        System.out.println(acc + "%");
        System.out.println("");
    }

    public double errorCalcOutput(double value, double target) {
        return value * (1 - value) * (target - value);
    }

    public double errorCalcHidden(double value, double sum) {
        return value * (1 - value) * sum;
    }

    public double weightedSum(double[] weight, double zero, double one, double two) {
        return ((weight[0] * zero) + (weight[1] * one) + (weight[2] * two));
    }

    public double delta(double value, double outputErr, double oldWeight) {
        double sum, del;
        del = (1 * outputErr * value);
        sum = del + oldWeight;
        return sum;
    }

    public int calculateAnswer(int target, int count) {
        double max = Double.NEGATIVE_INFINITY;
        int maxIndex = 0;
        for (int i = 0; i < outputNodes.length; i++) {
            if (outputNodes[i].getValue() > max) {
                max = outputNodes[i].getValue();
                maxIndex = i;
            }
        }

        if (target != maxIndex) {
            bias++;
            count++;
        }
        return count;
    }

    public int calculateTestAnswer(int target, int count) {
        double max = Double.NEGATIVE_INFINITY;
        int maxIndex = 0;
        for (int i = 0; i < outputNodes.length; i++) {
            if (outputNodes[i].getValue() > max) {
                max = outputNodes[i].getValue();
                maxIndex = i;
            }
        }

        if (target == maxIndex) {
            count++;
        }
        return count;
    }

    public double sigmoid(double sum) {
        double n = (-1 * sum);
        return (1 / (1 + Math.exp(n)));
    }
}
