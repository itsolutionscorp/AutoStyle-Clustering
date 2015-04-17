package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    
    private In synsetFile;
    private In hyponymFile;
    private Digraph digraph;
    private HashMap<Integer, List<String>> synsets;
    private HashMap<String, List<Integer>> inverseSet;

    public WordNet(String synsetFilename, String hyponymFilename) {

        synsetFile = new In(synsetFilename);
        hyponymFile = new In(hyponymFilename);

        //parse the synset file into the map, counting nodes
        synsets = new HashMap<Integer, List<String>>();
        inverseSet = new HashMap<String, List<Integer>>();
        int numNodes = 1;

        while (synsetFile.hasNextLine()) {
            StringTokenizer nextSynset = new StringTokenizer(synsetFile.readLine(), ",");
            int key = Integer.parseInt(nextSynset.nextToken());
            String[] synset = nextSynset.nextToken().split(" ");
            List<String> mapStrings = new ArrayList<String>();
            for (String s: synset) {
                mapStrings.add(s);
                if (inverseSet.containsKey(s)) {
                    inverseSet.get(s).add(key);
                } else {
                    ArrayList<Integer> al = new ArrayList<Integer>();
                    al.add(key);
                    inverseSet.put(s, al);
                }
            }
            synsets.put(key, mapStrings);
            numNodes++;
        }

        //creating Digraph
        digraph = new Digraph(numNodes);
        while (hyponymFile.hasNextLine()) {
            StringTokenizer nextHyponym = new StringTokenizer(hyponymFile.readLine(), ",");
            int hyponym = Integer.parseInt(nextHyponym.nextToken());
            while (nextHyponym.hasMoreTokens()) {
                digraph.addEdge(hyponym, Integer.parseInt(nextHyponym.nextToken()));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return inverseSet.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return inverseSet.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        List<Integer> id = inverseSet.get(word);
        TreeSet<String> toReturn = new TreeSet<String>();

        for (int i: id) {
            //adding synonyms
            for (String s: synsets.get(i)) {
                toReturn.add(s);
            }
            //adding descendants
            Set<Integer> inputID = new TreeSet<Integer>();
            inputID.add(i);
            Set<Integer> outputIDs = GraphHelper.descendants(digraph, inputID);
            for (int j: outputIDs) {
                for (String str: synsets.get(j)) {
                    toReturn.add(str);
                }
            }
        }
        return toReturn;
    }
}
