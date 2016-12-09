import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Utility.CsvHandler;
import Utility.Gift;
import Utility.Location;

public class Main {

	public static void main(String[] args) {
		List<List<Gift>> solution = new ArrayList<List<Gift>>();
		List<Gift> singleRoute = CsvHandler.ImportSubset(".\\Data\\gifts.csv", 0, 10);
		solution.add(singleRoute);
		System.out.println("Cost " + Location.Cost(singleRoute));
		System.out.println("TotalCost " + Location.TotalCost(solution));
		CsvHandler.ExportData(".\\Solution\\solution.csv", solution);
	}

}
