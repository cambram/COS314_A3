public class Node {
    private double value, weight;

    public Node(double v, double w) {
        this.value = v;
        this.weight = w;
    }

    public Node() {
        this.value = 0.0;
        this.weight = 0.0;
    }

    public Node(double v) {
        this.value = v;
        this.weight = 0.0;
    }

    public double getValue() {
        return this.value;
    }

    public double getWeight() {
        return this.weight;
    }
}
