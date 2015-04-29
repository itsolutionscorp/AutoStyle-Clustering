/*What do I have to import? 
**What package does this belong to?*/
package ngordnet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Collection;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;

public class WordNet {
	/** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
	/*A word net is a relationship in between the elements of the two files given above.*/

	// there is going to need variable or ADT declarations to store the data through the constructor.
	//Chosen ADTS are both going to be java hashmaps.
    private HashMap<Integer, HashSet<String>> synset;
	private HashMap<Integer, HashSet<Integer>> hyponyms; 
	private HashSet<String> hyponymsOfWord; // used only in the hyponyms method. Need to be here to be accessed by helper. 
	/*this method is going to put the elements of the given files into the chosen ADTs*/
	/* might need to add exception handling*/
    public WordNet(String synsetFilename, String hyponymFilename) { 
    	try {
	    	File s = new File(synsetFilename);
	    	File h = new File(hyponymFilename);
	    	Scanner synsetStream = new Scanner(s);
	    	Scanner hyponymsStream = new Scanner(h);
	    	synset = new HashMap<Integer, HashSet<String>>();
	    	hyponyms = new HashMap<Integer, HashSet<Integer>>();
	    	// while loop below puts the sysnset data into our synset map
	    	while (synsetStream.hasNextLine()) { // makes sure there is a next line
	    		String currLine = synsetStream.nextLine();
	    		String[] line = currLine.split(","); // turns the line gotten above into an array
	    		int indexS = Integer.parseInt(line[0]); // the first element of the line is an integer.
	    		HashSet<String> valS = new HashSet<String>(); 
	    		String[] synonyms = line[1].split(" "); // the second element is a string with whitespace.
	    		for (int n = 0; n < synonyms.length; n++) { // prevents null errors
	    			valS.add(synonyms[n]); 
	    		}
	    		HashSet<String> temp0 = new HashSet<String>();
	    		if (synset.containsKey(indexS)) {
	    			temp0 = synset.get(indexS);
	    			for (String e: valS) {
	    				temp0.add(e);
	    			}
	    			synset.put(indexS, temp0);
	    		} else {
	    			synset.put(indexS, valS);
	    		}	
	    	}
	    	//while loop below puts hyponym data into our hyponym map. 
	    	while (hyponymsStream.hasNextLine()) {
	    		String currLine = hyponymsStream.nextLine();
	    		String[] line = currLine.split(",");
	    		HashSet<Integer> valH = new HashSet<Integer>();
	    		LinkedList<Integer> elementH = new LinkedList<Integer>();
	    		for (int n = 0; n < line.length; n++ ) {
	    			elementH.add(Integer.parseInt(line[n]));
	    		}
	    		int indexH = elementH.removeFirst();
	    		while (!elementH.isEmpty()) {
	    			valH.add(elementH.remove());
	    		}
	    		HashSet<Integer> temp1 = new HashSet<Integer>();
	    		if (hyponyms.containsKey(indexH)) {
	    			temp1 = hyponyms.get(indexH);
	    			for(int e: valH) {
	    				temp1.add(e);
	    			}
	    			hyponyms.put(indexH, temp1);
	    		} else {
	    			hyponyms.put(indexH, valH);
	    		}
	    	}
    	} catch (FileNotFoundException e) {
    		System.out.println("the file was not found");
		}
    	
    } 

    /* Returns true if NOUN is a word in some synset. */
    
    public boolean isNoun(String noun) {
    	Set<String> synsetNouns = nouns();
    	if (synsetNouns.contains(noun)) {
    		return true;
    	} else {
    		return false;
    	}	
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
    	Collection<HashSet<String>> synsetvals = synset.values();
    	HashSet<String> nouns = new HashSet<String>();
    	for (Set<String> setS : synsetvals) {
    		for (String s: setS) {
    			nouns.add(s);
    		}
    	}
    	return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
    	if (!isNoun(word)) { // verifies the word given is in the synset. Should avoid NullPointers. 
    		throw new UnsupportedOperationException();
    	}
    	HashSet<Integer> wordkey = new HashSet<Integer>();
    	Set<Integer> synsetkeyes = synset.keySet(); // value set of synset. 
    	for (int key : synsetkeyes) { // gets the key to the passed in value. 
    		if (synset.get(key).contains(word)) { 
    			wordkey.add(key); // loop finds the word in the synset. 
    		}
    	}
    	hyponymsOfWord = new HashSet<String>();
    	int counter = 0; // used to verify if all instances of variable word in synset have a hyponym. 
    	for (int key: wordkey) { 
	    	for (String val : synset.get(key)) { // synsnet.get(wordkey) is going to return a Set of Strings. 
	    		hyponymsOfWord.add(val); // adds the word given and its synonyms to the set of hyponyms we will return 
	    	}
    		if (!hyponyms.containsKey(key)) { // checks is the word has hyponyms.  
    			counter += 1;
    		}
		}
		if(counter != 0) {
			return hyponymsOfWord;
		}
		for (int key: wordkey) {
			if (hyponyms.containsKey(key)) {
			   	for (int n : hyponyms.get(key)) { //loops through the array of hyponyms
				   	for (String val : synset.get(n)) { // loops through the set that is the value associted to the key n.  
			    			hyponymsOfWord.add(val); // adds the word given and its synonyms to the set of hyponyms we will return 
			    		} 
		    		if (hyponyms.containsKey(n)) { 
		    			//call to the helper
		    			helper(n);
		    		}
		    	}
		    }
	    }
    	return hyponymsOfWord;
    }
 
    private void helper(int n) {
    	if (!hyponyms.containsKey(n)) {
    		return;
    	}
    	else {
    		for (int k : hyponyms.get(n)) { //oops through the array of hyponyms
			   	for (String val : synset.get(k)) { // loops through the set that is the value associted to the key n.  
		    		hyponymsOfWord.add(val); // adds the word given and its synonyms to the set of hyponyms we will return 
		    	}
		    	helper(k);
    		}
    	return;
    	}
    }


}