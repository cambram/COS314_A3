public class Node {
    private double value;
    private double[] weight;

    public Node() {
        this.value = 0.0;
        this.weight = new double[4];
        double[] rng = { -0.5, -0.4, -0.3, -0.2, -0.1, 0.1, 0.2, 0.3, 0.4, 0.5 };
        int k = 0;
        for (int i = 0; i < 4; i++) {
            k = (int) Math.floor(Math.random() * (9 - 0 + 1) + 0);
            this.weight[i] = rng[k];
        }
    }

    public double getValue() {
        return this.value;
    }

    public double[] getWeight() {
        return this.weight;
    }

    public void setValue(double v) {
        this.value = v;
    }

    public void setWeight(double w, int index) {
        this.weight[index] = w;
    }
}
