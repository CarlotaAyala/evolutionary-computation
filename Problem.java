package ec.task1.domain;

import java.util.Random;

public abstract class Problem {
    protected final int d;
    protected final double[] lb;
    protected final double[] ub;
    protected final String name;

    protected Problem(int d, double[] lbound, double[] ubound, String name) {
        this.d = d;
        this.lb = lbound;
        this.ub = ubound;
        this.name = name;
    }

    public abstract double evaluate(double[] x);

    public String getName() {

        return name;
    }

    public double[] randomVector(Random rnd) {
        double[] x = new double[d];
        for (int i = 0; i < d; i++) {
            x[i] = lb[i] + rnd.nextDouble() * (ub[i] - lb[i]);
        }
        return x;
    }

    public Solution generateRandomSolution(Random rnd) {
        double[] x = randomVector(rnd);
        return new Solution(x, evaluate(x));
    }
}
