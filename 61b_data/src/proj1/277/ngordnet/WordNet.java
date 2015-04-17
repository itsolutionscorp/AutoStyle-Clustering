package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;


public class WordNet {
    private HashMap<String, ArrayList<String>> syn;
    private HashMap<String, ArrayList<String>> hyp;
    private ArrayList<String> targetKeys = new ArrayList<String>();
    private ArrayList<String> hyponymIDs = new ArrayList<String>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synIn = new In(synsetFilename);
        In hypIn = new In(hyponymFilename);
        ArrayList<String[]> synLines = new ArrayList<String[]>();
        ArrayList<String[]> hypLines = new ArrayList<String[]>();

        syn = new HashMap<String, ArrayList<String>>();
        hyp = new HashMap<String, ArrayList<String>>();

        // Add each synonym line (as a String array) to synLines
        while (synIn.hasNextLine()) {
            synLines.add(synIn.readLine().split(","));
        }
        // Add to the syn Map: key = ID, value = synset of word(s)
        for (String[] line: synLines) {
            String putID = line[0];
            List<String> l = Arrays.<String>asList(line[1].split(" "));
            ArrayList<String> putValues = new ArrayList<String>(l);
            syn.put(putID, putValues);
        }

        // Add each hyponym line (as a String array) to hypLines
        while (hypIn.hasNextLine()) {
            hypLines.add(hypIn.readLine().split(","));
        }

        // Adds to hyp Map: key = ID, value = hyponyms
        for (String[] line: hypLines) {
            String id = line[0];
            ArrayList<String> hypVals = new ArrayList<String>();
            for (String s: line) {
                if (!s.equals(id)) {
                    hypVals.add(s);
                }
            }
            if (hyp.containsKey(id)) {
                ArrayList<String> values = hyp.get(id);
                for (String v: hypVals) {
                    values.add(v);
                }
                hyp.put(id, values);
            } else {
                hyp.put(id, hypVals);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (nouns().contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> s = new HashSet<String>();
        for (ArrayList<String> val: syn.values()) {
            for (String word: val) {
                s.add(word);
            }
        }
        return s;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        
        ArrayList<ArrayList<String>> synonyms = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> hyponyms = new ArrayList<ArrayList<String>>();

        targetKeys.clear();
        hyponymIDs.clear();

        // Create lists of target keys + synonyms (includes word)
        for (String key: syn.keySet()) {
            ArrayList<String> vals = syn.get(key);
            for (String s: vals) {
                if (s.equals(word)) {
                    targetKeys.add(key);
                    synonyms.add(vals);
                }
            }
        }

        // Remove the word(s) from the synonyms list */
        for (String key: targetKeys) {
            synonyms.remove(word);
        }

        // Make a copy to iterate
        ArrayList<String> targetKeysCopy = new ArrayList<String>();
        for (String str: targetKeys) {
            targetKeysCopy.add(str);
        }

        // Find hyponym IDs
        for (String k: targetKeysCopy) {
            findHyponymIDs(k);
        }

        // Match hyponym IDs with synset words 
        for (String id: hyponymIDs) {
            if (syn.keySet().contains(id)) {
                hyponyms.add(syn.get(id));
            }
        }


        // Add synonyms to returnSet 
        Set<String> returnSet = new HashSet<String>();
        for (ArrayList<String> s: synonyms) {
            for (String wd: s) {
                returnSet.add(wd);
            }
        }

        // Add hyponyms to returnSet
        for (ArrayList<String> h: hyponyms) {
            for (String wrd: h) {
                returnSet.add(wrd);
            }
        }
        return returnSet;
    }


    private void findHyponymIDs(String id) {
        Set<String> hypKeySet = hyp.keySet();
        if (!hypKeySet.contains(id)) {
            hyponymIDs.add(id);
        } else { // ID is a key -- has one or more values
            for (String val: hyp.get(id)) {
                hyponymIDs.add(id);
                findHyponymIDs(val);
            }
        }
    }
}
