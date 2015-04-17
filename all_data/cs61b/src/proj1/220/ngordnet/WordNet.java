package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;

public class WordNet {
    private Digraph hyponyms;
    private HashMap<Integer, String[]> synsetsIntStr;
    private HashMap<String, ArrayList<Integer>> synsetsStrInt;

    /** Creates a WordNet using files form SYNSETPATH and HYPONYMPATH */
    public WordNet(String synsetPath, String hyponymPath) {
        In sFile = new In(synsetPath);
        synsetsIntStr = new HashMap<Integer, String[]>();
        synsetsStrInt = new HashMap<String, ArrayList<Integer>>();
        while (sFile.hasNextLine()) {
            String[] data = sFile.readLine().split(",");
            String[] synset;
            if (data[1].contains(" ")) {
                synset = data[1].split("\\s+");
            } else {
                synset = new String[1];
                synset[0] = data[1];
            }

            Integer index = Integer.parseInt(data[0]);
            synsetsIntStr.put(index, synset);
            for (String word : synset) {
                ArrayList<Integer> indices = synsetsStrInt.get(word);
                if (indices == null) {
                    ArrayList<Integer> n = new ArrayList<Integer>(2);
                    n.add(index);
                    synsetsStrInt.put(word, n);
                } else {
                    indices.add(index);
                }
            }
        }
        sFile.close();
        
        In hFile = new In(hyponymPath);
        hyponyms = new Digraph(synsetsIntStr.size());
        while (hFile.hasNextLine()) {
            String[] data = hFile.readLine().split(",");
            Integer index = Integer.parseInt(data[0]);
            for (int j = 1; j < data.length; j++) {
                hyponyms.addEdge(index, Integer.parseInt(data[j]));
            }
        }
        hFile.close();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synsetsStrInt.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetsStrInt.keySet();
    }

    private Set<String> getHyponyms(int index) {
        TreeSet<String> s = new TreeSet<String>();
        s.addAll(Arrays.asList(synsetsIntStr.get(index)));
        if (hyponyms.outdegree(index) > 0) {
            for (int i : hyponyms.adj(index)) {
                s.addAll(getHyponyms(i));
            }
        }
        return s;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        ArrayList<Integer> indices = synsetsStrInt.get(word);
        TreeSet<String> words = new TreeSet<String>();
        if (indices == null) {
            return words;
        }
        for (int index : indices) {
            words.addAll(getHyponyms(index));
        }
        return words;
    }
}
