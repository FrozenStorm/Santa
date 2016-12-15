import java.util.List;

import Utility.Solution;

public class Population {
	private int generation;
	private List<Solution> solutions;

	public Population(int generation, List<Solution> solutions) {
		this.generation = generation;
		this.solutions = solutions;
	}
	
	public int getSize(){
		return solutions.size();
	}
	
	public boolean isReproducible(){
		return solutions.size() > 1;
	}
	
	public List<Solution> getBestSolutions(int n){
		return solutions.subList(0, n);
	}
	
	public Solution getRandomSolution(){
		int index = (int) (Math.random() * solutions.size() - 1);
		return solutions.remove(index);
	}

	public int getGeneration() {
		return generation;
	}
}
