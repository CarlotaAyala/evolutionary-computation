package ec.task1.domain;

public class Solution {
    private final double[] x;
    private final double fitness;

    public Solution(double[] x, double fitness) {
        this.x = x;
        this.fitness = fitness;
    }

    public double[] getX() {
        return x;
    }

    public double getFitness() {
        return fitness;
    }
}
