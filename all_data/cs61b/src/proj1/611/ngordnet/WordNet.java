package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;


public class WordNet {

    //Stores the hyponyms of a particular hyponym
    private ArrayMap<Integer, ArrayList<Integer>> hypoMap;
    
    //Stores the contents of a particular synset
    private ArrayMap<Integer, ArrayList<String>> synsetMap;
    
    //Stores the location of a word (in a particular synset)
    private ArrayMap<String, ArrayList<Integer>> wordMap;
    
    private int maxSynset;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        hypoMap = new ArrayMap<Integer, ArrayList<Integer>>();
        synsetMap = new ArrayMap<Integer, ArrayList<String>>();
        wordMap = new ArrayMap<String, ArrayList<Integer>>();

        maxSynset = Integer.MIN_VALUE;
        
        In reader = new In(synsetFilename);

        if (reader == null) {
            System.exit(1);
        }

        String[] readInfo = null;
        while (reader.hasNextLine()) {
            readInfo = reader.readLine().split(",");
            int id = Integer.parseInt(readInfo[0]);
            String[] synsetWords = readInfo[1].split(" ");
            if (synsetWords == null) {
                throw new NullPointerException();
            }
            for (int i = 0; i < synsetWords.length; i++) {
                String withoutAnd = synsetWords[i];
                ArrayList<String> stringAdd = new ArrayList<String>();
                stringAdd.add(withoutAnd);
                synsetMap.put(id, stringAdd);
                ArrayList<Integer> addArray = new ArrayList<Integer>();
                addArray.add(id);
                wordMap.put(withoutAnd, addArray);
            }
        }

        reader = new In(hyponymFilename);
        while (reader.hasNextLine()) {
            readInfo = reader.readLine().split(",");
            int hypernym = Integer.parseInt(readInfo[0]);

            if (hypernym > maxSynset) {
                maxSynset = hypernym;
            }

            int[] hyponyms = new int[readInfo.length - 1];
            for (int j = 1; j < readInfo.length; j++) {
                int current = Integer.parseInt(readInfo[j]);
                hyponyms[j - 1] = current;
            }

            for (int k = 0; k < hyponyms.length; k++) {
                int currentHyponym = hyponyms[k];
                
                if (currentHyponym > maxSynset) {
                    maxSynset = currentHyponym;
                }
                
                ArrayList<Integer> hyponymAdd = new ArrayList<Integer>();
                hyponymAdd.add(currentHyponym);
                hypoMap.put(hypernym, hyponymAdd);
            }
            
            ArrayList<Integer> selfAdd = new ArrayList<Integer>();
            selfAdd.add(hypernym);
            hypoMap.put(hypernym, selfAdd);
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordMap.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = wordMap.keySet();
        return nounSet;
    }

    private Set<Integer> getHyponyms(int position) {
        Set<Integer> allHyponyms = new HashSet<Integer>();
        ArrayList<Integer> currHyponyms = hypoMap.get(position);
        if (currHyponyms == null || currHyponyms.size() == 0) {
            return allHyponyms;
        } else {
            for (Integer currPosition : currHyponyms) {
                if (currPosition != position) {
                    allHyponyms.add(currPosition);
                    Set<Integer> retrievedHyponyms = getHyponyms(currPosition);
                    if (retrievedHyponyms != null) {
                        allHyponyms.addAll(retrievedHyponyms);
                    }
                }
            }
        }
        
        return allHyponyms;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */  
    public Set<String> hyponyms(String word) {
        
        if (!this.isNoun(word)) {
            return null;
        }
        
        Set<Integer> allHyponyms = new HashSet<Integer>();
        ArrayList<Integer> allPositions = wordMap.get(word);
        Set<Integer> currHyponyms = new HashSet<Integer>();
        
        for (Integer thePosition : allPositions) {
            currHyponyms.add(thePosition);
            ArrayList<Integer> retrieved = hypoMap.get(thePosition);
            if (retrieved != null) {
                currHyponyms.addAll(retrieved);
            }
        }
        
        
        
        for (Integer currPosition : currHyponyms) {
            allHyponyms.add(currPosition);
            Set<Integer> retrievedHyponyms = getHyponyms(currPosition);
            if (retrievedHyponyms != null) {
                allHyponyms.addAll(retrievedHyponyms);              
            }
        }
        
        Set<String> hypoWords = new HashSet<String>();
        
        if (allHyponyms.size() == 0) {
            return null;
        } else {
            for (Integer theHyponym : allHyponyms) {
                ArrayList<String> theWords = synsetMap.get(theHyponym);
                hypoWords.addAll(theWords);
            }
        }
        
        return hypoWords;
    }
}
