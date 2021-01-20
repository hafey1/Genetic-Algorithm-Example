// Shell for the Population class of the GA assignment.
// Last modified: 1/6/21
public class Population {

    Candidate[] candidates;

    
    // Constructor
    public Population(int populationSize) {
        candidates = new Candidate[populationSize];
    }
    
    public void seedPop(int candidateLength) {
        // Seed a population with candidates of length candidateLength
		for (int i = 0; i < candidateLength; i++) {
			candidates[i].seedCandidate();
		}
    }      

    public Candidate getCandidate(int index) {
        // Return the candidate at a given index
		return candidates[index];	
	}

    public Candidate getFittest(Formula formula) {
		Candidate fittest = candidates[0];
		//int tracker = 0;
		for (int i = 1; i < candidates.length; i++) {
			int fit = candidates[i].getFitness(formula);
			if(fit >= fittest.getFitness(formula)) {
				fittest = candidates[i];
				//tracker = i;
			}
	  }
		//System.out.println(tracker + " is the fittest candidate with a score of: " +fittest.getFitness(formula));
		
		return fittest;
	}

  
    public int size() {
       // Returns the population size
	   return candidates.length;
    }

    public void saveCandidate(int index, Candidate candidate) {
       // Adds a candidate to the population
	   candidates[index] = candidate;
    }
    
    public void printPop(int numUniqueVars){ 
       // Prints out all the candidates in a population
       // You can use my code, but you must implement printCandidate
       // in the Candidate class for it to work:
		System.out.print("\n  ");
		for (int i = 0; i < numUniqueVars; i++) {
			System.out.print(((char) (97 + i)) + " ");
		}
		System.out.println("\n- - - - - - - - - - - - - - -");
		for(int i = 0; i < candidates.length; i++){
			candidates[i].printCandidate();
		}
		System.out.println();
    }
    
    
}