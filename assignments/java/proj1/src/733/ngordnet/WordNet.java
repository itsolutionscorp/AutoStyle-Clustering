package ngordnet;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;
import edu.princeton.cs.algs4.Digraph;



public class WordNet extends java.lang.Object {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
	String line = "";
	String splitBy = ",";
	Integer size = 0;
	Set<String> allnouns = new HashSet<String>();
	Map<String, Set<String>> wordhash = new HashMap<String, Set<String>>(); //hashes by id as a string
	Map<String, Set<Integer>> nounhash = new HashMap<String, Set<Integer>>(); // hashes by noun as a string
	Digraph dg;

    public WordNet(String synsetFilename, String hyponymFilename) {
    	try { 	
			BufferedReader synsetfile = new BufferedReader(new FileReader(synsetFilename));
			BufferedReader hypofile = new BufferedReader (new FileReader(hyponymFilename));



		//Create your graph or your ADT of however you want to hold the data
			while ((line = synsetfile.readLine()) != null) {
				String[] array_of_synsets = line.split(splitBy);
				String id = array_of_synsets[0];
				String noun_string = array_of_synsets[1];
				//Add each noun and id based upon the file

				String space = " ";
				String[] noun_list = noun_string.split(space);
				for (String noun : noun_list){
					this.allnouns.add(noun);
					if (! this.wordhash.containsKey(id)){
						this.wordhash.put(id, new HashSet<String>());

					}
					this.wordhash.get(id).add(noun); 


					if (! this.nounhash.containsKey(noun)){
						this.nounhash.put(noun, new HashSet<Integer>());

					}				
					this.nounhash.get(noun).add(Integer.parseInt(id));
					this.size ++; 
				}
			}
			// Do the same Buffered Reader logic for hyponym file
			//Add edges based upon each one
			line = "";
			this.dg = new Digraph(this.size);
			while ((line = hypofile.readLine()) != null) {
				String[] array_of_hyposets = line.split(splitBy);
				String nodeid = array_of_hyposets[0];
				for (int i = 1; i < array_of_hyposets.length; i++){
					dg.addEdge(Integer.parseInt(nodeid),Integer.parseInt(array_of_hyposets[i]));
					//System.out.println("Added edge between " + nodeid + " and " + array_of_hyposets[i]);
				}
			}
		}
		catch (Exception e)
		    {
		       e.printStackTrace(System.out);
		    }
	}

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun){
    	if (this.nounhash.containsKey(noun)){
    		return true;
    	}
    	return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns(){
    	return this.allnouns; 
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word){
    	//System.out.println(this.dg);
    	Set<Integer> a = GraphHelper.descendants(this.dg, this.nounhash.get(word));
    	//System.out.println(this.wordhash);
    	HashSet<String> output = new HashSet<String>();
	    for (int dec : a) {
	        Set<String> nameset = this.wordhash.get(String.valueOf(dec));
	        for (String name : nameset) { //all the names of the id provided
	        	output.add(name);
	        }
	    }
	    return output;
	}
}