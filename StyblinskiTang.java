package ec.task1.problems;

import ec.task1.domain.Problem;

public class StyblinskiTang extends Problem {
    private StyblinskiTang(int d, double[] lb, double[] ub) { super(d, lb, ub, "StyblinskiTang"); }

    public static StyblinskiTang make(int d) {
        double[] lb = new double[d], ub = new double[d];
        for (int i = 0; i < d; i++) {
            lb[i] = -5.0;
            ub[i] = 5.0; }
        return new StyblinskiTang(d, lb, ub);
    }

    @Override public double evaluate(double[] x) {
        double s = 0.0;
        for (double xi : x) {
            s += (Math.pow( xi, 4) - 16.0 * xi * xi + 5.0 * xi) / 2.0;
        }
        return s;
    }
}
