package Utility;

import java.util.ArrayList;

public class NearestNeighbor {
	static public ArrayList<Solution> GetSolutions(int number, ArrayList<Gift> data){
		data.sort(new GiftComparator());
		
		//Update auf Route und Solution
		return new ArrayList<Solution>();
	}
}
