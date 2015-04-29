package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;

public class WordNet {
    private TreeMap nounMap = new TreeMap();
    private TreeMap idMap = new TreeMap();
    private Digraph g;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In in = new In(synsetFilename);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            idMap.put(id, parts[1]);
            String[] nouns = parts[1].split(" ");
            for (String temp:nouns) {
                if (nounMap.containsKey(temp)) {
                    ((Set<Integer>) nounMap.get(temp)).add(id);
                } else {
                    Set<Integer> ids = new TreeSet<Integer>();
                    ids.add(id);
                    nounMap.put(temp, ids);
                }
            }
        }
        g = new Digraph(idMap.size());
        in = new In(hyponymFilename);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                g.addEdge(id, Integer.parseInt(parts[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounMap.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounMap.keySet();
    }
    
 
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> values = new TreeSet<String>();
        Set<Integer> ids = (Set<Integer>) nounMap.get(word);
        for (int id:ids) {
            values.addAll(getHyponyms(id));
        }
        return values;
    }

    private Set<String> getHyponyms(int id) {
        Set<String> values = new TreeSet<String>();
        Set<Integer> ids = new TreeSet<Integer>();
        ids.add(id);
        String[] synonyms = ((String) (idMap.get(id))).split(" ");
        for (String temp:synonyms) {
            values.add(temp);
        }
        ids = (GraphHelper.descendants(g, ids));
        ids.remove(id);
        for (int temp:ids) {
            values.addAll(getHyponyms(temp));
        }
        return values;
    }
}
