package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;

public class WordNet {
    private Digraph graph;
    private Map<Integer, TreeSet<String>> listWord;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFileName, String hyponymFileName) {
        In synset = new In(synsetFileName);
        In hypo = new In(hyponymFileName);
        listWord = new TreeMap<Integer, TreeSet<String>>();

        int index = 0;

        while (synset.hasNextLine()) {
            TreeSet<String> s = new TreeSet<String>();
            String line = synset.readLine();

            String[] parts = line.split(",");
            index = Integer.parseInt(parts[0]);

            String[] words = parts[1].split(" ");
            for (String w:words) {
                s.add(w);
            }
            listWord.put(index, s);
        }

        graph = new Digraph(index + 1);

        while (hypo.hasNextLine()) {
            String line = hypo.readLine();
            String[] parts = line.split(",");
            int hypernym = Integer.parseInt(parts[0]);
            int hypernym2 = hypernym;

            for (int i = 1; i < parts.length; i += 1) {
                int hyponym = Integer.parseInt(parts[i]);
                graph.addEdge(hypernym, hyponym);
                
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {

        for (Integer i : listWord.keySet()) {
            TreeSet<String> s = listWord.get(i);
            for (String w :s) {
                if (noun.equals(w)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> newSet = new TreeSet<String>();

        for (Integer i : listWord.keySet()) {
            TreeSet<String> s = listWord.get(i);
            for (String w :s) {
                newSet.add(w);
            }
        }
        return newSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> newSetIndex = new TreeSet<Integer>();

        for (int i = 0; i < listWord.size(); i += 1) {
            TreeSet<String> s = listWord.get(i);
            for (String w : s) {
                if (word.equals(w)) {
                    newSetIndex.add(i);
                }
            }
        }

        Set<String> newSetString = new TreeSet<String>();
        Set<Integer> descendants = GraphHelper.descendants(graph, newSetIndex);

        for (Integer desc : descendants) {
            newSetString.addAll(listWord.get(desc));
        }
        return newSetString;
    }
}
