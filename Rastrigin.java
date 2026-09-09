package ec.task1.problems;

import ec.task1.domain.Problem;

public class Rastrigin extends Problem {
    private Rastrigin(int d, double[] lb, double[] ub) { super(d, lb, ub, "Rastrigin"); }

    public static Rastrigin make(int d) {
        double[] lb = new double[d], ub = new double[d];
        for (int i = 0; i < d; i++) {
            lb[i] = -5.12;
            ub[i] = 5.12;
        }
        return new Rastrigin(d, lb, ub);
    }

    @Override public double evaluate(double[] x) {
        double s = 10.0 * d;
        for (double xi : x){
            s += xi*xi - 10.0 * Math.cos(2 * Math.PI * xi);
        }
        return s;
    }
}
