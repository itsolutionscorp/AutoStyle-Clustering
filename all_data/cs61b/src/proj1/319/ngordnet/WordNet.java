package ngordnet;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;



public class WordNet {
    /**get(number) --> String of synsets*/
    private Map<Integer, ArrayList<String>> synsetKtoV; 

    /**get(ArrayList[""]) --> Synset ID's*/
    private Map<String, Set<Integer>> synsetVtoK; 

    private ArrayList<ArrayList<Integer>> hyponyms;
    private Set<Integer> synsetID;
    private Digraph digraph;


    // private Digraph digraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetKtoV = new HashMap<Integer, ArrayList<String>>();
        synsetVtoK = new HashMap<String, Set<Integer>>();
        synsetID = new HashSet<Integer>();
        hyponyms = new ArrayList<ArrayList<Integer>>();
        In imported = new In(synsetFilename);
        String readLine;
        while (imported.hasNextLine()) {
            readLine = imported.readLine();
            /*split id from synonyms*/
            ArrayList<String> rawTokens = new ArrayList<String>(Arrays.asList(readLine.split(",")));
            String raw1 = rawTokens.get(1);
            ArrayList<String> synsArray = new ArrayList<String>(Arrays.asList(raw1.split(" ")));
            /* split synonyms for synset*/
            Set<String> syns = new HashSet<String>(Arrays.asList(rawTokens.get(1).split(" ")));
            this.synsetKtoV.put(Integer.valueOf(rawTokens.get(0)), synsArray);
            this.synsetID.add(Integer.valueOf(rawTokens.get(0)));

            
            for (int i = 0; i < syns.size(); i++) {
                /* create blank array to store values in*/
                Set<Integer> temp = new HashSet<Integer>();

                /* grab integer ID from synset file line*/
                temp.add(Integer.valueOf(rawTokens.get(0)));

                Integer addedInt = Integer.valueOf(rawTokens.get(0));

                /* if string is already a key in synsetVtoK, then we want to add it not replace it*/
                if (this.synsetVtoK.containsKey(synsArray.get(i))) {
                    synsetVtoK.get(synsArray.get(i)).add(addedInt);
                } else {
                    /* add key and value normally if unique key*/
                    this.synsetVtoK.put(synsArray.get(i), temp);
                }
            }

            // for (String each: syns) {
            //     this.synsetVtoK.put(each, new ArrayList<Integer>());
            //     this.synsetVtoK.get(each).add(Integer.valueOf(rawTokens.get(0)));
            // }
        }

        digraph = new Digraph(synsetID.size());
        In hypoImport = new In(hyponymFilename);
        ArrayList<String> readHypo;
        while (hypoImport.hasNextLine()) {
            readHypo = new ArrayList<String>(Arrays.asList(hypoImport.readLine().split(",")));

            ArrayList<Integer> orderedHypo = new ArrayList<Integer>();
            for (int i = 0; i < readHypo.size(); i++) {
                orderedHypo.add(Integer.valueOf(readHypo.get(i)));
            }
            hyponyms.add(orderedHypo);
        }
        for (int i = 0; i < hyponyms.size(); i++) {
            for (int k = 1; k < hyponyms.get(i).size(); k++) {
                digraph.addEdge(hyponyms.get(i).get(0), hyponyms.get(i).get(k));
            }
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (noun.equals(null)) {
            return false;
        }
        return synsetVtoK.containsKey(noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> values = synsetVtoK.keySet();
        return values;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms. */
      
    public Set<String> hyponyms(String word) {
        /* need to put "word" into synsetVtoK and return the hyponyms of those synsets.*/

        /*get synset ID's of synsets that word belongs to*/
        Set<Integer> wordSyns = synsetVtoK.get(word);
        Set<String> returnVal = new HashSet<String>(); //instantiate return Set
        Set<Integer> digSet = GraphHelper.descendants(digraph, wordSyns);
        ArrayList<String> stringDig = new ArrayList<String>();


        /* put hypos in return set*/
        for (Integer each: digSet) {
            stringDig = synsetKtoV.get(each);
            for (int i = 0; i < stringDig.size(); i++) {
                returnVal.add(stringDig.get(i));
            }
        }
        /*take synset ID's from wordsets and get their hyponyms*/
        return returnVal;

    }
}
