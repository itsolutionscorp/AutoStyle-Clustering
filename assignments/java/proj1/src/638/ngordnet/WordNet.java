
package ngordnet;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private In synsetStream;
    private In hyponymStream; 
    private Digraph wordDigraph;
    private HashMap<Integer, Set<String>> wordMap = new HashMap<Integer, Set<String>>(); 

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetStream = new In(synsetFilename);
        hyponymStream = new In(hyponymFilename);

        Integer refNumber; 
        Integer hyponymNumber;
        String line;
        String[] tokenizedLine;
        String[] synsetWords;
        Set<String> tokenSynsetWords = new HashSet<String>();
        while (synsetStream != null && synsetStream.hasNextLine()) {
            tokenSynsetWords = new HashSet<String>();
            line = synsetStream.readLine();
            tokenizedLine = line.split(",", 3);
            refNumber = Integer.parseInt(tokenizedLine[0]); 
            //takes in string containing all words and divides them into array
            synsetWords = tokenizedLine[1].split(" ");
            for (String x : synsetWords) {
                tokenSynsetWords.add(x);
            }
            wordMap.put(refNumber, tokenSynsetWords);
        }
        //Digraph for hyponyms
        wordDigraph = new Digraph(wordMap.size());
        while (hyponymStream.hasNextLine()) {
            line = hyponymStream.readLine();
            tokenizedLine = line.split(",");
            refNumber = Integer.parseInt(tokenizedLine[0]);
            for (int i = 1; i < tokenizedLine.length; i++) {
                hyponymNumber = Integer.parseInt(tokenizedLine[i]);
                wordDigraph.addEdge(refNumber, hyponymNumber);
            }
        }
    /* has public Digraph (int V) -- constructor to build out 
    a digraph with set number of vertices
    also has public void addEdge(int v, int w) -- 
    this will allow for an edge to be added to it */
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String x : nouns()) {
            if (x.equals(noun)) { 
                return true;
            }
        }
        return false;
    }

  /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> returnSet = new HashSet<String>();
        Collection<Set<String>> valueSets = wordMap.values();
        for (Set<String> x : valueSets) {
            for (String y : x) {
                returnSet.add(y);
            }
        }
        return returnSet;
    }

  /** Returns the set of all hyponyms of WORD as well as all synonyms of
  * WORD. If WORD belongs to multiple synsets, return all hyponyms of
  * all of these synsets. See http://goo.gl/EGLoys for an example.
  * Do not include hyponyms of synonyms.
  */
    public Set<String> hyponyms(String word) {
        Set<String> returnSet = new HashSet<String>();
        Set<Integer> keys = wordMap.keySet();
        Set<Integer> refNumbers = new HashSet<Integer>();
        for (Integer key : keys) {
            if (wordMap.get(key).contains(word)) {
                refNumbers.add(key); 
            }
        }
        keys = GraphHelper.descendants(wordDigraph, refNumbers);
        for (Integer key : keys) {
            for (String x : wordMap.get(key)) {
                returnSet.add(x);
            }
        }
        return returnSet;
    } 
}
