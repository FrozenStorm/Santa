package Utility;

public class Location
{
	private static double EARTH_RADIUS_KM = 6371;
	public static Location NorthPole = new Location(90, 0); // TODO muss das nicht auch in bogenmas sein oder ist es bereits
	
	public double latitude=0;
	public double longtitude=0;
	public double cosLatitude=0;
	
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
	
	private static double ToRad(double input)
	{
	    return input * (Math.PI / 180);
	}
	
	public void Print(){
		System.out.print("latitude = " + latitude  + " longtitude = " + longtitude);
	}
}