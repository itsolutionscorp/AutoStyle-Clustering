package approxlib;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

import distance.EditDist;
import tree.LblTree;

public class TreeEditDistance {

	public static void main(String[] args) {
//		long startTime = System.nanoTime();
			String home_dir = args[0];
			String ast_dir = "/ast/";
			if (args.length > 1 && args[1].equals("ruby")){
				ast_dir = "/jast/";
			}
		 	String fs2;
		 	File folder = new File(home_dir+ast_dir);
		 	File[] listOfFiles = folder.listFiles(new FilenameFilter() {
		 	    public boolean accept(File dir, String name) {
		 	        return name.toLowerCase().endsWith("ast");
		 	    }
		 	});
		 	Arrays.sort(listOfFiles, 
		 			  new Comparator<File>() {
		 			    public int compare(File item1, File item2) {
		 			        return ((Integer) Integer.parseInt(item1.getName().split("[._a-z]")[0]))
		 			        		.compareTo((Integer) Integer.parseInt(item2.getName().split("[._a-z]")[0]));
		 			    }
		 			  }
		 	);
		 	LblTree lt1 =null, lt2 = null;
		 	try {
	             lt1 = LblTree.fromString((new BufferedReader(new FileReader(home_dir+ast_dir + listOfFiles[listOfFiles.length - 1].getName() ))).readLine());
		 	} catch (Exception e) {
	             System.out.println("TREE1 argument has wrong format "+e.toString());
	         }
		 	String output = "";
		 	for (int i = 0; i < listOfFiles.length-1; i++) 
		 	{
		 	 fs2 = home_dir+ast_dir+listOfFiles[i].getName() ;
		 		try {
		             lt2 = LblTree.fromString((new BufferedReader(new FileReader(fs2))).readLine());
		         } catch (Exception e) {
		             System.out.println(fs2 + " argument has wrong format " + e.toString());
		         }
		 		output+=new EditDist(true).nonNormalizedTreeDist(lt1,lt2)+",";
		 	}
		 	System.out.print(output);
//		 	long endTime = System.nanoTime();
//		 	System.out.println("\n" + (endTime - startTime)/(float)1000000000);
	}
}
