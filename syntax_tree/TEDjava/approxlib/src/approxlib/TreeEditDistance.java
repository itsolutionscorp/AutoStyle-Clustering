package approxlib;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

import distance.EditDist;
import tree.LblTree;

public class TreeEditDistance {

	public static void main(String[] args) {
			String home_dir = args[0];
		 	String fs2;
		 	File folder = new File(home_dir+"/ast_dump/");
		 	File[] listOfFiles = folder.listFiles(); 
		 	LblTree lt1 =null, lt2 = null;
		 	int latest = listOfFiles.length-1;
		 	try {
	             lt1 = LblTree.fromString((new BufferedReader(new FileReader(home_dir+"/ast_dump/" +latest+".ast" ))).readLine());
		 	} catch (Exception e) {
	             System.out.println("TREE1 argument has wrong format "+e.toString());
	         }
		 	for (int i = 0; i < listOfFiles.length-1; i++) 
		 	{
		 	 fs2 = home_dir+"/ast_dump/"+listOfFiles[i].getName();
		 		try {
		             lt2 = LblTree.fromString((new BufferedReader(new FileReader(fs2))).readLine());
		         } catch (Exception e) {
		             System.out.println(fs2 + " argument has wrong format " + e.toString());
		         }
		         System.out.print(new EditDist(true).nonNormalizedTreeDist(lt1,lt2)+",");
		 	}
	}
}
