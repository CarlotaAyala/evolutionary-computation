package ec.task1.problems;

import ec.task1.domain.Problem;

public class Schwefel26 extends Problem {
    private Schwefel26(int d, double[] lb, double[] ub) { super(d, lb, ub, "Schwefel"); }

    public static Schwefel26 make(int d) {
        double[] lb = new double[d], ub = new double[d];
        for (int i = 0; i < d; i++) {
            lb[i] = -500.0;
            ub[i] = 500.0;
        }
        return new Schwefel26(d, lb, ub);
    }

    @Override public double evaluate(double[] x) {
        double sum = 0.0;
        for (double xi : x) {
            sum += xi * Math.sin(Math.sqrt(Math.abs(xi)));
        }
        return -sum;
    }
}
