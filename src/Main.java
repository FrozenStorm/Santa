import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import Utility.*;

public class Main {

	public static void main(String[] args) {
		ArrayList<Solution> mySolutions = new ArrayList<>();
		ArrayList<Gift> importedData = CsvHandler.ImportSubset(".\\Data\\gifts.csv", 0, 100000);
		mySolutions = NearestNeighbor.GetSolutions(1, importedData);
		System.out.println("Cost " + mySolutions.get(0).cost);
		CsvHandler.ExportData(".\\Solution\\solution.csv", mySolutions.get(0));
	}

}
