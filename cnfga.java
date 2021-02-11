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
		
		int genNum = 1;
		int maxGens = 1000;
	
	
		// if we are using cnf "fit enough" is when all clauses evaluate to true 
		// or when fitness score is equal to the number of clauses
		
		Candidate fitEnough =  population.getFittest(formula);
		int fitnessScore = fitEnough.getFitness(formula);
		String solutionFound = "Solution not found";
		while( fitnessScore < formula.clauses.size() && genNum < maxGens ){      
		  
		  // Since we are creating two new candidates from two
		  //   randomly-selected candidates, we'll only have to
		  //   loop for 
			int halfPop = population.size() / 2;
		  //   to replace the entire population
			
			for(int j = 0; j < halfPop; j++){
			  
				// Select two candidates; remember, the probability of 
				// a candidate's selection is weighted by its fitness.
				Candidate parent1 = selectParent(population, formula);
				Candidate parent2 = selectParent(population, formula);
		
				// Use crossover to create two new candidates
				Candidate child1 = crossover(parent1, parent2);
				Candidate child2 = crossover(parent2, parent1);
					
				// If a randomly generated number is less than
				// the mutation rate, mutate the candidate by
				// flipping a random bit for each child.
				Random rn = new Random();
				if(rn.nextDouble() < mutationRate){
					mutate(child1);
				}
				if(rn.nextDouble() < mutationRate){
					mutate(child2);
				}
				  
				// Add the new candidates to the new population.
				population.saveCandidate(j, child1);
				population.saveCandidate(halfPop + j, child2);
				
			}   

		// Get the new fittest candidate
		fitEnough = population.getFittest(formula);
		fitnessScore = fitEnough.getFitness(formula);
		genNum++;
		}
		
		// If you found a fit candidate, print out
		// the solution; else, print "Solution not found."
		if( fitnessScore >= formula.clauses.size()){
			solutionFound = "*Solution Found*\nCandidate: " + fitEnough.printCandidate();
		}
		System.out.println("\nFor CNF with the clauses: " + formula.clauses);
		System.out.println(solutionFound);
  }
	

	private static Candidate selectParent(Population pop, Formula formula) {
        // Returns a parent based on what the selection strategy returns.
        // In our case, we will use stochastic acceptance.
        // -- Find the maximum fitness within the given population.
        // -- Keep drawing candidates from the population at 
        // random and checking them with stochasticAcceptance()
        // until it returns true. Then you can return the "elite"
        // parent.
		
		Candidate maxFitness = pop.getFittest(formula);
		int maxFitnessScore = maxFitness.getFitness(formula);
		
		Random rn = new Random();
		
		boolean accept = false;
		int i = 0;
		int eliteIndex = 0;

		while (!accept){
			eliteIndex = rn.nextInt(pop.size());
			accept = stochasticAcceptance(pop.getCandidate(eliteIndex), formula, maxFitnessScore);
			i++;
		}

		return pop.getCandidate(eliteIndex);
     }
   
      
   private static Boolean stochasticAcceptance(Candidate candidate, Formula formula, int maxPopFitness){
		// Calculate the odds of keeping a candidate based on
        // odds = fitness of this candidate/maxPopFitness
        // Accept the candidate if a randomly generated number between
        // 0 and 1 is < the odds.
		
		boolean accept = false;
		
		Random rn = new Random();
		float rfg = rn.nextFloat();
			   
        int fit = candidate.getFitness(formula);
		float odds = (float) fit / maxPopFitness;
        
		if (Float.compare(rfg, odds) < 0){
			accept = true;
		}
		return accept;
    }


    
	private static Candidate crossover(Candidate parent1, Candidate parent2){
		// Uses standard midpoint crossover to create a new candidate.
		// If the formula has an odd number of variables,
		// the midpoint can be approximated with truncation.
		
		Candidate child = new Candidate(parent1.getLength());
		
		int p1start = 0;
		int p2start = 0;
		
		if(parent1.getLength() % 2 == 1){
			p2start = (parent1.getLength() / 2) + 1;
		} else {
			p2start = parent1.getLength() / 2;
		}
		
		for(int i = 0; i < parent1.getLength(); i++){
			
			boolean parentValue;
			if(i < p2start){
				parentValue = parent1.getValue(i);
			} else {
				parentValue = parent2.getValue(i);
			}
			child.setValue(i, parentValue);
		}
		return child;
    }

    
    
	private static void mutate(Candidate candidate) {
		// Flip a random bit in the candidate.
		Random rn = new Random();
		int mutationPoint = candidate.getLength();
		mutationPoint = rn.nextInt(mutationPoint);
	  
		boolean initalVal = candidate.getValue(mutationPoint);
		candidate.setValue(mutationPoint, initalVal ^ true);

    }


}  /* end of class cnfga */