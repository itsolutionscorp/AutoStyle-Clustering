package approxlib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

import distance.EditDist;
import tree.LblTree;

public class TreeEditDistanceMatrix {

	public static void main(String[] args) {
		String home_dir = args[0];
		String ast_dir = "/ast/";
		if (args.length > 1  && args[1].equals("ruby")){
			ast_dir = "/jast/";
		}
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

	 	LblTree[] trees = new LblTree[listOfFiles.length];
	 	for (int i = 0; i < listOfFiles.length; i++) 
	 	{
	 		String t = home_dir+ast_dir+listOfFiles[i].getName();
	 		try {
	             trees[i] = LblTree.fromString((new BufferedReader(new FileReader(t))).readLine());
	         } catch (Exception e) {
	             System.out.println(t + " argument has wrong format " + e.toString());
	         }	
	 	}
	 	double[][] distmatrix = new double[listOfFiles.length][listOfFiles.length];
	 	for (int i = 0; i < listOfFiles.length; i++) 
	 	{
		 	for (int j = i; j < listOfFiles.length; j++) 
		 	{
		 		double dist = new EditDist(true).nonNormalizedTreeDist(trees[i],trees[j]);
		 		distmatrix[i][j] = dist;
		 		distmatrix[j][i] = dist;
		 	}
	 	}
	 	
	 	try {
			PrintWriter out = new PrintWriter(home_dir + "/gen/java_ast_dist_matrix.np");
		 	for (int i = 0; i < listOfFiles.length; i++) 
		 	{
			 	for (int j = 0; j < listOfFiles.length; j++) 
			 	{
			 		out.printf("%f ", distmatrix[i][j]);
			 	}
			 	out.printf("\n");
		 	}
		 	out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	 	
	}

}
