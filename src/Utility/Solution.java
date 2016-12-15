package Utility;

import java.util.ArrayList;
import java.util.Iterator;

public class Solution extends ArrayList<Route>{
	private static final long serialVersionUID = 1L;
	public double cost;
	
	public void Update(){
		Iterator<Route> myInterator = this.iterator();
		cost = 0;
		
		while(myInterator.hasNext()){
			cost += myInterator.next().cost;
		}
	}
}
