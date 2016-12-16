package Utility;

import java.util.Comparator;

public class GiftComparator implements Comparator<Gift>{
    public int compare(Gift c1, Gift c2)
    {
    	return Double.compare(c1.location.latitude, c2.location.latitude);
    }
}
