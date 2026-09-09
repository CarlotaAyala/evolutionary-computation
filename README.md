# 🧬 Evolutionary Computation

Implementation and comparison of several optimization algorithms in **Java**, including Random Search, Hill Climbing, Differential Evolution and the Divine Religions Algorithm.

The project evaluates these methods on a collection of well-known benchmark optimization functions and studies their performance across different problem dimensions.

---

## 🎯 Project Overview

The objective of this project is to explore different optimization strategies, starting from simple search methods and progressing toward more advanced population-based metaheuristics.

The repository includes implementations of:

- Random Search
- Hill Climbing
- Improved Hill Climbing
- Differential Evolution
- Divine Religions Algorithm

These algorithms are evaluated using benchmark functions such as Ackley, Rastrigin, Rosenbrock, Sphere, Griewank and others.

The final version of the project focuses mainly on the **Divine Religions Algorithm (DRA)** and evaluates its performance in dimensions 10, 20 and 30.

---

## ⚙️ Algorithms Implemented

### Random Search

Random Search generates candidate solutions randomly inside the search space and keeps the best solution found.

It provides a simple baseline to compare against more advanced optimization methods.

### Hill Climbing

Hill Climbing starts from an initial solution and iteratively moves toward neighboring solutions with better objective values.

This method focuses strongly on exploitation and can converge quickly, although it may become trapped in local optima.

### Improved Hill Climbing

The improved version introduces modifications intended to increase the robustness of the local search and improve its ability to explore the search space.

### Differential Evolution

Differential Evolution is a population-based evolutionary algorithm.

The implementation includes:

- Population initialization.
- Mutation using three distinct individuals.
- Binomial crossover.
- Boundary handling.
- Greedy replacement.

Candidate vectors are generated from differences between individuals, allowing the algorithm to adapt its search steps to the structure of the population.

### Divine Religions Algorithm

The final stage of the project implements the **Divine Religions Algorithm (DRA)**, a population-based metaheuristic inspired by the interaction between individuals grouped into communities.

The implementation includes concepts such as:

- Population divided into communities.
- Selection of beliefs.
- Miracle operations.
- Reward mechanisms.
- Community leaders.
- Replacement of weak individuals.

The algorithm is evaluated on multiple benchmark functions and different dimensionalities.

---

## 📐 Benchmark Functions

The project includes a collection of standard continuous optimization functions used to evaluate the performance of the algorithms.

Implemented benchmark problems include:

- Ackley
- Bukin N.6
- Carrom Table
- Griewank
- Levy
- Michalewicz
- Rastrigin
- Rosenbrock
- Schwefel 2.26
- Sphere
- Styblinski-Tang
- Trid

These functions provide different optimization landscapes with varying levels of difficulty, including multimodality, narrow valleys and large search spaces.

---

## 🧪 Experimental Evaluation

The algorithms are tested repeatedly on benchmark functions to evaluate the quality and robustness of the solutions obtained.

The final experiments focus on the Divine Religions Algorithm using different dimensions:

```text
D = 10
D = 20
D = 30
```

The repository includes result files for the different benchmark problems and dimensions.

This makes it possible to compare the behaviour of the algorithm as the dimensionality of the problem increases.

---

## 🛠️ Technologies Used

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)

### Main concepts and tools

- Java
- Object-Oriented Programming
- Maven
- JUnit
- Metaheuristic optimization
- Evolutionary computation
- Benchmark testing

---

## 🧱 Project Design

The project follows an object-oriented structure.

### `Algorithm`

Defines a common abstraction for optimization algorithms.

### `Problem`

Represents an optimization problem and provides the objective function and search-space information.

### `Solution`

Represents candidate solutions and their corresponding objective values.

### Optimization algorithms

Each optimization method is implemented in its own class:

```text
RandomSearch.java
HillClimbing.java
ImprovedHillClimbing.java
DifferentialEvolution.java
DivineReligionsAlgorithm.java
```

This structure makes it easier to apply different algorithms to the same benchmark problems.

---

## 📂 Project Structure

```text
evolutionary-computation/
│
├── pom.xml
├── .gitignore
│
└── src/
    ├── main/
    │   └── java/
    │       └── ec/
    │           └── task1/
    │               ├── Main.java
    │               │
    │               ├── domain/
    │               │   ├── Algorithm.java
    │               │   ├── DifferentialEvolution.java
    │               │   ├── DivineReligionsAlgorithm.java
    │               │   ├── HillClimbing.java
    │               │   ├── ImprovedHillClimbing.java
    │               │   ├── Problem.java
    │               │   ├── RandomSearch.java
    │               │   ├── Solution.java
    │               │   └── StatisticsUtility.java
    │               │
    │               └── problems/
    │                   ├── Ackley.java
    │                   ├── Bukin6.java
    │                   ├── CarromTable.java
    │                   ├── Griewank.java
    │                   ├── Levy.java
    │                   ├── Michalewicz.java
    │                   ├── Rastrigin.java
    │                   ├── Rosenbrock.java
    │                   ├── Schwefel26.java
    │                   ├── Sphere.java
    │                   ├── StyblinskiTang.java
    │                   └── Trid.java
    │
    ├── test/
    │   └── java/
    │       └── ec/
    │           └── task1/
    │               └── problems/
    │                   └── ProblemKnownOptimaTest.java
    │
    └── results/
        └── DRA experiment result files
```

---

## ✅ Testing

The repository includes automated tests for known benchmark optima.

The test class:

```text
ProblemKnownOptimaTest.java
```

checks whether the benchmark function implementations return the expected values at their known optimum points.

This helps validate the correctness of the optimization problem definitions before running the search algorithms.

---

## ▶️ How to Run

1. Clone the repository:

```bash
git clone https://github.com/CarlotaAyala/evolutionary-computation.git
```

2. Enter the project directory:

```bash
cd evolutionary-computation
```

3. Compile the project with Maven:

```bash
mvn compile
```

4. Run the tests:

```bash
mvn test
```

5. Execute the main class from your preferred Java IDE or Maven configuration.

The current `Main.java` focuses on experiments with the **Divine Religions Algorithm**.

---

## 💡 What I Learned

This project helped me understand how different optimization strategies behave on complex search spaces.

In particular, I worked with:

- Random search.
- Local search.
- Population-based optimization.
- Differential Evolution.
- Metaheuristic algorithms.
- Exploration vs. exploitation.
- Search-space boundaries.
- Benchmark optimization functions.
- Experimental evaluation of stochastic algorithms.
- High-dimensional optimization.
- Object-oriented algorithm design.
- Automated testing of mathematical functions.

The progression of the project also helped me understand how more advanced metaheuristics build on fundamental ideas from simpler search algorithms.

---

## 🎓 Academic Context

This project was developed as part of the **Evolutionary Computation (EC)** course in the **University of Maribor**.

---

## 👩‍💻 Author

**Carlota Ayala**

Data Science and Engineering student  
University of Las Palmas de Gran Canaria (ULPGC)
