package ec.task1.domain;

import java.util.Random;

public class DivineReligionsAlgorithm implements Algorithm {

    private final long seed;
    private final int populationSize;
    private final int numCommunities;
    private final double beliefSelectionProb;
    private final double miracleProb;
    private final double rewardProb;
    private final boolean isDebug;

    public DivineReligionsAlgorithm(long seed,
                                    int populationSize,
                                    int numCommunities,
                                    double beliefSelectionProb,
                                    double miracleProb,
                                    double rewardProb,
                                    boolean isDebug) {
        this.seed = seed;
        this.populationSize = populationSize;
        this.numCommunities = numCommunities;
        this.beliefSelectionProb = beliefSelectionProb;
        this.miracleProb = miracleProb;
        this.rewardProb = rewardProb;
        this.isDebug = isDebug;
    }

    @Override
    public Solution execute(Problem problem, int maxEvaluations) {
        Random rnd = new Random(seed);

        int D = problem.d;
        double[] lb = problem.lb;
        double[] ub = problem.ub;

        Solution[] population = new Solution[populationSize];

        int[] communityOf = new int[populationSize];

        int fes = 0;

        for (int i = 0; i < populationSize; i++) {
            population[i] = problem.generateRandomSolution(rnd);
            communityOf[i] = i % numCommunities;
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

                Solution current = population[i];
                double[] xi = current.getX();
                double[] bestX = bestSolution.getX();

                double[] y = xi.clone();

                if (rnd.nextDouble() <= beliefSelectionProb) {
                    int dIndex = rnd.nextInt(D);
                    y[dIndex] = bestX[dIndex];
                }


                if (rnd.nextDouble() <= miracleProb) {
                    int cIdx = communityOf[i];

                    int dIndex = rnd.nextInt(D);
                    y[dIndex] = lb[dIndex] + rnd.nextDouble() * (ub[dIndex] - lb[dIndex]);

                    int leaderIdx = findLeaderIndex(population, communityOf, cIdx);
                    double[] leaderX = population[leaderIdx].getX();
                    int pIndex = rnd.nextInt(D);
                    y[pIndex] = 0.5 * (y[pIndex] + leaderX[pIndex]);
                    y[pIndex] = clamp(y[pIndex], lb[pIndex], ub[pIndex]);
                }


                if (rnd.nextDouble() <= rewardProb) {
                    int dIndex = rnd.nextInt(D);
                    double range = ub[dIndex] - lb[dIndex];
                    double delta = (rnd.nextDouble() * 2.0 - 1.0) * 0.1 * range;
                    y[dIndex] = clamp(y[dIndex] + delta, lb[dIndex], ub[dIndex]);
                }

                double fy = problem.evaluate(y);
                fes++;

                int comm = communityOf[i];
                int worstIdx = findWorstIndex(population, communityOf, comm);

                if (population[worstIdx] == null ||
                        fy <= population[worstIdx].getFitness()) {

                    population[worstIdx] = new Solution(y, fy);

                    if (bestSolution == null || fy < bestSolution.getFitness()) {
                        bestSolution = population[worstIdx];
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
        Solution best = null;
        for (Solution s : population) {
            if (s == null) continue;
            if (best == null || s.getFitness() < best.getFitness()) {
                best = s;
            }
        }
        return best;
    }

    private int findLeaderIndex(Solution[] population, int[] communityOf, int communityId) {
        int leaderIdx = -1;
        for (int i = 0; i < population.length; i++) {
            if (communityOf[i] != communityId || population[i] == null) continue;
            if (leaderIdx == -1 ||
                    population[i].getFitness() < population[leaderIdx].getFitness()) {
                leaderIdx = i;
            }
        }
        return (leaderIdx == -1) ? 0 : leaderIdx;
    }


    private int findWorstIndex(Solution[] population, int[] communityOf, int communityId) {
        int worstIdx = -1;
        for (int i = 0; i < population.length; i++) {
            if (communityOf[i] != communityId || population[i] == null) continue;
            if (worstIdx == -1 ||
                    population[i].getFitness() > population[worstIdx].getFitness()) {
                worstIdx = i;
            }
        }
        // Por seguridad: si no encuentra ninguno (no debería), devolvemos 0
        return (worstIdx == -1) ? 0 : worstIdx;
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
