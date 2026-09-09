package ec.task1.domain;

import java.util.Random;

public class DifferentialEvolution implements Algorithm {

    private final long seed;
    private final int populationSize;
    private final double cr;
    private final double f;
    private final boolean isDebug;

    public DifferentialEvolution(long seed, int populationSize, double cr, double f, boolean isDebug) {
        this.seed = seed;
        this.populationSize = populationSize;
        this.cr = cr;
        this.f = f;
        this.isDebug = isDebug;
    }

    @Override
    public Solution execute(Problem problem, int maxEvaluations) {
        Random rnd = new Random(seed);

        int D = problem.d;
        double[] lb = problem.lb;
        double[] ub = problem.ub;

        Solution[] population = new Solution[populationSize];

        int fes = 0;
        for (int i = 0; i < populationSize; i++) {
            population[i] = problem.generateRandomSolution(rnd);
            fes++;
            if (fes >= maxEvaluations) {
                return bestFromPopulation(population);
            }
        }

        Solution bestSolution = bestFromPopulation(population);
        if (isDebug) {
            printImprovement(fes, bestSolution.getFitness());
        }

        while (fes < maxEvaluations) {
            for (int i = 0; i < populationSize; i++) {
                double[] xi = population[i].getX();
                int a, b, c;
                do {
                    a = rnd.nextInt(populationSize);
                } while (a == i);
                do {
                    b = rnd.nextInt(populationSize);
                } while (b == i || b == a);
                do {
                    c = rnd.nextInt(populationSize);
                } while (c == i || c == a || c == b);

                double[] xa = population[a].getX();
                double[] xb = population[b].getX();
                double[] xc = population[c].getX();

                double[] v = new double[D];

                for (int j = 0; j < D; j++) {
                    double value = xa[j] + f * (xb[j] - xc[j]);
                    v[j] = clamp(value, lb[j], ub[j]);
                }

                double[] y = new double[D];

                int R = rnd.nextInt(D);

                for (int j = 0; j < D; j++) {
                    double r = rnd.nextDouble();
                    if (r < cr || j == R) {
                        y[j] = v[j];
                    } else {
                        y[j] = xi[j];
                    }
                }
                double fy = problem.evaluate(y);
                fes++;

                double fxi = population[i].getFitness();
                if (fy <= fxi) {
                    population[i] = new Solution(y, fy);
                    if (fy < bestSolution.getFitness()) {
                        bestSolution = population[i];
                        if (isDebug) {
                            printImprovement(fes, bestSolution.getFitness());
                        }
                    }
                }
                if (fes >= maxEvaluations) {
                    break;
                }
            }
        }
        return bestSolution;
    }

    private Solution bestFromPopulation(Solution[] population) {
        Solution best = population[0];
        for (int i = 1; i < population.length; i++) {
            if (population[i] != null &&
                    population[i].getFitness() < best.getFitness()) {
                best = population[i];
            }
        }
        return best;
    }

    private double clamp(double value, double min, double max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }


    private void printImprovement(int fes, double fitness) {
        System.out.printf("FEs: %d, best fitness: %.6e%n", fes, fitness);
    }
}
