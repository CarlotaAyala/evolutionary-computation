package ec.task1.problems;

import ec.task1.domain.Problem;

public class CarromTable extends Problem {
    private CarromTable(double[] lb, double[] ub) { super(2, lb, ub, "CarromTable"); }

    public static CarromTable make() {
        double[] lb = new double[]{-10.0, -10.0};
        double[] ub = new double[]{ 10.0,  10.0};
        return new CarromTable(lb, ub);
    }

    public static CarromTable make(int d) {
        if (d != 2) throw new IllegalArgumentException("Carrom Table needs to be 2D");
        return make();
    }

    @Override public double evaluate(double[] x) {
        double r = Math.sqrt(x[0]*x[0] + x[1]*x[1]);
        double absTerm = Math.abs(1.0 - r / Math.PI);
        double expTerm = Math.pow(Math.E, 2.0 * absTerm);
        double c = Math.cos(x[0]) * Math.cos(x[1]);
        double cos2 = c * c;
        return -expTerm * cos2 / 30.0;
    }
}
