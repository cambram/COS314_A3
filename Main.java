import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String args[]) {
        String data = "balance-scale.data";
        String line = "";
        BufferedReader reader = null;

        NeuralNetwork NeuNet = new NeuralNetwork();
        LinkedList<String[]> datapoints = new LinkedList<String[]>();

        try {
            reader = new BufferedReader(new FileReader(data));
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                datapoints.add(split);

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}