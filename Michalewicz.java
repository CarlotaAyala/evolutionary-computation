package ec.task1.problems;

import ec.task1.domain.Problem;

public class Michalewicz extends Problem {
    private final int m;

    private Michalewicz(int d, double[] lb, double[] ub, int m) {
        super(d, lb, ub, "Michalewicz");
        this.m = m;
    }

    public static Michalewicz make(int d) { return make(d, 10); }

    public static Michalewicz make(int d, int m) {
        double[] lb = new double[d], ub = new double[d];
        for (int i = 0; i < d; i++) {
            lb[i] = 0.0;
            ub[i] = Math.PI; }
        return new Michalewicz(d, lb, ub, m);
    }

    @Override public double evaluate(double[] x) {
        double s = 0.0;
        for (int i = 0; i < d; i++) {
            double xi = x[i];
            double a = Math.sin(xi);
            double b = Math.sin(((i + 1) * xi * xi) / Math.PI);
            s += a * Math.pow(b, 2 * m);
        }
        return -s;
    }
}
