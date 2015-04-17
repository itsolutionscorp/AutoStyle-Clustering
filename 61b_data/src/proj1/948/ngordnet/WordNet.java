package ngordnet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.TreeSet;
import java.util.Scanner;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
  private Map<Integer, String[]> wordMap = new HashMap<Integer, String[]>();
  private Map<Integer, List<Integer>> hypMap = new HashMap<Integer, List<Integer>>();
	
	public WordNet(String synsetFilename, String hyponymFilename) {
		
	    File sFile = new File(synsetFilename);
	    Scanner synFile = null;
	    try {
	        synFile = new Scanner(sFile);
	    } catch (FileNotFoundException e) {
	        System.err.println("Caught FileNotFoundException: " + e.getMessage());
	    }
	    while (synFile.hasNextLine() == true && synFile != null) {
	        String line = synFile.nextLine();
	        Scanner lineBreaker = new Scanner(line);
	        lineBreaker.useDelimiter(",");
	        
	        int tempInt = lineBreaker.nextInt();
	
	        String tempString = lineBreaker.next();
	        String[] tempArr = tempString.split(" ");
	
	        Integer intObj = new Integer(tempInt);
	        wordMap.put(intObj, tempArr);
	    }
		
	    File hFile = new File(hyponymFilename);
	    Scanner hypFile = null;
	    try {
	        hypFile = new Scanner(hFile);
	    } catch (FileNotFoundException e) {
	        System.err.println("Caught FileNotFoundException: " + e.getMessage());
	    }
	    while (hypFile.hasNextLine() == true && hypFile != null) {
	        String line = hypFile.nextLine();
	        Scanner lineBreaker = new Scanner(line);
	        lineBreaker.useDelimiter(",");
	
	        List<Integer> tempSet = new ArrayList<Integer>();
	        Integer tempInt = lineBreaker.nextInt();
	
	        while (lineBreaker.hasNext()) {
	            tempSet.add(lineBreaker.nextInt());
	        }
	        hypMap.put(tempInt, tempSet);
	    }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
    	
        Iterator itr = wordMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry)itr.next();
            String[] tempVals = (String[]) entry.getValue();
            for (int i = 0; i < tempVals.length; i += 1) {
                if (tempVals[i].contains(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
    
    	Set<String> returnSet = new TreeSet<String>();

    	Iterator itr = wordMap.entrySet().iterator();
    	while (itr.hasNext()) {
        Map.Entry entry = (Map.Entry)itr.next();
        String[] tempVals = (String[]) entry.getValue();
        for (int k = 0; k < tempVals.length; k += 1) {
            returnSet.add(tempVals[k]);
        	}
    	}
    	return returnSet;
	}

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> returnSet = new TreeSet<String>();
        Integer permIndex = null;
        ArrayList<Integer> nextCheck = null;

        Iterator itr = wordMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry)itr.next();
            String[] tempVals = (String[]) entry.getValue();
            Integer tempIndex = (Integer) entry.getKey();
            for (int i = 0; i < tempVals.length; i += 1) {
                if (word.equals(tempVals[i])) {
                    for (int j = 0; j < tempVals.length; j += 1) {
                        returnSet.add(tempVals[j]);
                        permIndex = tempIndex;
                    }
                }
            }
        }
        // MAKE SURE TO GET ALL VALUES!!!! FIX THIS!!!! currently only finding the first values that it comes upon
        
        Iterator itr2 = hypMap.entrySet().iterator();
        while (itr2.hasNext()) {
            Map.Entry entry = (Map.Entry)itr2.next();
            ArrayList<Integer> tempVals = (ArrayList<Integer>) entry.getValue();
            Integer tempIndex = (Integer) entry.getKey();
            if (permIndex.equals(tempIndex)) {
            	nextCheck = tempVals;
            }
        }
        
        Iterator itr3 = wordMap.entrySet().iterator();
        while (itr3.hasNext()) {
        	Map.Entry entry = (Map.Entry)itr3.next();
        	String[] tempVals = (String[]) entry.getValue();
        	Integer tempIndex = (Integer) entry.getKey();
	        if (nextCheck != null) {
	        	for (int i = 0; i < nextCheck.size(); i += 1) {
	        		if (tempIndex.equals(nextCheck.get(i))) {
	        			for (int j = 0; j < tempVals.length; j += 1) {
	        				returnSet.add(tempVals[j]);
	        			}
	        		}
	        	}
	        }
        }

        while (returnSet.contains(word)) {
            returnSet.remove(word);
        }
        returnSet.add(word);
        return returnSet;
    }
}
