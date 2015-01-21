import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class IKDDfinal {

	public static int max = 256;
	public static void main(String[] args) {
		/*convert data to binary data, but the first item isn't binary*/
		FileReader fr;
	//	FileWriter fw;
		ArrayList<ArrayList<Integer>>allData = new ArrayList<ArrayList<Integer>>();
		try {
			fr = new FileReader("D://knowledge/train.csv");
			String oneLine;
			BufferedReader br = new BufferedReader(fr);
			 br.readLine();
			while ((oneLine = br.readLine()) != null) {
				ArrayList<Integer> a = new ArrayList<Integer>();
				//System.out.println(oneLine);
				int i = 0, j = 0;
				String oneItem;
				int k = oneLine.indexOf(",");
				oneItem = oneLine.substring(0, k);
				a.add(Integer.parseInt(oneItem));
				for(;oneLine.indexOf(",", j + 1) != -1;)
				{
					i = oneLine.indexOf(",",j);
					
					i = i + 1;
					j = oneLine.indexOf(",",i);
				    oneItem = oneLine.substring(i, j);
				    if(Integer.parseInt(oneItem) > 100)a.add(1);
				    else a.add(0);
				   // System.out.print(oneItem+",");
				}
				j = j + 1;
				oneItem = oneLine.substring(j);
				if(Integer.parseInt(oneItem) > 100)a.add(1);
			    else a.add(0);
				// System.out.print(oneItem+"\n");
				allData.add(a);
				
				
			}
			br.close();
			fr.close();
			}catch (IOException e) {
				System.out.println("123");
				e.printStackTrace();
			}
		
		                        /* convert arraylist to 2D array*/

		int allDataArray[][] = convertArrayListToArray(allData);
		
		/*delete label*/
		int deleteLabelArray[][] =  DeleteLabel(allData);
		//System.out.println(computeDistance(deleteLabelArray[28894],deleteLabelArray[28895]));
		//System.out.println(computeDistance(deleteLabelArray[85],deleteLabelArray[86]));
		//System.out.println(deleteLabelArray[1][124]);
		ArrayList<ArrayList<Integer>> label0 = classifiction(allData,0);
		ArrayList<ArrayList<Integer>> label1 = classifiction(allData,1);
		ArrayList<ArrayList<Integer>> label2 = classifiction(allData,2);
		ArrayList<ArrayList<Integer>> label3 = classifiction(allData,3);
		ArrayList<ArrayList<Integer>> label4 = classifiction(allData,4);
		ArrayList<ArrayList<Integer>> label5 = classifiction(allData,5);
		ArrayList<ArrayList<Integer>> label6 = classifiction(allData,6);
		ArrayList<ArrayList<Integer>> label7 = classifiction(allData,7);
		ArrayList<ArrayList<Integer>> label8 = classifiction(allData,8);
		ArrayList<ArrayList<Integer>> label9 = classifiction(allData,9);
		
		
	    int label0Array[][] = convertArrayListToArray(label0);
		int label1Array[][] = convertArrayListToArray(label1);
		int label2Array[][] = convertArrayListToArray(label2);
		int label3Array[][] = convertArrayListToArray(label3);
		int label4Array[][] = convertArrayListToArray(label4);
		int label5Array[][] = convertArrayListToArray(label5);
		int label6Array[][] = convertArrayListToArray(label6);
		int label7Array[][] = convertArrayListToArray(label7);
		int label8Array[][] = convertArrayListToArray(label8);
		int label9Array[][] = convertArrayListToArray(label9);
		
		
		FileReader fr1;
		ArrayList<ArrayList<Integer>>allData1= new ArrayList<ArrayList<Integer>>();
		try {
			fr1 = new FileReader("D://knowledge/test.csv");
			String oneLine;
			BufferedReader br1 = new BufferedReader(fr1);
			 br1.readLine();
			while ((oneLine = br1.readLine()) != null) {
				ArrayList<Integer> a = new ArrayList<Integer>();
				//System.out.println(oneLine);
				int i = 0, j = 0;
				String oneItem;
				int k = oneLine.indexOf(",");
				oneItem = oneLine.substring(0, k);
				a.add(Integer.parseInt(oneItem));
				for(;oneLine.indexOf(",", j + 1) != -1;)
				{
					i = oneLine.indexOf(",",j);
					
					i = i + 1;
					j = oneLine.indexOf(",",i);
				    oneItem = oneLine.substring(i, j);
				    if(Integer.parseInt(oneItem) > 100)a.add(1);
				    else a.add(0);
				   // System.out.print(oneItem+",");
				}
				j = j + 1;
				oneItem = oneLine.substring(j);
				if(Integer.parseInt(oneItem) > 100)a.add(1);
			    else a.add(0);
				// System.out.print(oneItem+"\n");
				allData1.add(a);
				
				
			}
			br1.close();
			fr1.close();
			}catch (IOException e) {
				System.out.println("123");
				e.printStackTrace();
			}
		int allDataArray1[][] = convertArrayListToArray(allData1);
		
		try {
			FileWriter fw;
			fw = new FileWriter("D://knowledge/answer.csv");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("ImageId,Label\n");
			
			for(int i = 0; i < allDataArray1.length; i++){
				System.out.println(i+1+"...");
				bw.write(i+1+","+pickShotestLabel(allDataArray1[i], label0Array, label1Array, label2Array, label3Array, 
					   label4Array, label5Array, label6Array, label7Array, label8Array, label9Array)+"\n");
			//if(i == 100)break;
			}
		bw.close();
		fw.close();
		}catch (IOException e) {
			System.out.println("123");
			e.printStackTrace();
		}
		System.out.println("end");
	}
	
	public static int pickShotestLabel(int[] a, int[][] l0, int[][] l1, int[][] l2, 
			int[][] l3, int[][] l4, int[][] l5, int[][] l6, int[][] l7, int[][] l8, 
			int[][] l9)
	{
		double k = 1;
		int rem = 0;
		double average[] = new double[10];
	    
	   average[0] = sweep(l0,a);
	   average[1] = sweep(l1,a);
	   average[2] = sweep(l2,a);
	   average[3] = sweep(l3,a);
	   average[4] = sweep(l4,a);
	   average[5] = sweep(l5,a);
	   average[6] = sweep(l6,a);
	   average[7] = sweep(l7,a);
	   average[8] = sweep(l8,a);
	   average[9] = sweep(l9,a);
	   
	   
	   for(int i = 0; i < average.length; i++)if(k > average[i])
	   {
		   k = average[i];
		   rem = i;
	}
		return rem;
	}
	
	public static double sweep( int[][] a, int[] b)
	{
		double answer = 0;
		answer = computeDistance(a[0], b);
		for(int i = 0; i < a.length; i++)
	    {
	    	if(answer > computeDistance(a[i], b)) {
	    		answer = computeDistance(a[i], b);
	    	}
	    } 
	   return answer;
		
	}
	
	public static ArrayList<ArrayList<Integer>> classifiction(ArrayList<ArrayList<Integer>> a, int b)
	{
		int size =  a.get(0).size();
		int outSize = a.size();
		ArrayList<ArrayList<Integer>> c = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < outSize; i++){
			
				if(a.get(i).get(0) == b){
					ArrayList<Integer> temp = new ArrayList<Integer>();
					for(int j = 1; j < size; j++)temp.add(a.get(i).get(j));
					c.add(temp);
				}
				
		}
		return c;
	}
	public static int[][] convertArrayListToArray(ArrayList<ArrayList<Integer>> a)
	{
		int size =  a.get(0).size();
		int outSize = a.size();
		int b[][] = new int[outSize][size];
		for(int i = 0; i < outSize; i++)
			for(int j = 0; j < size; j++)b[i][j] = a.get(i).get(j);
		
		return b;
	}
	
	public static int[][] DeleteLabel(ArrayList<ArrayList<Integer>> a)
	{
		int size =  a.get(0).size();
		int outSize = a.size();
		int b[][] = new int[outSize][size-1];
		for(int i = 0; i < outSize; i++)
			for(int j = 1; j < size; j++)b[i][j-1] = a.get(i).get(j);
		
		return b;
	}
	
	public static double computeDistance(int[] a, int[] b)
	{
		
		double child = 0;
		double mother = 0;
		int size = a.length;
		for(int i = 0; i < size; i++)
		{
			mother += (a[i] | b[i]);
			if(a[i] != 1 || b[i] != 1)child += (a[i] | b[i]);
		}
		return child/mother;
	}
}
