package ec.task1.problems;

import ec.task1.domain.Problem;

public class Sphere extends Problem {
    private Sphere(int d, double[] lb, double[] ub) { super(d, lb, ub, "Sphere"); }

    public static Sphere make(int d) {
        double[] lb = new double[d], ub = new double[d];
        for (int i = 0; i < d; i++) {
            boolean par_1based = ((i + 1) % 2 == 0);
            if (par_1based) {
                lb[i] = -100.0;
                ub[i] = 100.0;
            } else {
                lb[i] = -10.0;
                ub[i] = 10.0;
            }
        }
        return new Sphere(d, lb, ub);
    }

    @Override public double evaluate(double[] x) {
        double s = 0.0;
        for (double xi : x) s += xi * xi;
        return s;
    }
}
