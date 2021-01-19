// Shell for the Candidate class of the GA assignment.
// Last Modified: 1/6/21

import java.io.*;
import java.util.BitSet;
import java.util.Random;
import java.lang.Math;

// Since we are trying to focus on the GA aspect of the
// project, we simply associate variable 'a' with index 0
// in the candidate. Subsequent indices represent variables
// 'b', 'c', etc.

public class Candidate {
    
    private BitSet truthVals;
    private int numVals;
    
    public Candidate(int length){
       truthVals = new BitSet(length);
       numVals = length;
    }
    
    public void seedCandidate(){
    // Randomly sets bits in the candidate
	Random rn = new Random();
	for(int i = 0; i < numVals; i++){
		int rand = rn.nextInt(100);
		int setbit = rand % 2;
		if (setbit == 1){
			truthVals.set(i, true);
			}
		else {
			truthVals.set(i, false);
			}
		}
	}
	
    public void setValue(int index, boolean val){
    // Sets the bit at the given index to correspond to val.
		try {
			truthVals.set(index, val);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
    }
    
    public boolean getValue(int index){
      // Returns the value at a given index
		boolean result = false;
		if(index >= numVals || index < 0){
			System.out.println("Error: Bit is not set because index is out of bounds, please enter index from 0 to " + (numVals - 1));
			return result;
		}
		result = truthVals.get(index);
		
		return result;
	}
    
    public int getLength(){
      // Returns the length of the Candidate
	  return numVals;
    }
    
    public int getFitness(Formula formula) {
      // Returns the fitness of a candidate; this is the number of clauses
      // in a formula made true by the candidate.
		return -1;
    }
    
    public void printCandidate(){
      // Prints out the candidate
		String byteArrayRepresentation = "";
		
		String intRepresentation = "{ ";
		int intForChar = 0;
		
		for(int i = 0; i < numVals; i ++) {
			if (truthVals.get(i)){
				intRepresentation += "1 ";
			} else {
				intRepresentation += "0 ";
			}
		}
		intRepresentation += "}";
		System.out.println(intRepresentation);
    }

}