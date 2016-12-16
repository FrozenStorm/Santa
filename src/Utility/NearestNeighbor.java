package Utility;

import java.util.ArrayList;
import java.util.Random;

public class NearestNeighbor {
	static double MAX_WEIGHT = 1000;
	static public ArrayList<Solution> GetSolutions(int number, ArrayList<Gift> data){
		Random myRand = new Random();
		int indexNow,i2=0;
		Solution nextSolution;
		Gift giftNow;
		ArrayList<Solution> mySolutions = new ArrayList<>();
		ArrayList<Gift> remainingGifts;
		Route nextRoute;
		data.sort(new GiftComparator());
		
		for(int s=0; s < number; s++){
			remainingGifts = (ArrayList<Gift>)data.clone();
			nextSolution = new Solution();
			while(remainingGifts.isEmpty() == false){
				double weight = 10;
				nextRoute = new Route();
				indexNow = myRand.nextInt(remainingGifts.size());
				giftNow = remainingGifts.get(indexNow);
				System.out.println(remainingGifts.size());
				while(giftNow.weight+weight < MAX_WEIGHT){
					nextRoute.add(giftNow);
					remainingGifts.remove(giftNow);
					weight += giftNow.weight;
					if(remainingGifts.isEmpty()==true) break; //Abbruch falls liste leer bevor sack voll
					//indexNow = PIndNext(giftNow,indexNow,remainingGifts);
					indexNow = PNext(giftNow,indexNow,remainingGifts);
					giftNow = remainingGifts.get(indexNow);
				}
				nextRoute.Update();
				nextSolution.add(nextRoute);
			}
			nextSolution.Update();
			mySolutions.add(nextSolution);
		}
		return mySolutions;
	}
	
	private static int PNext(Gift g0, int ind1, ArrayList<Gift> SortL){
		double best=0;
		double next=0;
		int bestIndex=0;
		best = Location.Haversine(g0.location, SortL.get(bestIndex).location);
		for(int i=1;i<SortL.size();i++){
			next = Location.Haversine(g0.location, SortL.get(i).location);
			if(next < best){
				best = next;
				bestIndex = i;
			}
		}
		return bestIndex;
	}
	
	private static int PIndNext(Gift g0, int ind1, ArrayList<Gift> SortL){
		int indNext = 0, ind = 0;		//index
		boolean PnextFound = false;
		int k = 0;					//Zaeler
		double Dnext = 0;			//Distanz zum naechsten Punkt
		double D = Location.Haversine(g0.location, SortL.get(ind1%SortL.size()).location);			//kuerzeste bisher gefundene Distanz
		double LatBound = (g0.location.latitude+getAngDist(SortL.get(ind1%SortL.size()).location.latitude, D))%(2*Math.PI);		// Schranke berechnen
		/*
		System.out.print("Index = " + ind1 + " ");
		g0.Print();
		System.out.print("\n");
		for(int i=0;i<SortL.size();i++){
			System.out.print("    Gift[" + i + "] :");
			SortL.get(i).Print();
			System.out.print("\n");
		}*/
		while(!PnextFound){
			ind = (ind1+k)%SortL.size();
			//System.out.println(ind);
			if( ((SortL.get(ind).location.latitude < LatBound) || (SortL.get(ind).location.latitude >= g0.location.latitude)) && k < SortL.size()/2){
				Dnext = Location.Haversine(g0.location, SortL.get(ind).location);
				if( Dnext < D ){
					D = Dnext;		//kuerzere Distanz merken
					indNext = ind;			//index der kuerzesten Distanz merken
					LatBound = (g0.location.latitude+getAngDist(SortL.get(ind).location.latitude, D))%(2*Math.PI);// Schranke berechnen 
				}
			}else{
				PnextFound = true;
			}
			k += 1;
		}
		
		PnextFound = false;
		k = 0;
		LatBound = (g0.location.latitude-getAngDist(SortL.get(ind).location.latitude, D))%(2*Math.PI);// Schranke berechnen
		while(!PnextFound){
			ind = (ind1-k)%SortL.size();
			if(ind < 0) ind += ((int)(Math.abs(ind)/SortL.size())+1)*SortL.size();
			//System.out.println(ind);
			if( ((SortL.get(ind).location.latitude > LatBound) || (SortL.get(ind).location.latitude <= g0.location.latitude)) && k < SortL.size()/2){
				Dnext = Location.Haversine(g0.location, SortL.get(ind).location);
				if( Dnext < D ){
					D = Dnext;		//kuerzere Distanz merken
					indNext = ind;  //index der kuerzesten Distanz merken
					LatBound = (g0.location.latitude-getAngDist(SortL.get(ind).location.latitude, D))%(2*Math.PI);// Schranke berechnen
				}
			}else{
				PnextFound = true;
			}
			k += 1;
		}
		
		
		return indNext;
	}
	
	private static double getAngDist(double Lat0, double D){
		double Ang = (D/(6371*Math.sin(Lat0)));
		if(Ang > Math.PI){
			Ang = Math.PI;
			System.out.println("Keine Schranke");
		}
		return Ang;
	}
}
