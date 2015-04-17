
package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class WordNet {

    private Map<Integer, String[]> synsets;
    private Digraph connections;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new HashMap<Integer, String[]>();
        In synsetsFile = new In(synsetFilename);
        In hyponymsFile = new In(hyponymFilename);
        int vertices = 0;

        while (!(synsetsFile.isEmpty())) {
            String line = synsetsFile.readLine();
            String[] lineParts = line.split(",");
            int synsetID = Integer.parseInt(lineParts[0]);
            String[] wordsInSynset = lineParts[1].split(" ");
            synsets.put(synsetID, wordsInSynset);
            vertices++;
        }

        connections = new Digraph(vertices);

        while (!(hyponymsFile.isEmpty())) {
            String line = hyponymsFile.readLine();
            String[] lineParts = line.split(",");
            for (int i = 1; i < lineParts.length; i++) {
                int vert = Integer.parseInt(lineParts[0]);
                int descendant = Integer.parseInt(lineParts[i]);
                connections.addEdge(vert, descendant);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> words = new TreeSet<String>();

        for (int entry: synsets.keySet()) {
            for (String word: synsets.get(entry)) {
                words.add(word);
            }
        }

        return words;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> related = new TreeSet<String>();
        Set<Integer> containingVerts = new TreeSet<Integer>();

        for (int entry: synsets.keySet()) {
            for (String noun: synsets.get(entry)) {
                if (noun.equals(word)) {
                    containingVerts.add(entry);
                }
            }
        }

        Set<Integer> hyponymVerts = GraphHelper.descendants(connections, containingVerts);

        for (int vert: hyponymVerts) {
            for (String noun: synsets.get(vert)) {
                related.add(noun);
            }
        }

        return related;
    }
}
