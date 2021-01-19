1/12/21 — Principles of AI - Spring 2021

Homework 1: Genetic Algorithms

** This is a big project, so it is worth 2 regular homework grades.
** If only parts of your program work, it is your responsibility to demonstrate those parts to me so that you can get partial credit. 

Write a Java program which uses a Genetic Algorithm to find a solution to a formula written in Conjunctive Normal Form. 
Use stochastic acceptance to perform selection.
Use crossover and mutation for the reproduction part of the algorithm.
Input: a formula written in CNF from the command line. You can assume that the input will be a valid CNF formula. 
Output: an assignment of truth values to the variables in the formula which will make the formula true. You can use 0 and 1 to denote “False” and “True.” In the event that there are not enough iterations to find a solution, I will increase that number, within reason, to give your code a chance.
To compile:
javac cnfga.java
To run:
java cnfga "(a | b) ^ (a | c) ^ (-a | -c | d) ^ (-a | -b) ^ (a | f) ^ e ^ f ^ g ^ h ^ i ^ j"
A possible solution might be:
1000111111

Attached files: 

Each file has *copious* comments designed to give you as much help as possible; however, they are not step-by-step instructions to follow blindly. It's probably best to read the files in the order listed. 
Formula.java is completed code for your use. The rest of the files are shells constructed from code I had written, stripped of code but not comments. (I have added a tiny
bit of code to cnfga.java to create and print out a Formula from command-line input 
so that you can see a bit of the data structure.)

  cnfga.java — main program 
  Formula.java - parses a string (the formula read in from the command line) and populates a data structure (an ArrayList of Hashsets) which should be used throughout the program. 
  Population.java - a class which allows us to work with a population.
  Candidate.java - a class which allows us to work with individual candidates. 

