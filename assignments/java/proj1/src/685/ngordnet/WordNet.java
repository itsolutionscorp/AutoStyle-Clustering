package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    /* referred a lot to Oracle documentation for how to use the methods for 
     * everything imported from java.util. */

    private HashMap<Integer, ArrayList<String>> synMap;
    private int numOfKeys = 0; //num of keys in synMap
    private Digraph digraph;

    /* Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        //parses synsets into map; maps id to synset
        In synRaw = new In(synsetFilename);
        synMap = new HashMap<Integer, ArrayList<String>>();
        /* received assistance from senior EECS major on syntax for ArrayLists 
         * and concerning the differences between using ArrayList<String> and 
         * String[] for this while loop and subsequent ones similar to this.
         */
        while (synRaw.hasNextLine()) {
            ArrayList<String> tempSyn = new ArrayList<String>();

            String[] temp = synRaw.readLine().split(",");
            for (int i = 0; i < temp.length; i++) {
                tempSyn.add(temp[i]);
            }

            String[] syn = tempSyn.get(1).split(" ");
            ArrayList<String> synonyms = new ArrayList<String>(); 

            for (int i = 0; i < syn.length; i++) {
                synonyms.add(syn[i]);
            }

            synMap.put(Integer.parseInt(tempSyn.get(0)), synonyms);
            numOfKeys += 1;
        }

        //creates full Digraph
        digraph = new Digraph(synMap.keySet().size());
        In hypRaw = new In(hyponymFilename);
        while (hypRaw.hasNextLine()) {
            ArrayList<String> tempHyp = new ArrayList<String>();
            String[] hyp = hypRaw.readLine().split(",");
            for (int i = 0; i < hyp.length; i++) {
                tempHyp.add(hyp[i]);
            }

            Integer hyper = Integer.parseInt(tempHyp.get(0));
            for (int i = 1; i < tempHyp.size(); i++) {
                digraph.addEdge(hyper, Integer.parseInt(tempHyp.get(i)));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (int i = 0; i < numOfKeys; i++) {
            for (String syn : synMap.get(i)) {
                nouns.add(syn);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
      * If WORD belongs to multiple synsets, return all hyponyms of all of these 
      * synsets.
      */
    public Set<String> hyponyms(String word) {
        Set<String> all = new HashSet<String>();
        Set<Integer> synsetsWithWord = new HashSet<Integer>();
        for (int i = 0; i < numOfKeys; i++) {
            for (int j = 0; j < synMap.get(i).size(); j++) {
                if ((synMap.get(i)).get(j).contains(word)) {
                    synsetsWithWord.add(i);
                }
            }
        }

        Set<Integer> hypoAndSynIDs = GraphHelper.descendants(digraph, synsetsWithWord);
        for (int synID : synsetsWithWord) {
            hypoAndSynIDs.add(synID);
        }

        for (int id : hypoAndSynIDs) {
            for (int i = 0; i < synMap.get(id).size(); i++) {
                all.add(synMap.get(id).get(i));
            }
        }

        return all;
    }

}
