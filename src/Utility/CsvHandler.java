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
	
	public static List<Gift>ImportData(String src){
		List<Gift> giftList = new ArrayList<Gift>();
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
	
	public static List<Gift>ImportSubset(String src, int startLine, int length){
		List<Gift> giftList = ImportData(src);
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
	
	public static void ExportData(String dest, List<List<Gift>> data){
		try{
			FileWriter fw = new FileWriter(dest);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw);
		    out.println("GiftId,TripId");
			for(int route=0; route<data.size(); route++){
				for(int i=0; i<data.get(route).size(); i++){
					out.println(data.get(route).get(i).giftId + "," + i);
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
