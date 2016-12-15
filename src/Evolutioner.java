import java.util.ArrayList;
import java.util.List;

import Utility.Route;
import Utility.Solution;
import Utility.SolutionComparator;

public class Evolutioner {
	private final static double ELITISM_RATIO = 0.2;
	private Population population;
	private int populationSize;
	
	public Evolutioner(Population population){
		this.population = population;
		populationSize = population.getSize();
	}
	
	public void evolute(){
		List<Solution> newSolutions = population.getBestSolutions((int) (population.getSize() * ELITISM_RATIO));
		while (population.isReproducible()){
			Solution parent1 = population.getRandomSolution();
			Solution parent2 = population.getRandomSolution();
			newSolutions.addAll(recombine(parent1, parent2));
		}
		genocide(newSolutions);
		population = new Population(population.getGeneration() + 1, newSolutions);
	}

	private List<Solution> mutate(List<Solution> solutions) {
		for (Solution solution : solutions){
			//TODO AMO
		}
		return null;
	}
	
	private void genocide(List<Solution> newSolutions) {
		newSolutions.sort(new SolutionComparator());
		newSolutions.removeAll(newSolutions.subList(populationSize, newSolutions.size() -1));
	}

	private List<Solution> recombine(Solution parent1, Solution parent2){
		Route route1 = parent1.getRandomRoute();
		Route route2 = parent2.getRandomRoute();
		parent1.insertForeignRoute(route2);
		parent2.insertForeignRoute(route1);
		parent1.Update();
		parent2.Update();
		ArrayList<Solution> newSolutions = new ArrayList<>();
		newSolutions.add(parent1);
		newSolutions.add(parent2);
		return newSolutions;
	}
	
	public Solution getBest(){
		return population.getBestSolutions(1).get(0);
	}
}
