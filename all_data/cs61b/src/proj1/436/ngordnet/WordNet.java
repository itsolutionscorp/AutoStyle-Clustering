package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;

public class WordNet {
    private TreeMap<Integer, Word> wordsMap;
    private TreeSet<String> wordsSet;
    private Digraph connections;
    private int lines;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        wordsSet = new TreeSet<String>();
        wordsMap = new TreeMap<Integer, Word>();
        lines = 0;
        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine();
            String[] wordsOfLine = line.split(",");
            int id = Integer.parseInt(wordsOfLine[0]);
            String[] syn = wordsOfLine[1].split(" ");
            String def = wordsOfLine[2];
            for (String word: syn) {
                wordsSet.add(word);
            }
            wordsMap.put(id, new Word(id, syn, def));
            lines++;
        }
        connections = new Digraph(lines);
        while (hyponymFile.hasNextLine()) {
            String line = hyponymFile.readLine();
            String[] idsOfLine = line.split(",");
            int root = Integer.parseInt(idsOfLine[0]);
            for (int i = 1; i < idsOfLine.length; i++) {
                connections.addEdge(root, Integer.parseInt(idsOfLine[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordsSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordsSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    
    /*adds hymonyms of adjacent vertices*/
    private Set<String> treeDGraph(int id) {
        TreeSet<String> hyponymSet = new TreeSet<String>();
        for (int vert: connections.adj(id)) {
            for (String word: wordsMap.get(vert).syn) {
                hyponymSet.add(word);
            }
            hyponymSet.addAll(treeDGraph(vert));
        }
        return hyponymSet;
    }
    public Set<String> hyponyms(String word) {
        TreeSet<String> hyponymSet = new TreeSet<String>();
        for (Word w: wordsMap.values()) {
            if (w.containsSyn(word)) {
                for (String synonym: w.syn) {
                    hyponymSet.add(synonym);
                }
                hyponymSet.addAll(treeDGraph(w.id));
            }
        }
        return hyponymSet;
    }

    private class Word {
        int id;
        String[] syn;
        String def;

        public Word(int idI, String[] synI, String defI) {
            this.id = idI;
            this.syn = synI;
            this.def = defI;
        }

        /* checks if there is sWord among Word's synonyms*/
        public boolean containsSyn(String sWord) {
            for (String word: syn) {
                if (word.equals(sWord)) {
                    return true;
                }
            }
            return false;
        }
    }
}
