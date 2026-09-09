package ec.task1.domain;
import java.util.Arrays;
import java.util.Random;

public class ImprovedHillClimbing implements Algorithm {

    private final long seed;
    private final double StepSize;
    private final boolean isDebug;
    private final double minStepSize;
    private final double stepReductionFactor;

    public ImprovedHillClimbing(long seed, double StepSize, boolean isDebug, double minStepSize, double stepReductionFactor) {
        this.seed = seed;
        this.StepSize = StepSize;
        this.isDebug = isDebug;
        this.minStepSize = minStepSize;
        this.stepReductionFactor = stepReductionFactor;
    }

    @Override
    public Solution execute(Problem problem, int maxEvaluations) {
        Random rnd = new Random(seed);

        Solution bestSolution = problem.generateRandomSolution(rnd);
        int fes = 1;

        if (isDebug) {
            printImprovement(fes, bestSolution.getX(), bestSolution.getFitness());
        }

        double currentStep = StepSize;

        double[] lb = problem.lb;
        double[] ub = problem.ub;

        while (currentStep >= minStepSize) {
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
                plusX[i] = Math.min(ub[i], plusX[i] + currentStep);
                double fPlus = problem.evaluate(plusX);
                fes++;

                if (fPlus < bestNeighborFitness) {
                    bestNeighbor = new Solution(plusX, fPlus);
                    bestNeighborFitness = fPlus;
                    improved = true;
                }

                double[] minusX = currentX.clone();
                minusX[i] = Math.max(lb[i], minusX[i] - currentStep);
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
                currentStep *= stepReductionFactor;
            }

        }

        return bestSolution;
    }

    private void printImprovement(int evals, double[] x, double f) {
        System.out.printf("%d: x = %s = %.12f%n", evals, Arrays.toString(x), f);
    }
}
