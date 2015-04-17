/** An object that stores the WordNet graph 
 *  in a manner useful for extracting all hyponyms of a word.
 *  @author Raymond Chan 
 */

package ngordnet;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    /** Number of vertices that the tree has. */
    private int size;

    private In synset;

    private In hyponym;

    /** DiGraph that depicts the relationship between words. */
    private Digraph tree;

    /** Stores all noun as NounNode objects in a TreeMap. */
    private TreeMap<Integer, String> words;

    /** An ArrayList of words from synsets. */
    private ArrayList<String> wordList;

    /** Creates a WordNet from Synset and Hyponym files. */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new In(synsetFilename);
        hyponym = new In(hyponymFilename);
        words = new TreeMap<Integer, String>();

        int totalVertices = 0;
        wordList = new ArrayList<String>();

        while (synset.hasNextLine()) {
            String[] line = synset.readLine().split(",");
            Integer key = new Integer(Integer.parseInt(line[0]));
            String val = line[1];
            words.put(key, val);
            totalVertices += 1;
            String[] allWords = val.split(" ");
            if (allWords.length > 1) {
                for (String word : allWords) {
                    wordList.add(word);
                }
            } else {
                wordList.add(val);
            }
        }

        tree = new Digraph(totalVertices);

        while (hyponym.hasNextLine()) {
            String[] line = hyponym.readLine().split(",");
            int parent = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++) {
                int value = Integer.parseInt(line[i]);
                tree.addEdge(parent, value);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordList.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> result = new HashSet<String>();
        Iterator<String> wordsIter = wordList.iterator();
        while (wordsIter.hasNext()) {
            result.add(wordsIter.next());
        }
        return result;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> result = new HashSet<String>();
        if (!isNoun(word)) {
            return result;
        } else {
            Set<Integer> hypNum = new HashSet<Integer>();
            Set<Integer> locations = new HashSet<Integer>();
            
            // Finding all the integer locations of strings that contains the selected word.
            for (Map.Entry<Integer, String> entry : words.entrySet()) {
                String[] val = entry.getValue().split(" ");
                if (val.length > 1) {
                    for (int i = 0; i < val.length; i++) {
                        if (word.equals(val[i])) {
                            locations.add(entry.getKey());
                        }
                    }
                } else if (word.equals(val[0])) {
                    locations.add(entry.getKey());
                }
            }

            hypNum.addAll(GraphHelper.descendants(tree, locations));

            for (Integer pos : hypNum) {
                String[] nouns = words.get(pos).split(" ");
                if (nouns.length > 1) {
                    for (String w : nouns) {
                        result.add(w);
                    }
                }
                result.add(nouns[0]);
            }
            return result;
        }
    }
}
