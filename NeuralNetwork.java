import java.util.LinkedList;

public class NeuralNetwork {
    private Node[] hiddenLayer1;
    private Node[] hiddenLayer2;
    private Node[] inputNodes;
    private double bias;

    public NeuralNetwork() {
        inputNodes = new Node[4];
        hiddenLayer1 = new Node[4];
        hiddenLayer2 = new Node[4];
        bias = 0.0;

        for (int i = 0; i < 4; i++) {
            inputNodes[i] = new Node();
            hiddenLayer1[i] = new Node();
            hiddenLayer2[i] = new Node();
        }
    }

    public void clearInputs() {
        for (int i = 0; i < 4; i++) {
            inputNodes[i].setValue(0.0);
        }
    }

    public void setInputs(Node[] nodes) {
        for (int i = 0; i < 4; i++) {
            inputNodes[i] = nodes[i];
        }
    }

    public void train(LinkedList<String[]> datapoints) {
        int index = 0;
        while (index < datapoints.size()) {
            for (int i = 1; i < 5; i++) {
                inputNodes[i - 1].setValue(Double.parseDouble(datapoints.get(index)[i]));
            }
            // calculate n1 then activation
            index++;
        }
    }

}
