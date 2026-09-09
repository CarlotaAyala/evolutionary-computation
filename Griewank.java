package ec.task1.problems;

import ec.task1.domain.Problem;

public class Griewank extends Problem {
    private Griewank(int d, double[] lb, double[] ub) { super(d, lb, ub, "Griewank"); }

    public static Griewank make(int d) {
        double[] lb = new double[d], ub = new double[d];
        for (int i = 0; i < d; i++) {
            lb[i] = -600.0;
            ub[i] = 600.0; }
        return new Griewank(d, lb, ub);
    }

    @Override public double evaluate(double[] x) {
        double sum = 0.0, prod = 1.0;
        for (int i = 0; i < d; i++) {
            double xi = x[i];
            sum += xi*xi / 4000.0;
            prod *= Math.cos(xi / Math.sqrt(i + 1.0));
        }
        return sum - prod + 1.0;
    }
}