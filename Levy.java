package ec.task1.problems;

import ec.task1.domain.Problem;

public class Levy extends Problem {
    private Levy(int d, double[] lb, double[] ub) { super(d, lb, ub, "Levy"); }

    public static Levy make(int d) {
        double[] lb = new double[d], ub = new double[d];
        for (int i = 0; i < d; i++) {
            lb[i] = -10.0;
            ub[i] = 10.0;
        }
        return new Levy(d, lb, ub);
    }

    @Override public double evaluate(double[] x) {
        double[] w = new double[d];
        for (int i = 0; i < d; i++) {
            w[i] = 1.0 + (x[i] - 1.0) / 4.0;
        }

        double term1 = Math.sin(Math.PI * w[0]);
               term1 *= term1;

        double sum = 0.0;
        for (int i = 0; i < d - 1; i++) {
            double wi = w[i];
            double t = wi - 1.0;
            double sinPart = Math.sin(Math.PI * wi + Math.PI);
            sum += t*t * (1.0 + 10.0 * sinPart * sinPart);
        }
        double wd = w[d - 1];
        double sin2 = Math.sin(2.0 * Math.PI * wd);
        double term3 = (wd - 1.0) * (wd - 1.0) * (1.0 + sin2 * sin2);

        return term1 + sum + term3;
    }
}
