package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

public class WordNet {
    private HashMap<Integer, String[]> idToWords;
    private HashMap<String, ArrayList<Integer>> wordToID;
    private Digraph hyponyms;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        idToWords = new HashMap<Integer, String[]>();
        wordToID = new HashMap<String, ArrayList<Integer>>();


        In in = new In(synsetFilename);
        String[] line;
        while (in.hasNextLine()) {
            line = in.readLine().split(",");
            idToWords.put(Integer.parseInt(line[0]), line[1].split(" "));

            for (String word : line[1].split(" ")) {
                ArrayList<Integer> ids;
                if (!wordToID.containsKey(word)) {
                    ids = new ArrayList<Integer>();
                } else {
                    ids = wordToID.get(word);
                }
                ids.add(Integer.parseInt(line[0]));
                wordToID.put(word, ids);
            }
        }

        hyponyms = new Digraph(wordToID.keySet().size());
        in = new In(hyponymFilename);
        while (in.hasNextLine()) {
            line = in.readLine().split(",");
            for (int i = 1; i < line.length; i++) {
                hyponyms.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
            }
        }
        in.close();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordToID.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordToID.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    /*private Set<String> hypHelper(String word) {
        Set<String> set = new TreeSet();
        set.add(word);
        for (Integer id : wordToID.get(word)) {
            for (Integer adjID : hyponyms.adj(id)) {
                for (String hyponym : idToWords.get(adjID)) {
                    set.addAll(hypHelper(hyponym));
                }
            }
        }
        return set;
    }

    public Set<String> hyponyms(String word) {
        Set<String> set = hypHelper(word);
        for (Integer id : wordToID.get(word)) {
            for (String s : idToWords.get(id)) {
                set.add(s);
            }
        }
        return set;
    }*/
    public Set<String> hyponyms(String word) { //
        Set<Integer> ids = new HashSet<Integer>(wordToID.get(word));
        ids = GraphHelper.descendants(hyponyms, ids);
        Set<String> words = new HashSet<String>();
        Set<String> mySet;
        for (int id : ids) {
            mySet = new HashSet<String>();
            Collections.addAll(mySet, idToWords.get(id));
            words.addAll(mySet);
        }
        return words;
    }
}
