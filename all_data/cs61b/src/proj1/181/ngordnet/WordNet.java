package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class WordNet {
    private Digraph connections;
    private HashMap iDtoWords = new HashMap();
    private HashMap wordToiDs = new HashMap();
     /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetReader = new In(synsetFilename);
        In hyponymReader = new In(hyponymFilename);
        String[] lines = synsetReader.readAllLines();
        String[] hypLines = hyponymReader.readAllLines();
        for (String line: lines) {
            String[] rawTokens = line.split(",");
            int temp = Integer.parseInt(rawTokens[0]);
            String[] tempWords = rawTokens[1].split(" ");
            iDtoWords.put(temp, tempWords);
            for (String word: tempWords) {
                if (wordToiDs.containsKey(word)) {
                    TreeSet<Integer> iDs = (TreeSet<Integer>) wordToiDs.get(word);
                    iDs.add(temp);
                    wordToiDs.put(word, iDs);
                } else {
                    TreeSet<Integer> iDs = new TreeSet<Integer>();
                    iDs.add(temp);
                    wordToiDs.put(word, iDs);
                }
            }
        }

        int numVs = 0;
        for (String line: hypLines) {
            numVs += line.split(",").length;
        }
        connections = new Digraph(numVs);

        for (String line: hypLines) {
            String[] tokens = line.split(",");
            int[] ints = new int[tokens.length];
            int tempIndex = 0;
            for (String token:tokens) {
                int temp = Integer.parseInt(token);
                ints[tempIndex] = temp;
                tempIndex += 1;
            }
            for (int a = 1; a < tokens.length; a++) {
                connections.addEdge(ints[0], ints[a]);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordToiDs.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return (Set<String>) wordToiDs.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> result = new TreeSet<String>();
        TreeSet<Integer> synIDs = (TreeSet<Integer>) wordToiDs.get(word);
        if (synIDs == null) {
            return null;
        }
        TreeSet<Integer> vNums = (TreeSet<Integer>) GraphHelper.descendants(connections, synIDs);
        Iterator<Integer> it = vNums.iterator();
        while (it.hasNext()) {
            String[] temp = (String[]) iDtoWords.get(it.next());
            for (String a: temp) {
                result.add(a);
            }
        }
        return result;
    }
}
