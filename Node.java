public class Node {
    private double value, weight;

    public Node(double v, double w) {
        this.value = v;
        this.weight = w;
    }

    public Node() {
        this.value = 0.0;
        double[] rng = { -0.5, -0.4, -0.3, -0.2, -0.1, 0.1, 0.2, 0.3, 0.4, 0.5 };
        int i = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
        this.weight = rng[i];
    }

    public double getValue() {
        return this.value;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setValue(double v) {
        this.value = v;
    }

    public void setWeight(double w) {
        this.weight = w;
    }
}
