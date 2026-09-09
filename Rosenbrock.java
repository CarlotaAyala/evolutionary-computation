package ec.task1.problems;

import ec.task1.domain.Problem;

public class Rosenbrock extends Problem {
    private Rosenbrock(int d, double[] lb, double[] ub) { super(d, lb, ub, "Rosenbrock"); }

    public static Rosenbrock make(int d) {
        double[] lb = new double[d], ub = new double[d];
        for (int i = 0; i < d; i++) {
            lb[i] = -5.0;
            ub[i] = 10.0;
        }
        return new Rosenbrock(d, lb, ub);
    }

    @Override public double evaluate(double[] x) {
        double s=0.0;
        for(int i=0;i<d-1;i++){
            double a = x[i+1] - x[i]*x[i];
            double b = x[i] - 1.0;
            s += 100.0*a*a + b*b;
        }
        return s;
    }
}
