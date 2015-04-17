package ngordnet; 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
//Rodger Pang and Austin Wright both helped me implement this part of the project.
//Brian Ho also gave me the method .hasNextLine()
//I also used the Java api.

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Set<String> synonymSet = new HashSet<String>();
    private HashMap<Integer, String> wordMap = new HashMap<Integer, String>();
    private Digraph alphaDigraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);
        while (synsets.hasNextLine()) {
            String synsetLine = synsets.readLine();
            String[] arrayOfOneSynset = synsetLine.split(",");
            String[] synonyms = arrayOfOneSynset[1].split(" ");
            wordMap.put(Integer.parseInt(arrayOfOneSynset[0]), arrayOfOneSynset[1]);
            int index = 0;
            while (index < synonyms.length) {
                synonymSet.add(synonyms[index]);
                index = index + 1;
            }
        }

        alphaDigraph = new Digraph(wordMap.size());
        while (hyponyms.hasNextLine()) {
            String hyponymLine = hyponyms.readLine();
            String[] arrayOfOneHyponym = hyponymLine.split(",");
            int index = 1;
            while (index < arrayOfOneHyponym.length) {
                int firstID = Integer.parseInt(arrayOfOneHyponym[0]);
                int secondID = Integer.parseInt(arrayOfOneHyponym[index]);
                alphaDigraph.addEdge(firstID, secondID);
                index = index + 1;
            }
        }
    }
    

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synonymSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synonymSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> hyponymSet = new HashSet<Integer>();
        Set<Integer> keys = wordMap.keySet();
        for (Integer k : keys) {
            String[] individualWords = wordMap.get(k).split(" ");
            for (int index = 0; index < individualWords.length; index++) {
                if (individualWords[index].equals(word)) {
                    hyponymSet.add(k);
                }
            }
        }
        Set<Integer> grapher = GraphHelper.descendants(alphaDigraph, hyponymSet);
        Set<String> alphaGrapher = new HashSet<String>(grapher.size());
        for (Integer wordID : grapher) {
            String[] individualHyponymWord = wordMap.get(wordID).split(" ");
            for (int index = 0; index < individualHyponymWord.length; index++) {
                alphaGrapher.add(individualHyponymWord[index]);
            }
        }
        return alphaGrapher;
    }
}
