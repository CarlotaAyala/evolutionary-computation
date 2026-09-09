package ec.task1.problems;

import ec.task1.domain.Problem;

public class Ackley extends Problem {
    private Ackley(int d, double[] lb, double[] ub) { super(d, lb, ub, "Ackley"); }

    public static Ackley make(int d) {
        double[] lb = new double[d], ub = new double[d];
        for(int i=0;i<d;i++){
            lb[i]=-32.768;
            ub[i]=32.768;
        }
        return new Ackley(d, lb, ub);
    }

    @Override public double evaluate(double[] x) {
        double s1=0.0, s2=0.0;
        for(double xi: x){
            s1 += xi*xi;
            s2 += Math.cos(2*Math.PI*xi);
        }
        double a = -20.0*Math.exp(-0.2*Math.sqrt(s1/d));
        double b = -Math.exp(s2/d);
        return a + b + 20 + Math.E;
    }
}
