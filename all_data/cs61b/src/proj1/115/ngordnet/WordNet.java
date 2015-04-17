package ngordnet;
import ngordnet.WordNet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.*;

public class WordNet {

	private String synsetFilename;
	private String hyponymFilename;
	private Map<Integer, ArrayList<String>> mapSyn;
	private Digraph hypoDi;
	private int foundKey;
    private Map<String, ArrayList<Integer>> mapWord;


	// maps? 
	// .split(" , ")

	/** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME 
	 * creates a map of the synset file
	 * creates a digraph of the hyponym relationships*/
    public WordNet(String synsetFilename, String hyponymFilename) {
    	this.synsetFilename = synsetFilename;
    	this.hyponymFilename = hyponymFilename;

    	In inSyn = new In(synsetFilename);
    	In inHyp = new In(hyponymFilename);


    	mapSyn = new TreeMap<Integer, ArrayList<String>>();
        mapWord = new TreeMap<String, ArrayList<Integer>>();
    	for (String line = inSyn.readLine(); line != null; line = inSyn.readLine()) {
    		String[] splitLine = line.split(",");
            String[] splitString = splitLine[1].split(" ");

            ArrayList<String> wordList = new ArrayList<String>();
            for (int i = 0; i < splitString.length; i += 1) { // for each word in synset, adds to the array list
                  wordList.add(splitString[i]); 
            }
            mapSyn.put(Integer.parseInt(splitLine[0]), wordList); // creates a map entry, with Key = number, value = arraylist

            
            
            
            
            for (String entry : splitString) {
                int intReal = Integer.parseInt(splitLine[0]);
                
                if (mapWord.containsKey(entry)) {
                    ArrayList<Integer> update = mapWord.get(entry);
                    update.add(intReal);
                    mapWord.put(entry, update);
                } else {
                    ArrayList<Integer> number = new ArrayList<Integer>();
                    number.add(intReal);
                    mapWord.put(entry, number);
                }
                
                }
            
    	}

        
    	hypoDi = new Digraph(mapSyn.size());
    	for (String hypo = inHyp.readLine(); hypo != null; hypo = inHyp.readLine()) {
    		String[] sLine = hypo.split(",");

    		int size = sLine.length;

    		for (int i = 1; i < size; i += 1) {
    			hypoDi.addEdge(Integer.parseInt(sLine[0]), Integer.parseInt(sLine[i]));
    		}
    	}
    }


    /* Returns true if NOUN is a word in some synset.
     * Method takes in @param a string noun and returns true or false */
    public boolean isNoun(String noun) {

    	return mapWord.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
    	
        return mapWord.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */

    public Set<String> hyponyms(String word){
        ArrayList<Integer> hypoSet = mapWord.get(word); // array list of the IDs for the word
        Set<Integer> input = new TreeSet<Integer>(); //creating a new set to store the IDs, for the input of descendants
        while (!hypoSet.isEmpty()) {
            input.add(hypoSet.remove(0));
        }


    	Set<Integer> intSet = GraphHelper.descendants(hypoDi, input);
    	Integer[] array = intSet.toArray(new Integer[0]);
    	Set<String> sSet = new TreeSet<String>();

    	for (int i = 0; i < array.length; i += 1) {
            ArrayList<String> find = mapSyn.get(array[i]);
            while (!find.isEmpty()) {
                sSet.add(find.remove(0));
            }
    	}
        sSet.add(word);
    	return sSet;
        //return null;
    } 
    
    public static void main(String[] args) {
        WordNet wn = new WordNet("./ngordnet/synsets11.txt", "./ngordnet/hyponyms11.txt");

        /* These should all print true. */
        System.out.println(wn.isNoun("jump"));
        System.out.println(wn.isNoun("leap"));
        System.out.println(wn.isNoun("nasal_decongestant"));

        /* The code below should print the following (maybe not in this order): 
            All nouns:
            augmentation
            nasal_decongestant
            change
            action
            actifed
            antihistamine
            increase
            descent
            parachuting
            leap
            demotion
            jump
        */

        System.out.println("All nouns:");
        for (String noun : wn.nouns()) {
            System.out.println(noun);
        }

        /* The code below should print the following (maybe not in this order): 
            Hypnoyms of increase:
            augmentation
            increase
            leap
            jump
        */

        System.out.println("Hypnoyms of increase:");
        for (String noun : wn.hyponyms("increase")) {
            System.out.println(noun);
        }

        /* The code below should print the following (maybe not in this order): 
            Hypnoyms of jump:
            parachuting
            leap
            jump
        */

        System.out.println("Hypnoyms of jump:");
        for (String noun : wn.hyponyms("jump")) {
            System.out.println(noun);
        }  

        /* The code below should print the following (maybe not in this order):
            Hypnoyms of change:
            alteration
            saltation
            modification
            change
            variation
            increase
            transition
            demotion
            leap
            jump        
        */

        /** From: http://goo.gl/EGLoys */
        System.out.println("Hypnoyms of change:");

        WordNet wn2 = new WordNet("./ngordnet/synsets14.txt", "./ngordnet/hyponyms14.txt");
        for (String noun : wn2.hyponyms("change")) {
            System.out.println(noun);
        }              
    }

    
    
}
