package ec.task1.domain;

import java.util.Arrays;
import java.util.Random;

public class RandomSearch implements Algorithm {
    private final long seed;
    private final boolean isDebug;

    public RandomSearch(long seed, boolean isDebug) {
        this.seed = seed;
        this.isDebug = isDebug;
    }

    @Override
    public Solution execute(Problem problem, int maxEvaluations) {
        Random rnd = new Random(seed);

        Solution best = problem.generateRandomSolution(rnd);
        double[] bestX = best.getX();
        double bestF  = best.getFitness();
        int evals = 1;


        while (evals < maxEvaluations) {
            Solution cand = problem.generateRandomSolution(rnd);
            evals++;
            if (cand.getFitness() < bestF) {
                bestX = cand.getX();
                bestF = cand.getFitness();
                if (isDebug) printImprovement(evals, bestX, bestF);
            }
        }
        return new Solution(bestX, bestF);
    }

    private void printImprovement(int evals, double[] x, double f) {
        String vectorStr = Arrays.toString(x).replace('.', ',');
        System.out.printf("%d: x = %s = %.12f%n", evals, vectorStr, f);
    }
}
