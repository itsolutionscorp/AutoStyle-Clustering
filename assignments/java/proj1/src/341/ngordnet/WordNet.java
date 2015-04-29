package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
//import ngordnet.GraphHelper;

//use a treeMap that maps an integer to a word


public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private String syn;
    private String hyp;
    private int v = 0; //number of vertices/nouns
    private Set<String> nounList;
    private HashMap<Integer, Set<Integer>> diagram;
    private Set<Integer> hyponyms;
    private HashMap<Integer, Set<String>> synsets;
    private Set<String> nouns;
    private Set<String> returnedHyp;
    private Digraph dg;

    public WordNet(String synsetFilename, String hyponymFilename) {
        syn = synsetFilename;
        hyp = hyponymFilename;
        diagram = new HashMap<Integer, Set<Integer>>();
        synsets = new HashMap<Integer, Set<String>>();
        nounList = new TreeSet<String>();

        //Analyzing synset file
        In synFile = new In(syn);
        String synStr;
        while (synFile.hasNextLine()) {
            synStr = synFile.readLine();
            String[] splitSyn = synStr.split(",");
            int sIndex = Integer.parseInt(splitSyn[0]); //ordered nouns
            String sWords = splitSyn[1]; //the word
            String[] individual = sWords.split(" "); //gets multiple words if there are
            nouns = new TreeSet<String>();
            for (int i = 0; i < individual.length; i += 1) {
                nouns.add(individual[i]); //add list of all hyponyms
                nounList.add(individual[i]);
                synsets.put(sIndex, nouns);
            }
        }
     
        //hyponym file
        In hypFile = new In(hyp);
        String hypStr;
        while (hypFile.hasNextLine()) {
            hypStr = hypFile.readLine();
            String[] splitHyp = hypStr.split(",");
            int hyper = Integer.parseInt(splitHyp[0]); //hypernyms (on top of)
            hyponyms = new TreeSet<Integer>(); //removed set<integer>
            for (int i = 1; i < splitHyp.length; i += 1) {
                if (diagram.containsKey(hyper)) {
                    hyponyms = diagram.get(hyper);
                }
                int pH = Integer.parseInt(splitHyp[i]);
                hyponyms.add(pH); //add list of all hyponyms
                v += 2;
            }
            diagram.put(hyper, hyponyms);
        }

        dg = new Digraph(v);
      //iterate through keys of hashmap
      //save first value (key), value of first vertex
      //parse through values of that key
      //add edges from key to every value
      //int topVal;
        for (Integer key : diagram.keySet()) {
            Set<Integer> valueSet = diagram.get(key);
            for (Integer value : valueSet) {
                dg.addEdge(key, value);
            }
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String word : nounList) {
            if (noun.equals(word)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounList;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
      //get key of word
      //for that word, get keys of values 
      //create treeset with that number, add it in to graph helper
      //pass in 5, graph helper takes number and returns set of that 
      //number and every number associated with it
      // returnedHyp = new Set<String>();
        Set<Integer> hypNumSet = new TreeSet<Integer>();
        Set<String> valSet = new TreeSet<String>();
        Set<Integer> index = new TreeSet<Integer>();
        returnedHyp = new TreeSet<String>();
        for (Integer findKey : synsets.keySet()) {
            for (String values : synsets.get(findKey)) { 
                if (word.equals(values)) {
                    index.add(findKey); //should have one element - just the key
                    hypNumSet = GraphHelper.descendants(dg, index);           
                }
            }
        }
        for (Integer key : hypNumSet) {
            valSet = synsets.get(key);
            for (String s : valSet) {
                returnedHyp.add(s);
            }
        }
        return returnedHyp;
    }
  
}


