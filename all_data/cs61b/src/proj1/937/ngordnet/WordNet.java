package ngordnet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Digraph graph;
    private HashMap<Integer, String[]> numToWord = new HashMap<Integer, String[]>();
    private HashMap<String, String> wordToDef = new HashMap<String, String>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        ArrayList<String> synsetsText = null;
        ArrayList<String> hyponymsText = null;

        synsetsText = readText(synsetFilename);
        hyponymsText = readText(hyponymFilename);

        fillGraph(synsetsText, hyponymsText);
    }

    private ArrayList<String> readText(String text) {
        In in = new In(text);
        ArrayList<String> total = new ArrayList<String>();
        String line = in.readLine();

        while (line != null) {
            total.add(line);
            line = in.readLine();
        }

        return total;
    }

    private void fillGraph(ArrayList<String> synsets, ArrayList<String> hyponyms) {

        graph = new Digraph(synsets.size());

        for (int i = 0; i < synsets.size(); i++) {
            String[] sArray = synsets.get(i).split(",");
            String[] words = sArray[1].split(" ");

            numToWord.put(Integer.parseInt(sArray[0]), words);
            for (int j = 0; j < words.length; j++) {
                wordToDef.put(words[j], sArray[2]);
            }
        }

        for (int i = 0; i < hyponyms.size(); i++) {
            String[] sArray = hyponyms.get(i).split(",");
            for (int j = 1; j < sArray.length; j++) {
                graph.addEdge(Integer.parseInt(sArray[0]), Integer.parseInt(sArray[j]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordToDef.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordToDef.keySet();
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {

        // s to id
        Set<Integer> set = new TreeSet<Integer>();

        for (Integer curId : numToWord.keySet()) {
            for (String curWord : numToWord.get(curId)) {
                if (curWord.equals(word)) {
                    set.add(curId);
                }
            }
        }

        // descendants
        Set<Integer> ids = GraphHelper.descendants(graph, set);

        // id to word
        TreeSet<String> words = new TreeSet<String>();

        for (Integer curId : ids) {
            words.addAll(Arrays.asList(numToWord.get(curId)));
        }

        return words;
    }
}
