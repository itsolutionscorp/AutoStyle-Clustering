package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;


public class WordNet  {
    // Stores the directional information between word vertices
    private Digraph dg;
    // Store the set of word and their wordIDs (since a word can appear in multiple vertices)
    private HashMap<String, HashSet<Integer>> wMap;
    // Store set of words in a list (list index equals the wordID)
    private ArrayList<HashSet<String>> vList;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {          
        wMap = new HashMap<String, HashSet<Integer>>();
        vList = new ArrayList<HashSet<String>>();

        //variable declaration
        String nextln;
        String[] lnAr;
        String[] nounAr;
        int num = 0;
        boolean isDummy = true;
        HashSet<String> vEntry;

        In synFile = new In(synsetFilename);
        // deals with Synsets!
        while (synFile.hasNextLine()) {
            nextln = synFile.readLine();
            lnAr = nextln.split(",");
            num = Integer.parseInt(lnAr[0]);
            nounAr = lnAr[1].split(" ");
            // add the noun (in nounAr) and num into wMap
            vEntry = new HashSet<String>();
            for (String noun: nounAr) {
                addsynset(noun, num);
                vEntry.add(noun);
            }
            // add all the nouns into vList
            vList.add(num, vEntry);

            /* for testing purposes
            if (!lnAr[2].equals("dummy") && isDummy) {
                System.out.println("The line is not dummy, but " + lnAr[2]);
                isDummy = false;
            }*/
        }

        addHyponym(hyponymFilename, num);
    }

    private void addHyponym(String hyponymFilename, int num) {
        //variable declaration
        String nextln;
        String[] lnAr;

        In hypFile = new In(hyponymFilename);
        //create new digraph. V number determined from last file
        dg = new Digraph(num + 1); 

        int v;
        int w;
        while (hypFile.hasNextLine()) {
            nextln = hypFile.readLine();
            lnAr = nextln.split(",");
            // first number: starting point of arrow
            v = Integer.parseInt(lnAr[0]);
            for (int i = 1; i < lnAr.length; i++) {
                // subsequent numbers represent the direction of arrow
                w = Integer.parseInt(lnAr[i]);
                dg.addEdge(v, w);
            }
        }
    }

    //helper method to deal with adding synset noun entries
    private void addsynset(String n, Integer i) {
        HashSet<Integer> tempset;
        // add number into the set if the key already exists
        if (wMap.containsKey(n)) {
            tempset = wMap.get(n);
            tempset.add(i);
        } else {
        // create new set and map entry if key does not exist
            tempset = new HashSet<Integer>();
            tempset.add(i);
            wMap.put(n, tempset);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wMap.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
        * WORD. If WORD belongs to multiple synsets, return all hyponyms of
        * all of these synsets. See http://goo.gl/EGLoys for an example.
        * Do not include hyponyms of synonyms.
        */
    public Set<String> hyponyms(String word) {
        Set<String> temp = new HashSet<String>();
        if (!isNoun(word)) { // any immediate abort case.
            return temp;
        }
        HashSet<Integer> iVert = wMap.get(word);
        Set<Integer> fVert = GraphHelper.descendants(dg, iVert);
        for (int i:fVert) {
            HashSet<String> stSet = vList.get(i);
            temp.addAll(stSet);
        }
        return temp;
    }
}
