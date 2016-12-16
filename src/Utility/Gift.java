package Utility;

import java.util.Comparator;

public class Gift {
	public int giftId;
	public Location location;
	public double weight;
	
	public Gift(int giftId, double latitude, double longtitude, double weight){
		this.giftId = giftId;
		location = new Location(latitude, longtitude);
		this.weight = weight;
	}
	
	public void Print(){
		location.Print();
		System.out.print(" weight = " + weight);
	}
}