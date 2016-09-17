import java.io.FileNotFoundException;
import java.util.ArrayList;




public class History {

	private static ArrayList<String[]> unorderedList;
	private static String[] data;
	
	public static ArrayList<String[]> prepare(ArrayList<String> history) {
		 
		unorderedList = new ArrayList<String[]>();
		for(int i=0; i<history.size()/5; i++){
			data = new String[5];
			for(int j=0;j<5;j++){
				data[j] = history.get((i*5)+j);
			}
			unorderedList.add(data);
		}
		
		return unorderedList;
	}
	
	private static void swapArrayElements(int[] idx, int i, int j) {
		int t = idx[i]; 
		idx[i] = idx[j]; 
		idx[j] = t; 
	}
	private static void swapArrayElements(String[] idx, int i, int j) {
		String t = idx[i]; 
		idx[i] = idx[j]; 
		idx[j] = t; 
	}
	
	public static int[] byScore(ArrayList<String> list) throws FileNotFoundException{
		
		unorderedList = History.prepare(list);
		
		int[] uDateArray  = new int[unorderedList.size()];
		int[] ind = new int[unorderedList.size()];
		for(int i=0; i<unorderedList.size();i++){
			uDateArray[i]=Integer.parseInt(unorderedList.get(i)[2]);
			ind[i]=i;
		}
		
		for(int i=uDateArray.length-1;i>0; i--){
			if(uDateArray[i-1]>uDateArray[i]){
				History.swapArrayElements(uDateArray, i-1, i);
				History.swapArrayElements(ind, i-1, i);
				i=uDateArray.length;
			}	
		}
	
		return ind;
	}
	public static int[] byName(ArrayList<String> list) throws FileNotFoundException{
		
		unorderedList = History.prepare(list);


		String[] uDateArray  = new String[unorderedList.size()];
		int[] ind = new int[unorderedList.size()];
		for(int i=0; i<unorderedList.size();i++){
			uDateArray[i]=unorderedList.get(i)[0];
			ind[i]=i;
		}
			

		for(int i=0;i<ind.length-1; i++){
			if(uDateArray[i].compareTo(uDateArray[i+1])>0){
				History.swapArrayElements(uDateArray, i, i+1);
				History.swapArrayElements(ind, i, i+1);
				i=0;
			}	
			
		}


		return ind;
	}
	public static int[] byDate(ArrayList<String> list) throws FileNotFoundException{
		
		unorderedList = History.prepare(list);
		
		int[] uDateArray  = new int[unorderedList.size()];
		int[] ind = new int[unorderedList.size()];
		for(int i=0; i<unorderedList.size();i++){
			uDateArray[i]=Integer.parseInt(unorderedList.get(i)[4]);
			ind[i]=i;
		}
		
		for(int i=uDateArray.length-1;i>0; i--){
			if(uDateArray[i-1]>uDateArray[i]){
				History.swapArrayElements(uDateArray, i-1, i);
				History.swapArrayElements(ind, i-1, i);
				i=uDateArray.length;
			}	
		}
	
		return ind;
	}
}
