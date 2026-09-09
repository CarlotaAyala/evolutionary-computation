package ec.task1.domain;
import java.util.Arrays;
import java.util.Random;

public class HillClimbing implements Algorithm {

    private final long seed;
    private final double stepSize;
    private final boolean isDebug;

    public HillClimbing(long seed, double stepSize, boolean isDebug) {
        this.seed = seed;
        this.stepSize = stepSize;
        this.isDebug = isDebug;
    }

    @Override
    public Solution execute(Problem problem, int maxEvaluations) {
        Random rnd = new Random(seed);

        Solution bestSolution = problem.generateRandomSolution(rnd);
        int fes = 1;

        if (isDebug) {
            printImprovement(fes, bestSolution.getX(), bestSolution.getFitness());
        }

        double[] lb = problem.lb;
        double[] ub = problem.ub;

        while (true) {
            double[] currentX = bestSolution.getX();
            int D = currentX.length;

            if (fes + 2 * D > maxEvaluations) {
                break;
            }

            Solution bestNeighbor = bestSolution;
            double bestNeighborFitness = bestNeighbor.getFitness();
            boolean improved = false;

            for (int i = 0; i < D; i++) {

                double[] plusX = currentX.clone();
                plusX[i] = Math.min(ub[i], plusX[i] + stepSize);
                double fPlus = problem.evaluate(plusX);
                fes++;

                if (fPlus < bestNeighborFitness) {
                    bestNeighbor = new Solution(plusX, fPlus);
                    bestNeighborFitness = fPlus;
                    improved = true;
                }


                double[] minusX = currentX.clone();
                minusX[i] = Math.max(lb[i], minusX[i] - stepSize);
                double fMinus = problem.evaluate(minusX);
                fes++;

                if (fMinus < bestNeighborFitness) {
                    bestNeighbor = new Solution(minusX, fMinus);
                    bestNeighborFitness = fMinus;
                    improved = true;
                }
            }

            if (improved) {
                bestSolution = bestNeighbor;
                if (isDebug) {
                    printImprovement(fes, bestSolution.getX(), bestSolution.getFitness());
                }
            } else {
                break;
            }

        }

        return bestSolution;
    }

    private void printImprovement(int evals, double[] x, double f) {
        System.out.printf("%d: x = %s = %.12f%n", evals, Arrays.toString(x), f);
    }
}
