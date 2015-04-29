package ngordnet;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private Digraph wordGraph;
    private BiMap<Integer, String> wordMap;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        /** Read the size of wordGraph**/
        In syn = new In(synsetFilename);
        String line = syn.readLine();
        String nextLine = syn.readLine();
        while (nextLine != null) {
            line = nextLine;
            nextLine = syn.readLine();           
        }
        String[] elements = line.split(",");
        int size = Integer.parseInt(elements[0]) + 1;
        
        /** Construct wordGraph **/
        wordGraph = new Digraph(size);
        In hyp = new In(hyponymFilename);
        String line1 = hyp.readLine();
        while (line1 != null) {
            String[] elementsStr = line1.split(",");
            int[] elementsInt = new int[elementsStr.length];
            for (int i = 0; i < elementsStr.length; i += 1) {
                elementsInt[i] = Integer.parseInt(elementsStr[i]);
                if (i != 0) {
                    wordGraph.addEdge(elementsInt[0], elementsInt[i]);
                }
            }
            line1 = hyp.readLine();
        }
        
        /** Construct wordMap **/
        wordMap = new BiMap<Integer, String>();
        In syn2 = new In(synsetFilename);
        String line2 = syn2.readLine();
        while (line2 != null) {
            String[] elementsStr2 = line2.split(",");
            Integer key = Integer.parseInt(elementsStr2[0]);
            String[] words = elementsStr2[1].split(" ");
            for (int i = 0; i < words.length; i += 1) {
                wordMap.put(key, words[i]);
            }
            line2 = syn2.readLine();
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordMap.containsVal(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordMap.getVal();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> id = wordMap.getKey(word);
        Object[] idArray = id.toArray();
        Set<String> hypWord = new HashSet<String>();
        for (int i = 0; i < idArray.length; i += 1) {
            Set<Integer> wordID = new TreeSet<Integer>();            
            wordID.add((Integer) idArray[i]);
            Set<Integer> hypID = GraphHelper.descendants(wordGraph, wordID);
            Object[] hypIDArray = hypID.toArray();
            for (int j = 0; j < hypID.size(); j += 1) {
                hypWord.addAll(wordMap.getVal((Integer) hypIDArray[j]));
            }
        }
        return hypWord;
    }
}
