package ngordnet;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private HashMap<Integer, ArrayList<String>> synsetHM;
    private HashMap<String, ArrayList<Integer>> nounHM;
    private Digraph digraph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String s, String h) {
        synsetHM = new HashMap<Integer, ArrayList<String>>();
        nounHM = new HashMap<String, ArrayList<Integer>>();
        int count = 0;
        In in = new In(s);

        /** Read the synset file
         * Each line is as follows:
         * { int Synset_ID, String synset, String definition }
         * { 36, AND_circuit AND_gate, a circuit in a computer... } */
        while (in.hasNextLine()) {
            String[] currentLine = in.readLine().split(",");

            // read the first element as an int synsetID, second element as a string
            int synsetID = Integer.parseInt(currentLine[0]);
            String allNouns = currentLine[1];

            String[] allNounsSeparated = allNouns.split(" ");
            ArrayList<String> nounList = new ArrayList<String>();
            for (String x : allNounsSeparated) {
                nounList.add(x);
            }

            // place ID and nouns inside synset Hash Map
            synsetHM.put(synsetID, nounList);

            // for each noun in nounList, place noun and ID inside noun Hash Map
            // if the key already exists, then add to the arraylist
            // if not, then make a new arraylist with the one synsetID
            for (String n : nounList) {
                if (nounHM.containsKey(n)) {
                    nounHM.get(n).add(synsetID);
                } else {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(synsetID);
                    nounHM.put(n, temp);
                }
            }

            // count number of elements
            count += 1;
        }

        digraph = new Digraph(count);

        in = new In(h);
        /** Read the hyponyn file
         * Each line is as follows:
         * { int Synset_ID, int Hyponym_ID, int Hyponym_ID, ... }
         * where there is no limit to number of Hyponym_IDs */
        while (in.hasNextLine()) {
            String[] currentLine = in.readLine().split(",");
            int synsetID = Integer.parseInt(currentLine[0]);

            for (int i = 0; i < currentLine.length; i += 1) {
                // get integer on the line
                int n = Integer.parseInt(currentLine[i]);
                // add edge to digraph
                digraph.addEdge(synsetID, n);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        // if noun is in nounHM, then it is a noun
        return nounHM.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        // hashmap method keySet returns set of the keys
        return nounHM.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms. */
    public Set<String> hyponyms(String word) {
        // holds all IDs associated with a noun
        Set<Integer> startingID = new TreeSet<Integer>();
        // holds all IDs returned by GraphHelper.descendants
        Set<Integer> descendantIDs = new TreeSet<Integer>();
        // holds all hyponyms of word
        Set<String> hyponyms = new TreeSet<String>();

        // get the list of IDs (value) that correspond to a noun (key) in nounHM
        if (nounHM.containsKey(word)) {
            ArrayList<Integer> listofIDs = nounHM.get(word);
            for (int id : listofIDs) {
                startingID.add(id);
            }
        }

        descendantIDs = GraphHelper.descendants(digraph, startingID);
        // convert descendantID to string using synsetHM
        for (int i : descendantIDs) {
            ArrayList<String> listOfNouns = synsetHM.get(i);
            for (String s : listOfNouns) {
                hyponyms.add(s);
            }
        }
        return hyponyms;
    }
}
