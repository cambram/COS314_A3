public class NeuralNetwork {
    private Node[] hiddenLayer1;
    private Node[] hiddenLayer2;
    private Node[] inputNodes;

    public NeuralNetwork() {
        inputNodes = new Node[4];
        hiddenLayer1 = new Node[4];
        hiddenLayer2 = new Node[4];

        for (int i = 0; i < 4; i++) {
            hiddenLayer1[i] = new Node();
            hiddenLayer2[i] = new Node();
        }
    }

    public void setInputs(Node[] nodes) {
        for (int i = 0; i < 4; i++) {
            inputNodes[i] = nodes[i];
        }
    }

}
