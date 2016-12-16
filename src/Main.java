import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import Utility.*;

public class Main {

	public static void main(String[] args) {
		ArrayList<Solution> mySolutions = new ArrayList<>();
		ArrayList<Gift> importedData = CsvHandler.ImportSubset(".\\Data\\gifts.csv", 1, 100000);
		mySolutions = NearestNeighbor.GetSolutions(1, importedData);
		mySolutions.sort(new SolutionComparator());
		for(int i=0;i<mySolutions.size();i++){
			System.out.println("------Solution [" + i + "]--------");
			//mySolutions.get(i).Print(); // Alles ausgeben
			System.out.println(mySolutions.get(i).cost); // Nur Gesamtkosten ausgeben
		}
		CsvHandler.ExportData(".\\Solution\\solution.csv", mySolutions.get(0));

	/*
	 * 		
	 * ArrayList<Solution> mySolutions = new ArrayList<>();
		ArrayList<Gift> importedData = CsvHandler.ImportSubset("./Data/gifts.csv", 0, 100000);
		Population adamAndEva = new Population(0,NearestNeighbor.GetSolutions(1000, importedData));
		Evolutioner evolutioner = new Evolutioner(adamAndEva);
		System.out.println("Cost " + evolutioner.getBest().cost);
		for (int i = 0; i < 100; i++){
			evolutioner.evolute();
		}
		System.out.println("Cost " + evolutioner.getBest().cost);
		CsvHandler.ExportData(".\\Solution\\solution.csv", evolutioner.getBest());
	 */
	
	}

}
