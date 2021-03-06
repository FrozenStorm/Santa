package Utility;

import java.util.ArrayList;
import java.util.Iterator;

public class Solution extends ArrayList<Route>{
	private static final long serialVersionUID = 1L;
	public double cost=0;
	
	public void Update(){
		Iterator<Route> myInterator = this.iterator();
		cost = 0;
		
		while(myInterator.hasNext()){
			cost += myInterator.next().cost;
		}
	}
	
	public Route getRandomRoute() {
		return remove((int)(Math.random() * size() - 1));
	}

	public void insertForeignRoute(Route foreignRoute) {
		removeForeignGifts(foreignRoute);
		add(foreignRoute);
	}

	private void removeForeignGifts(Route foreignRoute) {
		for (Gift gift : foreignRoute){
			for (Route route : this){
				route.remove(gift);
			}
		}
	}
	
	public void Print(){
		System.out.print("Solutioncost = " + cost);
		for(int i=0;i<this.size();i++){
			System.out.println("Route[" + i + "] :");
			this.get(i).Print();
		}
	}
}
