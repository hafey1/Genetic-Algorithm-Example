// Assignment: Genetic Algorithms
// Last modified: 1/11/2021
// Shell of a
// Java program which uses a genetic algorithm to find a solution to
// an equation in conjunctive normal form.

// Program takes as input a string representing a formula written in 
// conjunctive normal form and prints a string of 1's and 0's meant to be the
// instantiations of the variables as true/false from left to write
// which satisfy the formula.
// Example call: 
// java cnfga "(a | b) ^ (b | c) | d"
// Example output: 1011 
// This would signify that a is true, b is false,
//    c is true, and d is true. Note that this is one possible 
//    solution that makes the formula true.
// 
// Use the Formula class provided to parse a formula read in from the
// command line. 
// Get the number of unique variables in the equation
// Randomly create a population of strings which assign a 0 or 1 to 
// each variable in the list. Example: String 1011
// represents an assignment where a is True, b is False,
// c is True, and d is True. (Since any formula's variables can be 
// mapped into numbers, we won't worry about fancy variable names.)
// While not done do
//     Evaluate fitness of each member of the population
//     (We'll use a fitness function which counts the 
//      number of clauses which evaluate to TRUE.)
//     If a member is "fit enough," 
//        done := true.
//     else 
//        Create a new population by
//           Selecting members to be parents based on fitness.
//           Producing offspring from those parents using crossover and mutation.

import java.io.*;
import java.util.*;

public class cnfga {
   
   // You can experiment with the mutationRate.
   private static final double mutationRate = 0.01;

   public static void main(String [] args) {
   
		// Print out the input formula given as a string argument 
		// to the program:
		// cmd prompt needs | to be escaped with a preceeding ^. Like so ((a^b)^|b)
		System.out.println("Input Formula:");
		System.out.println(args[0]);
		System.out.println();
      
		// Call the Formula class to create a data structure from
		// the input for quick access and evaluation of candidates:
      
		Formula formula = new Formula(args[0]);
		System.out.print("formula = "); 
		System.out.println(formula.clauses);
		System.out.print("uniqueVars = ");
		System.out.println(formula.numUniqueVars);
      
		// Set a population size (use an even number)
		Population population = new Population(10);
		// Set the maximum number of generations that
		// you wish to loop through before you give up.
		int maxGens = 10;
		// Create an initial population of random candidates.
		// (Use the number of unique variables in a formula to create 
		// candidates of appropriate size.)   
		int i = 0;
	  
		while (i < population.size()){
			//create random candidates with RNG
			Candidate cand = new Candidate(formula.numUniqueVars);
			// use candidate seed to initialize
			population.saveCandidate(i, cand);	
			i++;
		}
		population.seedPop(10);
		
	genNum = 1;
	int fitnessScore = 30;
	
	// if we are using cnf "fit enough" is when all clauses evaluate to true 
	// or when fitness score is equal to the number of clauses
	mutate(candidates[0]);
	
	while( fitnessScore < formula.clauses.size() && genNum < maxGens ){      
      Population childPopulation = new Population(10);
      
      // Since we are creating two new candidates from two
      //   randomly-selected candidates, we'll only have to
      //   loop for 
		int halfPop = popSize / 2
      //   to replace the entire population
        
      for(int i = 0; i < halfPop; i++){
          // Select two candidates; remember, the probability of 
          //   a candidate's selection is weighted by its fitness.
          
          // Use crossover to create two new candidates
          
          // If a randomly generated number is less than
          // the mutation rate, mutate the candidate by
          // flipping a random bit.
          
          // Add the new candidates to the new population.
      }   
            
        // Get the new fittest candidate
		genNum++;
    }

    // If you found a fit candidate, print out
    // the solution; else, print "Solution not found."
  }
      
   
/*   private static Candidate selectParent(Population pop, Formula formula) {
        // Returns a parent based on what the selection strategy returns.
        // In our case, we will use stochastic acceptance.
        // -- Find the maximum fitness within the given population.
        // -- Keep drawing candidates from the population at 
        // random and checking them with stochasticAcceptance()
        // until it returns true. Then you can return the "elite"
        // parent.
        
     }
*/   
      
/*   private static Boolean stochasticAcceptance(Candidate candidate, Formula formula,
           int maxPopFitness){
           
        // Calculate the odds of keeping a candidate based on
        // odds = fitness of this candidate/maxPopFitness
        // Accept the candidate if a randomly generated number between
        // 0 and 1 is < the odds.

    }
*/
    
    
/*  private static Candidate crossover(Candidate parent1, Candidate parent2){
      // Uses standard midpoint crossover to create a new candidate.
      // If the formula has an odd number of variables,
      // the midpoint can be approximated with truncation.
    }
*/
    
    
  private static void mutate(Candidate candidate) {
      // Flip a random bit in the candidate.
	  Random rn = new Random();
	  int mutationPoint = candidate.getLength();
	  mutationPoint = rn.nextInt(mutationPoint);
	  System.out.println("mutation point is: " + mutationPoint);
	  
    }


}  /* end of class cnfga */