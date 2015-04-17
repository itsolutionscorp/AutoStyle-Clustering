package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Digraph d;
    private In synset;
    private In hyponym;
    private HashMap<Integer, HashSet<String>> synMap;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new In(synsetFilename);
        hyponym = new In(hyponymFilename);
        synMap = new HashMap<Integer, HashSet<String>>();

        if (!synset.isEmpty()) {
            while (synset.hasNextLine()) {
                String line = synset.readLine();
                String[] key = line.split(",");
                String[] set = key[1].split(" ");
                HashSet<String> temp = new HashSet<String>();
                for (int i = 0; i < set.length; i = i + 1) {
                    temp.add(set[i]);
                }
                synMap.put(Integer.parseInt(key[0]), temp);
            }
        }

        int synCount = synMap.size();
        d = new Digraph(synCount);

        if (!hyponym.isEmpty()) {
            while (hyponym.hasNextLine()) {
                String hline = hyponym.readLine();
                String[] hkey = hline.split(",");
                Integer[] inthkey = new Integer[hkey.length];
                for (int k = 0; k < hkey.length; k = k + 1) {
                    inthkey[k] = Integer.parseInt(hkey[k]);
                }

                for (int z = 1; z < hkey.length; z = z + 1) {
                    d.addEdge(inthkey[0], inthkey[z]);
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<Integer> keyset = synMap.keySet();
        for (Integer x: keyset) {
            HashSet<String> temp = synMap.get(x);
            if (temp.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    //assumes isNoun is true
    private Set<Integer> isNounKey(String noun) {
        Set<Integer> keyset = synMap.keySet();
        Set<Integer> returnKey = new HashSet<Integer>();
        for (Integer x: keyset) {
            HashSet<String> temp = synMap.get(x);
            if (temp.contains(noun)) {
                returnKey.add(x);
            }
        }
        return returnKey;
    }
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<Integer> keyset = synMap.keySet();
        Set<String> nouns = new HashSet<String>();
        for (Integer x: keyset) {
            HashSet<String> temp = synMap.get(x);
            String[] tempArray = temp.toArray(new String[0]);
            for (int i = 0; i < tempArray.length; i = i + 1) {
                nouns.add(tempArray[i]);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        if (isNoun(word)) {
            Set<Integer> start = isNounKey(word);
            Set<Integer> hypoKey = GraphHelper.descendants(d, start);
            for (Integer x: hypoKey) {
                HashSet<String> temp = synMap.get(x);
                String[] tempArray = temp.toArray(new String[0]);
                for (int i = 0; i < tempArray.length; i = i + 1) {
                    hyponyms.add(tempArray[i]);
                }
            }
        }
        return hyponyms;
    }
}
