package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
//import java.util.ArrayList;


public class WordNet {


    //The Digraph marks the path
    private Digraph map;

    //The Map keys and defines the values
    private HashMap<Integer, HashSet<String>> values;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        values = new HashMap<Integer, HashSet<String>>();

        //Creating the Digraph
        In synsets = new In(synsetFilename);
        int synsetCount = 0;
        while (synsets.hasNextLine()) {
            String input = synsets.readLine();
            String[] comps = input.split(",");
            String[] words = comps[1].split(" ");
            HashSet<String> synsetWords = new HashSet<String>();
            for (String w: words) {
                synsetWords.add(w);

            }
            values.put(new Integer(Integer.parseInt(comps[0])), synsetWords); 
            synsetCount += 1;
        }
        map = new Digraph(synsetCount);
        In hyponyms = new In(hyponymFilename);
        while (hyponyms.hasNextLine()) {
            String hypoLine = hyponyms.readLine();
            String[] words = hypoLine.split(",");
            for (int i = 1; i < words.length; i += 1) {
                map.addEdge(Integer.parseInt(words[0]), Integer.parseInt(words[i]));
            }
        }

    } 

    // /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        boolean isNoun = false;
        Set<Integer> indices = values.keySet();
        for (Integer i : indices) {
            HashSet<String> words = values.get(i);
            for (String s : words) {
                if (s.equals(noun)) {
                    isNoun = true;
                }
            }
        }
        return isNoun;

    }




    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        Set<Integer> indices = values.keySet();
        for (Integer i : indices) {
            HashSet<String> words = values.get(i);
            for (String s : words) {
                nouns.add(s);
            }
        }
        return nouns;


    }

    // * Returns the set of all hyponyms of WORD as well as all synonyms of
    //   * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    //   * all of these synsets. See http://goo.gl/EGLoys for an example.
    //   * Do not include hyponyms of synonyms.

    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> finders = new HashSet<Integer>();
        Set<Integer> indices = values.keySet();
        for (Integer i : indices) {
            HashSet<String> words = values.get(i);
            for (String s : words) {
                if (s.equals(word)) {
                    finders.add(i);
                }
            }
        }
        Set<Integer> hypoIndices = GraphHelper.descendants(map, finders);
        for (Integer i: hypoIndices) {
            HashSet<String> moreWords = values.get(i);
            for (String s: moreWords) {
                hyponyms.add(s);
            }
        }
        return hyponyms;


    }
}
