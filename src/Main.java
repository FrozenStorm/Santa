import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import Utility.*;


public class Main {

	public static void main(String[] args) {
		
		// Unendliches erzeugen von neuen Lösungen ------------------------------------------------------------
		ArrayList<Gift> importedData = CsvHandler.ImportSubset(".\\Data\\gifts.csv", 1, 100000);
		ArrayList<Solution> mySolutions;
		
		// Generieren von neuen Lösungen => nach generierung aus NewSolutions in Solutions kopieren von hand um sie als Import brauchen zu können
		/*int k=0;
		while(true){
			mySolutions = NearestNeighbor.GetSolutions(1, importedData);
			CsvHandler.ExportData(".\\NewSolution\\solution" + k + ".csv", mySolutions.get(0));
			k++;
		}*/
		
		// Import von bestehenden Lösungen
		mySolutions = CsvHandler.ImportSolution(importedData, ".\\Solution\\solution", 6);
		
		// Optimierung
//		Population adamAndEva = new Population(0,NearestNeighbor.GetSolutions(1000, importedData));
//		Evolutioner evolutioner = new Evolutioner(adamAndEva);
//		System.out.println("Cost " + evolutioner.getBest().cost);
//		for (int i = 0; i < 100; i++){
//			evolutioner.evolute();
//		}
//		System.out.println("Cost " + evolutioner.getBest().cost);
		
		//Ausgabe wobei die Lösung solution0.csv die beste ist
		mySolutions.sort(new SolutionComparator());
		for(int i=0;i<mySolutions.size();i++){
			System.out.println("------Solution [" + i + "]--------");
			//mySolutions.get(i).Print(); // Alles ausgeben
			System.out.println(mySolutions.get(i).cost); // Nur Gesamtkosten ausgeben
			CsvHandler.ExportData(".\\NewSolution\\solution" + i + ".csv", mySolutions.get(i));
		}
	}

}
