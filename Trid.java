package ec.task1.problems;

import ec.task1.domain.Problem;

public class Trid extends Problem {
    private Trid(int d, double[] lb, double[] ub) { super(d, lb, ub, "Trid"); }

    public static Trid make(int d) {
        double[] lb = new double[d], ub = new double[d];
        double bound = d * d;
        for (int i = 0; i < d; i++) {
            lb[i] = -bound;
            ub[i] = bound;
        }
        return new Trid(d, lb, ub);
    }

    @Override public double evaluate(double[] x) {
        double s1 = 0.0, s2 = 0.0;
        for (int i = 0; i < d; i++) s1 += (x[i] - 1.0) * (x[i] - 1.0);
        for (int i = 0; i < d - 1; i++) s2 += x[i] * x[i + 1];
        return s1 - s2;
    }
}
