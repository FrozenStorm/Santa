package Utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CsvHandler {
	private static final char DEFAULT_SEPARATOR = ',';
	
	public static ArrayList<Solution> ImportSolution(ArrayList<Gift> giftList, String src, int number){
		ArrayList<Solution> solutionList = new ArrayList<Solution>();
		Solution solutionNow;
		
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        try{
        	for(int x=0;x<number;x++){
        		System.out.println("Importing Solution " + x);
	        	solutionNow = new Solution();
	        	br = new BufferedReader(new FileReader(src + x + ".csv"));
	        	br.readLine(); // Ignore Header
	            while ((line = br.readLine()) != null) {
	                String[] gift = line.split(cvsSplitBy); // use comma as separator
	                while(Integer.valueOf(gift[1])>=solutionNow.size())solutionNow.add(new Route()); // Fehlende Routen erzeugen
                	if(Integer.valueOf(gift[0]) == giftList.get(Integer.valueOf(gift[0])-1).giftId){
                		solutionNow.get(Integer.valueOf(gift[1])).add(giftList.get(Integer.valueOf(gift[0])-1));
                	}
                	else
                	{
                		System.out.println("Error");
                	}
	            }
	            for(int y=0;y<solutionNow.size();y++){
	            	solutionNow.get(y).Update();
	            }
	            solutionNow.Update();
	            solutionList.add(solutionNow);
	        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return solutionList;
	}
	
	public static ArrayList<Gift> ImportData(String src){
		ArrayList<Gift> giftList = new ArrayList<Gift>();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try{
        	br = new BufferedReader(new FileReader(src));
        	br.readLine(); // Ignore Header
            while ((line = br.readLine()) != null) {
                String[] gift = line.split(cvsSplitBy); // use comma as separator
                giftList.add(new Gift(Integer.valueOf(gift[0]),Double.valueOf(gift[1]),Double.valueOf(gift[2]),Double.valueOf(gift[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return giftList;
	}
	
	public static ArrayList<Gift> ImportSubset(String src, int startLine, int length){
		ArrayList<Gift> giftList = ImportData(src);
		if(startLine >= 0 && length > 0 && (startLine + length) <= giftList.size()){
			int i;
			for(i=0;i<startLine;i++){
				giftList.remove(0);
			}
			while(giftList.size() > length){
				giftList.remove(length);
			}
		}
		return giftList;
	}
	
	public static void ExportData(String dest, Solution data){
		try{
			FileWriter fw = new FileWriter(dest);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
		    out.println("GiftId,TripId");
			for(int route=0; route<data.size(); route++){
				for(int i=0; i<data.get(route).size(); i++){
					out.println(data.get(route).get(i).giftId + "," + route);
				}
			}
			out.close();
			bw.close();
			fw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
