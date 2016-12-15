package Utility;

import java.util.ArrayList;
import java.util.Iterator;
import Utility.Location;

public class Route extends ArrayList<Gift>{
	private static final long serialVersionUID = 2L;
	public double cost;
	public double weight;
	
	public void Update(){
		Gift currentGift = this.get(0);;
		double remainingWeight = 10; // Base weight of sleighs
		Iterator<Gift> myInterator = this.iterator();
		
		while(myInterator.hasNext()){
			remainingWeight += myInterator.next().weight;
		}
		weight = remainingWeight;
		
		myInterator = this.iterator();
		cost = Location.Haversine(Location.NorthPole, currentGift.location) * remainingWeight;
		remainingWeight -= currentGift.weight;
		while(myInterator.hasNext()){
			Gift nextGift = myInterator.next();
			cost += Location.Haversine(currentGift.location, nextGift.location) * remainingWeight;
			remainingWeight -= nextGift.weight;
			currentGift = nextGift;
		}

		cost += Location.Haversine(currentGift.location, Location.NorthPole) * remainingWeight;
	}
}
