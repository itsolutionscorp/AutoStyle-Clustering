package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set; 
import java.util.HashSet; 
import java.util.ArrayList; 
import java.util.HashMap;

public class WordNet {

    private In hf; 
    private In sf; 
    private static Set<String> wordSet;
    private static HashMap<String, ArrayList<String>> hMap; 
    private static HashMap<String, ArrayList<String>> wMap;
    private static HashMap<String, ArrayList<String>> iMap; 

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        hf = new In(hyponymFilename);
        sf = new In(synsetFilename);
        this.hyponymMap();  
        this.synsetMap(); 
    }

    /** Creates a Map given the synset file: 
    Each Key represents a word 
    Each Value represents the synset ID for that word */
    private void synsetMap() {
        wMap = new HashMap<String, ArrayList<String>>();
        iMap = new HashMap<String, ArrayList<String>>(); 
        wordSet = new HashSet<String>(); 

        while (sf.hasNextLine()) {
            String curr = sf.readLine(); 
            String[] splits = curr.split(","); 
            String[] sWords = splits[1].split(" "); 
            ArrayList<String> iSet = new ArrayList<String>(); 
            for (int i = 0; (i < sWords.length); i++) {
                if (!(wordSet.contains(sWords[i]))) {
                    wordSet.add(sWords[i]); 
                }
                if (wMap.containsKey(sWords[i])) {
                    ArrayList<String> update = wMap.get(sWords[i]); 
                    update.add(splits[0]);
                    wMap.put(sWords[i], update); 
                } else {
                    ArrayList<String> cSet = new ArrayList<String>();
                    cSet.add(splits[0]);
                    wMap.put(sWords[i], cSet); 
                }
                iSet.add(sWords[i]); 
            }
            iMap.put(splits[0], iSet);
        }
    }

    /** Creates a Map given the hypnonym file: 
        Each Key represents an ID reference to a word
        Each Value represents the set of hypnonyms for that given word */
    private void hyponymMap() {
        hMap = new HashMap<String, ArrayList<String>>();
        while (hf.hasNextLine()) {
            String curr = hf.readLine(); 
            String[] currHyps = curr.split(",");
            String pointer = currHyps[0]; 
            if (hMap.containsKey(pointer)) {
                ArrayList<String> update = hMap.get(pointer); 
                if (currHyps.length > 1) {
                    for (int i = 1; (i < currHyps.length); i++) {
                        update.add(currHyps[i]); 
                    }
                } else {
                    update.add("NO HYPONYM"); 
                }
                hMap.put(pointer, update); 
            } else {
                ArrayList<String> currSet = new ArrayList<String>();  
                if (currHyps.length > 1) {
                    for (int i = 1; (i < currHyps.length); i++) {
                        currSet.add(currHyps[i]); 
                    }
                } else {
                    currSet.add("NO HYPONYM"); 
                }
                hMap.put(pointer, currSet); 
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> allWords = this.nouns();
        return allWords.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordSet; 
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        ArrayList<String> synIDs = wMap.get(word);
        Set<String> result = new HashSet<String>(); 

        for (int i = 0; (i < synIDs.size()); i++) {
            String cRef = (String) synIDs.get(i);
            ArrayList<String> hRefs = hMap.get(cRef); 
            if (hRefs != null) {
                for (int j = 0; (j < hRefs.size()); j++) {
                    if (hMap.containsKey(hRefs.get(j))) {
                        ArrayList<String> tempList = hMap.get(hRefs.get(j));
                        int z = 0;  
                        while (z != tempList.size()) {
                            hRefs.add(tempList.get(z));
                            z++; 
                        }
                    }
                    String xRef = hRefs.get(j); 
                    ArrayList<String> hypWords = iMap.get(xRef);
                    for (int k = 0; (k < hypWords.size()); k++) {
                        result.add(hypWords.get(k)); 
                    }   
                } 
            }
            ArrayList<String> synWords = iMap.get(cRef);
            for (int m = 0; (m < synWords.size()); m++) {
                result.add(synWords.get(m));
            } 
        }
        return result; 
    }
}
