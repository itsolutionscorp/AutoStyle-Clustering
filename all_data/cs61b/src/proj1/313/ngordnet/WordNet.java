package ngordnet;

import java.util.Set;
import java.util.HashSet;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.File;  
//import java.io.FileInputStream;  
import java.io.FileReader;
import edu.princeton.cs.algs4.Digraph;
//import edu.princeton.cs.algs4.DirectedDFS;



public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private int count = 0, line1 = 0;
    private int NN = 100000, NNN = 51, NNNN = 5;
    private String[][] hy = new String[NN][NNNN];
    private String[][] cy = new String[NN][NNN];

    public WordNet(String synsetFilename, String hyponymFilename) {
	File file = new File(synsetFilename);  
	BufferedReader reader = null;  
	try {  
	    reader = new BufferedReader(new FileReader(file));  
	    String tempStr = null;  

	    while ((tempStr = reader.readLine()) != null) {   
		hy[count] = tempStr.split(",");
		count += 1;  
	    }  
	    reader.close();  
	} catch (IOException e) {  
	    e.printStackTrace();  
	} finally {  
	    if (reader != null) {  
		try {  
		    reader.close();  
		} catch (IOException e1) {  
		}  
	    }  
	}
	File file1 = new File(hyponymFilename);  
	BufferedReader reader1 = null;  
	try {  
	    reader1 = new BufferedReader(new FileReader(file1));  
	    String tempStr1 = null;  

	    while ((tempStr1 = reader1.readLine()) != null) {  
		cy[line1++] = tempStr1.split(",");
		//line1 += 1;  
	    }  
	    reader1.close();  
	} catch (IOException e) {  
	    e.printStackTrace();  
	} finally {  
	    if (reader1 != null) {  
		try {  
		    reader1.close();  
		} catch (IOException e1) {  
		}  
	    }  
	}

    }
   
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
	String ddy[]; 
 	Set<String> x = new HashSet<String>();
	for (int i = 0; i < count; i += 1) {
	  
	    for (String k : hy[i][1].split(" ")) {
		x.add(k);
	    }
	}
	return x;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
	String ddy[]; 
	for (int i = 0; i < count; i += 1) {
	    ddy = hy[i][1].split(" ");
	    for (String kh : ddy) {
		if (kh.equals(noun)) {
		    return true;
		}
	    }
	}
	return false;
    }


    public Set<String> hyponyms(String word) {
	Set<String> x = new HashSet<String>();
	Set<Integer> z = new HashSet<Integer>();
	Set<Integer> y = new HashSet<Integer>();
	int age;
	String ddy[];
	for (int i = 0; i < count; i++) {
	   
	    for (String k : hy[i][1].split(" ")) {
		if (k.equals(word)) {
		    z.add(i);
		}
	    }
	}
	Digraph cynthia = new Digraph(count);
	for (int j = 0; j < line1; j += 1) {
	    for (int i = 1; i < cy[j].length; i += 1) {
		if (cy[j][i] != null) {
		    cynthia.addEdge(Integer.parseInt(cy[j][0]), Integer.parseInt(cy[j][i]));
		}
	    }
	}

	y = GraphHelper.descendants(cynthia, z);
	for (Integer zy : y) {
	    
	    for (String k : hy[zy][1].split(" ")) {
		x.add(k);
	    }
	}
	for (Integer zzy :z) {
	
	    for (String k : hy[zzy][1].split(" ")) {
		x.add(k);
	    }
	}
	return x;
    }
}
