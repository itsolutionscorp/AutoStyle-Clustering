package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;


public class WordNet {
    private TreeMap<Integer, TreeSet<String>> synsetMap;
    private TreeMap<Integer, TreeSet<Integer>> hyponymMap;
    private Digraph wordNetDiagraph;
    private In synsetIn;
    private In hyponymIn;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME 
      * The first field is the synset id (an integer), the second field is the synset
      * The first field is a synset id; subsequent fields are the id numbers 
      * of the synset's direct hyponyms.
      * synsetMap: ID as key and synset member as TreeSet
      * hyponymMap: ID as key and hyponyms as TreeSet
      */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetMap = new TreeMap<Integer, TreeSet<String>>();
        synsetIn = new In(synsetFilename);
        while (synsetIn.hasNextLine()) {
            String newLine = synsetIn.readLine();
            String[] splittedLine = newLine.split(",");
            Integer key = Integer.parseInt(splittedLine[0]);
            TreeSet<String> synset = new TreeSet<String>();
            String[] synsetsInString = splittedLine[1].split(" ");
            for (int i = 0; i < synsetsInString.length; i += 1) {
                synset.add(synsetsInString[i]);
            }
            synsetMap.put(key, synset);
        }
      
        hyponymMap = new TreeMap<Integer, TreeSet<Integer>>();
        hyponymIn = new In(hyponymFilename);
        while (hyponymIn.hasNextLine()) {
            String newLine = hyponymIn.readLine();
            String[] splittedLine = newLine.split(",");
            Integer key = Integer.parseInt(splittedLine[0]);
            if (!hyponymMap.containsKey(key)) {
                TreeSet<Integer> newSet = new TreeSet<Integer>();
                hyponymMap.put(key, newSet);
            }
            TreeSet<Integer> hyponyms = hyponymMap.get(key);
            for (int i = 1; i < splittedLine.length; i += 1) {
                hyponyms.add(Integer.parseInt(splittedLine[i]));
            }
            hyponymMap.put(key, hyponyms);
        }

        wordNetDiagraph = new Digraph(synsetMap.size());
        for (Integer hypernym : hyponymMap.keySet()) {
            for (Integer hyponym : hyponymMap.get(hypernym)) {
                wordNetDiagraph.addEdge(hypernym, hyponym);
            }
        }
    } 

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer synsetID : synsetMap.keySet()) {
            if (synsetMap.get(synsetID).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> allNouns = new TreeSet<String>();
        for (Integer synsetID : synsetMap.keySet()) {
            for (String noun : synsetMap.get(synsetID)) {
                allNouns.add(noun);
            }
        }
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      * hyponymsAndSynonyms
      */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) { //not part of wordNet
            throw new NullPointerException("Word is not part of wordNet."); 
        }
        TreeSet<Integer> synsetIDs = new TreeSet<Integer>();
        for (Integer synsetID: synsetMap.keySet()) {
            if (synsetMap.get(synsetID).contains(word)) {
                synsetIDs.add(synsetID);
            }
        }
        Set<Integer> hyponymsByID = GraphHelper.descendants(wordNetDiagraph, synsetIDs);
        return idToString(hyponymsByID);
    }

    //changes a set of synsetIDs into their respective synsets.
    private Set<String> idToString(Set<Integer> synsetIDs) {
        TreeSet<String> stringSynsets = new TreeSet<String>();
        for (Integer synsetID : synsetIDs) {
            for (String synsetMember : synsetMap.get(synsetID)) {
                stringSynsets.add(synsetMember);
            }
        }
        return stringSynsets;
    }
}
