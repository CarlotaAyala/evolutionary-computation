package ec.task1.problems;

import ec.task1.domain.Problem;

public class Bukin6 extends Problem {
    private Bukin6(double[] lb, double[] ub) { super(2, lb, ub, "BukinN6"); }

    public static Bukin6 make() {
        double[] lb = new double[]{-15.0, -3.0};
        double[] ub = new double[]{ -5.0,  3.0};
        return new Bukin6(lb, ub);
    }

    public static Bukin6 make(int d) {
        if (d != 2) throw new IllegalArgumentException("Bukin needs to be 2D");
        return make();
    }

    @Override public double evaluate(double[] x) {
        double x1 = x[0], x2 = x[1];
        return 100.0 * Math.sqrt(Math.abs(x2 - 0.01 * x1 * x1))
                + 0.01 * Math.abs(x1 + 10.0);
    }
}
