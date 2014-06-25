<<<<<<< HEAD
<<<<<<< HEAD
## Solving the Knapsack Problem via Genetic Algorithm

### Introduction

Solves the knapsack problem with a genetic algorithm, written in Java.

### Description

The knapsack problem is built via command line user input

The fitness function sums the corresponding weights and values (separately) for each population member one by one. It then compares the population member’s total weight to the knapsack capacity. If the knapsack capacity has been exceeded by the population member’s total weight, then the fitness value is set to 0. Otherwise, the population member’s corresponding total value is set as the fitness value and returned.

The stopping criterion has been defined as ‘the occurrence of 3 consecutive equal generational fitness value means.’ In other words, if the mean of a generation’s population members’ fitness function value is repeated 3 times in a row, the algorithm is stopped. For example, if generations 14, 15, and 16 of a particular execution all return a generational fitness value mean of 34.6, the algorithm will halt and skip directly to plotting its results.
 
### Usage

![Knapsack Problem via Genetic Algorithm](https://github.com/mmmayo13/tweet-sentiment-scores/tweet.intro.gif)

### Requirements

This program is written in Java, and therefore requires a JVM.

### Installation

Download the knapsack package, compile & execute:

```
// Compile knapsack
javac knapsack/*.java

// Execute knapsack
java knapsack/KnapsackProblem <output_file>
```

### Getting Help

The code is well-commented and should be sufficient for those who understand genetic algorithms.

If you require an introduction to genetic algorithms, check [here](http://en.wikipedia.org/wiki/Genetic_algorithm).

An overview of the knapsack problem can be found [here](http://en.wikipedia.org/wiki/Knapsack_problem).

### Author

[Matt Mayo](http://about.me/mattmayo)

### License

This software is made available under the [MIT License](http://choosealicense.com/licenses/mit/)
=======
Solves knapsack problem with a genetic algorithm

The knapsack problem is built via command line user input.

The fitness function sums the corresponding weights and values (separately) for each population member one by one.  It then compares the population member’s total weight to the knapsack capacity.  If the knapsack capacity has been exceeded by the population member’s total weight, then the fitness value is set to 0.  Otherwise, the population member’s corresponding total value is set as the fitness value and returned.

The stopping criterion has been defined as ‘the occurrence of 3 consecutive equal generational fitness value means.’  In other words, if the mean of a generation’s population members’ fitness function value is repeated 3 times in a row, the algorithm is stopped.  For example, if generations 14, 15, and 16 of a particular execution all return a generational fitness value mean of 34.6, the algorithm will halt and skip directly to plotting its results.
>>>>>>> f1b31a548868fb6c7aaa7cf4fd07d91a6e76a8d0
=======
knapsack-problem-ga
===================

Solves the knapsack problem using genetic algorithm in Java
>>>>>>> 6bb7d7c7c1bef16b18d758780809ab870bf7423a
