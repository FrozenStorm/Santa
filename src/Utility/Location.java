package Utility;

import java.util.List;

public class Location
{
	private static double EARTH_RADIUS_KM = 6371;
	public static Location NorthPole = new Location(90, 0); // TODO muss das nicht auch in bogenmas sein oder ist es bereits
	
	public double latitude;
	public double longtitude;
	public double cosLatitude;
	
	public Location(double latitude, double longtitude)
	{
	    this.latitude = ToRad(latitude); // TODO stimmt umrechnung wie sind sie in datei gespeichert
	    this.longtitude = ToRad(longtitude);
	    this.cosLatitude = Math.cos(this.latitude);
	}
		
	public static double Haversine(Location point1, Location point2)
	{
	    double dLat = point2.latitude - point1.latitude;
	    double dLon = point2.longtitude - point1.longtitude;
	
	    double a = Math.pow(Math.sin(dLat / 2), 2) +
	               point1.cosLatitude * point2.cosLatitude *
	               Math.pow(Math.sin(dLon / 2), 2);
	
	    return 2 * EARTH_RADIUS_KM * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	}
	
	public static double Cost(List<Gift> giftList){
		double totalCost = 0;
		double remainingWeight = 10; // Base weight of sleighs
		Gift currentGift = giftList.get(0);
		
		for(int i = 0; i < giftList.size(); i++){
			remainingWeight += giftList.get(i).weight;
		}
		
		totalCost += Haversine(Location.NorthPole, currentGift.location) * remainingWeight;
		remainingWeight -= currentGift.weight;
		
		for (int i = 1; i < giftList.size(); i++) {
			Gift nextGift = giftList.get(i);
			totalCost += Haversine(currentGift.location, nextGift.location) * remainingWeight;
			remainingWeight -= nextGift.weight;
			currentGift = nextGift;
		}

		totalCost += Haversine(currentGift.location, Location.NorthPole) * remainingWeight;

		return totalCost;
	}
	
	public static double TotalCost(List<List<Gift>> giftList){
		double totalCost = 0;
		
		for (int i = 0; i < giftList.size(); i++) {
			totalCost += Location.Cost(giftList.get(i));
		}
		
		return totalCost;
	}
	
	private static double ToRad(double input)
	{
	    return input * (Math.PI / 180);
	}
}