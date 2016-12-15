package Utility;

import java.util.Comparator;
import Utility.Route;

public class RouteComparator implements Comparator<Route>{
	public int compare(Route c1, Route c2)
    {
    	return Double.compare(c1.cost, c2.cost);
    }
}
