package ec.task1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import ec.task1.domain.*;
import ec.task1.problems.*;

public class Main {

    private static final String SURNAME = "AyalaPerez";

    public static void main(String[] args) throws IOException {

        int runs = 50;
        int[] dims = new int[]{10, 20, 30};

        int populationSize = 50;
        int numCommunities = 5;
        double BPSP = 0.7;
        double MP = 0.3;
        double RP = 0.5;

        for (int d : dims) {

            runDRAForProblem(Sphere.make(d), d, runs, populationSize, numCommunities, BPSP, MP, RP);
            runDRAForProblem(Ackley.make(d), d, runs, populationSize, numCommunities, BPSP, MP, RP);
            runDRAForProblem(Griewank.make(d), d, runs, populationSize, numCommunities, BPSP, MP, RP);
            runDRAForProblem(Rastrigin.make(d), d, runs, populationSize, numCommunities, BPSP, MP, RP);
            runDRAForProblem(Schwefel26.make(d), d, runs, populationSize, numCommunities, BPSP, MP, RP);
            runDRAForProblem(Rosenbrock.make(d), d, runs, populationSize, numCommunities, BPSP, MP, RP);
            runDRAForProblem(Trid.make(d), d, runs, populationSize, numCommunities, BPSP, MP, RP);
            runDRAForProblem(StyblinskiTang.make(d), d, runs, populationSize, numCommunities, BPSP, MP, RP);
            runDRAForProblem(Levy.make(d), d, runs, populationSize, numCommunities, BPSP, MP, RP);
            runDRAForProblem(Michalewicz.make(d), d, runs, populationSize, numCommunities, BPSP, MP, RP);

        }

        System.out.println("Finished all DRA runs. Check the generated .txt files.");
    }

    private static void runDRAForProblem(Problem problem,
                                         int d,
                                         int runs,
                                         int populationSize,
                                         int numCommunities,
                                         double bpsp,
                                         double mp,
                                         double rp) throws IOException {

        String name = problem.getName();
        double[] results = new double[runs];
        int maxFes = 3000 * d;

        for (int i = 0; i < runs; i++) {
            long seed = System.nanoTime();

            Algorithm dra = new DivineReligionsAlgorithm(
                    seed,
                    populationSize,
                    numCommunities,
                    bpsp,
                    mp,
                    rp,
                    false
            );

            Solution best = dra.execute(problem, maxFes);
            results[i] = best.getFitness();
        }

        String fileName = String.format("DRA-%s_%sD%d.txt", SURNAME, name, d);

        writeResultsToFile(fileName, results);
    }

    private static void writeResultsToFile(String fileName, double[] results) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (double v : results) {
                bw.write(String.format(Locale.US, "%.20f", v));
                bw.newLine();
            }
        }
        System.out.println("Written file: " + fileName);
    }
}
