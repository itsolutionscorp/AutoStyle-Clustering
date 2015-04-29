package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
// import java.util.Iterator;

public class WordNet {

    private HashMap<Integer, Set<String>> synMap;
    private HashMap<Integer, ArrayList<Integer>> hypMap; 
    private Digraph hypGraph;

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synFile = new In(synsetFilename);
        In hypFile =  new In(hyponymFilename);

        ArrayList<String> synList = new ArrayList<String>();
        ArrayList<String> hypList = new ArrayList<String>();
        synMap = new HashMap<Integer, Set<String>>();
        hypMap = new HashMap<Integer, ArrayList<Integer>>();

        int vertices = 0;

        /** Creates map with ID as key, the NOUN as the value */
        while (synFile.hasNextLine()) {
            String s = synFile.readLine();
            String[] sList = s.split(","); //every three is an integer
            int id = Integer.parseInt(sList[0]); 
            synMap.put(id, new HashSet<String>(Arrays.asList(sList[1].split(" "))));
            vertices += 1;
        }

        hypGraph = new Digraph(vertices);

        while (hypFile.hasNextLine()) {
            String h = hypFile.readLine();
            String[] hList = h.split(",");
            int firstWord = Integer.parseInt(hList[0]);

            ArrayList<Integer> hAryLst = new ArrayList<Integer>(); 
            //store subsequent nums that are the hyps
            for (int i = 1; i < hList.length; i++) {
                int hypWord = Integer.parseInt(hList[i]);
                hAryLst.add(hypWord);
                hypGraph.addEdge(firstWord, hypWord);
            }
            hypMap.put(firstWord, hAryLst);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        /** courtesy http://stackoverflow.com/questions/1066589/iterate-through-a-hashmap */
        for (Integer key : synMap.keySet()) {
            if (synMap.get(key).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        if (synMap == null) {
            throw new NullPointerException();
        }

        Set<String> nounSet = new HashSet<String>();
        for (Integer key : synMap.keySet()) {
            for (String eachVal : synMap.get(key)) {
                nounSet.add(eachVal);
            }
        }
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */

    public Set<String> hyponyms(String word) {

        HashSet<Integer> getHyps = new HashSet<Integer>();
        Set<Integer> dOutput = new HashSet<Integer>();
        Set<String> hypSet = new HashSet<String>();

        for (Integer key : synMap.keySet()) {
            Set<String> innerSet = synMap.get(key);
            if (innerSet.contains(word)) {
                getHyps.add(key);
            }
        }

        dOutput = GraphHelper.descendants(hypGraph, getHyps);
        for (int synID : dOutput) {
            for (String s : synMap.get(synID)) {
                hypSet.add(s);
            }
        }
        return hypSet;
    }
}
