package ngordnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.princeton.cs.introcs.In;


public class WordNet {
    
    private HashMap<Integer, String[]> synMap = new HashMap<Integer, String[]>();
    private HashMap<String, List<Integer>> synMapInv = new HashMap<String, List<Integer>>();
    private HashMap<Integer, List<Integer>> hypoMap = new HashMap<Integer, List<Integer>>();
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        addSynset(synsetFilename);
        addHyponym(hyponymFilename);
     

      /**
       *  may need other data structures for wordnet methods
       */
    }

    private void addSynset(String sFileName) {
        In sFile = new In(sFileName);
        while (!sFile.isEmpty()) {
            String sLine = sFile.readLine();
            String[] sFields = sLine.split(",");
            Integer sKey = Integer.valueOf(sFields[0]); // Stores Synset ID
            String sValues = sFields[1]; // Stores Synset      "word1 word2"
            String[] synWords = sValues.split(" "); // [word1, word2]

            synMap.put(sKey, synWords);

            for (String synWord : synWords) {
                List<Integer> ids = synMapInv.get(synWord);
                
                if (ids == null) { // contains
                    ids = new ArrayList<Integer>();
                    synMapInv.put(synWord, ids);
                }
                ids.add(sKey);
                    
            }
            /**
             * if (noun already exists in synMapInv)
             *      add to integer array
             * else
             *      synMapInv.put(stuff)
             * end
             * 
             */
        }
    }

    private void addHyponym(String hFileName) {
        In hFile = new In(hFileName);
        while (!hFile.isEmpty()) {
            String hLine = hFile.readString();
            String[] hFields = hLine.split(","); // ["0", "1", "2"]
            String hKey = hFields[0]; // Stores Synset ID
            String[] hValues = new String[hFields.length - 1];
            // Copies Hyponyms into new string array
            System.arraycopy(hFields, 1, hValues, 0, hFields.length - 1);
            
            List<Integer> hValuesInt = hypoMap.get(Integer.valueOf(hKey));
            if (hValuesInt == null) {
                hValuesInt = new ArrayList<Integer>();
            }
            
            // change string array to integer array
            for (int i = 0; i < hValues.length; i = i + 1) {
                hValuesInt.add(Integer.valueOf(hValues[i]));
            }

            hypoMap.put(Integer.valueOf(hKey), hValuesInt);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synMapInv.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synMapInv.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        
        Set<String> result = new HashSet<String>();
        
        List<Integer> synIDs = synMapInv.get(word);
        
        if (synIDs != null) {
            for (int i : synIDs) {
                result.addAll(findSynonyms(i));
                result.addAll(findHyponyms(i));
            }
        }
        
        return result;
        
        /**
         * Given "increase"
         * look into synMapInv to find synset ID's that correspond to increase
         * use synset ID's to find synonyms in synMap, return synonyms
         * use synset ID's to find hyponyms (other synset ID's) in hypoMap
         * use hyponym synset ID's to find hyponyms in synMap, return
         * 
         */
    }
    
    private Set<String> findSynonyms(int id) {
        Set<String> hyponyms = new HashSet<String>();
        String[] synTemp = synMap.get(id);
        for (int i = 0; i < synTemp.length; i = i + 1) {
            hyponyms.add(synTemp[i]);
        }
        
        return hyponyms;        
    }
    
    private Set<String> findHyponyms(int id) {
        Set<String> hyponyms = new HashSet<String>();
        List<Integer> hypoTemp = hypoMap.get(id); // [1] are hyponyms of synset 0
        
        // check for no hyponyms
        if (hypoTemp == null) {
            return hyponyms;
        }
        
        int size = hypoTemp.size();
        for (int i = 0; i < size; i = i + 1) {
            Set<String> synonyms = findSynonyms(hypoTemp.get(i));
            hyponyms.addAll(synonyms);
            hyponyms.addAll(findHyponyms(hypoTemp.get(i)));
        }
        
        return hyponyms;
    }
}
