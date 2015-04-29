package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Arrays;

public class WordNet {
    private Map synsetMap = new HashMap<Integer, ArrayList<String>>();
    private Map hyponymMap = new HashMap<Integer, ArrayList<Integer>>();
    private Digraph hypograph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        this.synsetToMap(synsetFilename);
        this.hyponymToMap(hyponymFilename);
        this.mapToGraph(hyponymMap);
    } 

    /* Pulls synset data from file and organizes the data into a HasMap with integer 
    synset keys and string values organized into an ArrayList.*/
    private void synsetToMap(String synsetFilename) {
        In in = new In(synsetFilename);
        while (!in.isEmpty()) {
            String curr = in.readLine();
            ArrayList<String> tempAList = new ArrayList<String>(Arrays.asList(curr.split(",")));
            int key = Integer.parseInt((String) tempAList.get(0));
            String synonyms = (String) tempAList.get(1);
            ArrayList<String> value = new ArrayList<String>(Arrays.asList(synonyms.split(" ")));
            synsetMap.put(key, value);
        }
    }

    /* Pulls hyponym data from file and organizes the data into a HashMap with integer hypernym 
    keys and integer hyponym values */
    private void hyponymToMap(String hyponymFilename) {
        In in = new In(hyponymFilename);
        while (!in.isEmpty()) {
            String curr = in.readLine();
            ArrayList<String> strHyponyms = new ArrayList<String>(Arrays.asList(curr.split(",")));
            ArrayList<Integer> intHyponyms = new ArrayList<Integer>();
            int key = Integer.parseInt((String) strHyponyms.get(0));
            for (int i = 0; i <= strHyponyms.size() - 1; i += 1) {
                int convertHyp = Integer.parseInt((String) strHyponyms.get(i));
                intHyponyms.add(convertHyp);
                if (hyponymMap.containsKey(key)) {
                    ArrayList doubleKeys = (ArrayList) hyponymMap.get(key);
                    doubleKeys.add(convertHyp);
                    hyponymMap.remove(key);
                    hyponymMap.put(key, doubleKeys);
                } else {
                    hyponymMap.put(key, intHyponyms);
                }
            }
        }
    }

    /*Creates a digraph of a Map toGraph)*/
    private void mapToGraph(Map toGraph) {
        hypograph = new Digraph(synsetMap.size());
        for (int key : (Set<Integer>) hyponymMap.keySet()) {
            for (int hypKey : (ArrayList<Integer>) hyponymMap.get(key)) {
                hypograph.addEdge(key, hypKey);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (!findKey(noun).isEmpty()) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set nounSet = new HashSet<String>();
        for (int i = 0; i <= synsetMap.size() - 1; i += 1) {
            nounSet.addAll((Collection) synsetMap.get(i));
        }
        return nounSet;
    }

    /* Returns all the keys of synsets where word can be found. Returns keys in ArrayList.*/
    private Set<Integer> findKey(String word) {
        Set keyset = new HashSet();
        //for (int key : (ArrayList<Integer>) synsetMap.values()) {
        for (int i = 0; i <= synsetMap.size() - 1; i += 1) {
            ArrayList<String> currVal = (ArrayList) synsetMap.get(i);
            if (currVal.contains(word)) {
                keyset.add(i);
            }
        }
        return keyset;
    } 

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set holder = new HashSet();
        if (isNoun(word)) {
            Set currKeys = findKey(word); //AList of keys for initial word
            for (int currKey : (Set<Integer>) currKeys) {
                holder.addAll((Collection) synsetMap.get(currKey));
            }
            for (int key : (Set<Integer>) GraphHelper.descendants(hypograph, currKeys)) {
                holder.addAll((Collection) synsetMap.get(key));
            }
        }
        return holder;
    }
}



