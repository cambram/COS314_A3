import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String args[]) {
        String data = "balance-scale.data";
        String line = "";
        BufferedReader reader = null;
        // Neural network defined
        NeuralNetwork NeuNet = new NeuralNetwork();
        LinkedList<String[]> datapoints = new LinkedList<String[]>();
        try {
            reader = new BufferedReader(new FileReader(data));
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                datapoints.add(split);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Node[] input = new Node[4]; // Node array to temporarily hold inputs
        // Loop through every datapoint in balance-scale
        for (int i = 0; i < datapoints.size(); i++) {
            // Assigning the input data (datapoints.get(i)[0] is the correct class)
            for (int j = 1; j < 5; j++) {
                Node node = new Node(Double.parseDouble(datapoints.get(i)[j]));
                input[j - 1] = node;
            }
            NeuNet.setInputs(input);
            NeuNet.train(datapoints.get(i)[0]);
        }
    }
}