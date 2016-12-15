package Utility;

import java.util.Comparator;
import Utility.Solution;

public class SolutionComparator implements Comparator<Solution>{
	public int compare(Solution c1, Solution c2)
    {
    	return Double.compare(c1.cost, c2.cost);
    }
}
