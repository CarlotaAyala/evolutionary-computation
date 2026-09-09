package ec.task1.problems;

import ec.task1.domain.Problem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProblemKnownOptimaTest {

    private static final double TOL = 1e-7;

    @Test @DisplayName("Sphere")
    void sphereAtOrigin() {
        for (int d : new int[]{2,5,10}) {
            Problem p = Sphere.make(d);
            double[] x = new double[d];
            assertEquals(0.0, p.evaluate(x), TOL);
        }
    }

    @Test @DisplayName("Ackley")
    void ackleyAtOrigin() {
        for (int d : new int[]{2,5,10}) {
            Problem p = Ackley.make(d);
            double[] x = new double[d];
            assertEquals(0.0, p.evaluate(x), 1e-6);
        }
    }

    @Test @DisplayName("Griewank")
    void griewankAtOrigin() {
        for (int d : new int[]{2,5,10}) {
            Problem p = Griewank.make(d);
            double[] x = new double[d];
            assertEquals(0.0, p.evaluate(x), TOL);
        }
    }

    @Test @DisplayName("Rastrigin")
    void rastriginAtOrigin() {
        for (int d : new int[]{2,5,10}) {
            Problem p = Rastrigin.make(d);
            double[] x = new double[d];
            assertEquals(0.0, p.evaluate(x), TOL);
        }
    }

    @Test @DisplayName("Rosenbrock")
    void rosenbrockAtOnes() {
        for (int d : new int[]{2,5,10}) {
            Problem p = Rosenbrock.make(d);
            double[] x = new double[d];
            for (int i=0;i<d;i++) x[i] = 1.0;
            assertEquals(0.0, p.evaluate(x), TOL);
        }
    }

    @Test @DisplayName("Levy")
    void levyAtOnes() {
        for (int d : new int[]{2,5,10}) {
            Problem p = Levy.make(d);
            double[] x = new double[d];
            for (int i=0;i<d;i++) x[i] = 1.0;
            assertEquals(0.0, p.evaluate(x), TOL);
        }
    }

    @Test @DisplayName("StyblinskiTang")
    void styblinskiTangAtOptimum() {
        for (int d : new int[]{2,5,10}) {
            Problem p = StyblinskiTang.make(d);
            double[] x = new double[d];
            for (int i=0;i<d;i++) x[i] = -2.90353401818596;
            double expected = -39.16616570377142 * d;
            assertEquals(expected, p.evaluate(x), 1e-6);
        }
    }

    @Test @DisplayName("Trid")
    void tridAtAnalyticMinimum() {
        for (int d : new int[]{2,5,10}) {
            Problem p = Trid.make(d);
            double[] x = new double[d];
            for (int i=0;i<d;i++) x[i] = (i+1) * (d - i);
            double fstar = - (d * (d + 4.0) * (d - 1.0)) / 6.0;
            assertEquals(fstar, p.evaluate(x), 1e-6);
        }
    }

    @Test @DisplayName("Schwefel")
    void schwefelAtNearOptimum() {
        for (int d : new int[]{2,5,10}) {
            Problem p = Schwefel26.make(d);
            double[] x = new double[d];
            for (int i=0;i<d;i++) x[i] = 420.968746;
            assertEquals(-418.982887272433799807913601398 * d, p.evaluate(x), TOL);
        }
    }

    @Test @DisplayName("Bukin6 (2D)")
    void bukin6AtOptimum() {
        Problem p = Bukin6.make(2);
        double[] x = new double[]{-10.0, 1.0};
        assertEquals(0.0, p.evaluate(x), TOL);
    }

    @Test @DisplayName("Carrom Table (2D)")
    void carromTableBasic() {
        Problem p = CarromTable.make(2);
        double[] x = new double[]{Math.PI, 0.0};
        double v = p.evaluate(x);
        assertTrue(Double.isFinite(v));
        assertTrue(v <= 0.0);
    }
}
