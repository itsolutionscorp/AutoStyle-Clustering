
package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;



public class WordNet {
    private Digraph g;
    private Map<String, Set<String>> relations;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In hyponym = new In(hyponymFilename);
        In synsets = new In(synsetFilename);
        relations = new TreeMap<String, Set<String>>();
        int count = 0;
        while (synsets.hasNextLine()) {
            String[] thisLine = synsets.readLine().split(",");
            Set<String> words = new TreeSet<String>(Arrays.asList(thisLine[1].split(" ")));
            relations.put(thisLine[0], words);
            count += 1;

        }
        g = new Digraph(count);
        while (hyponym.hasNextLine()) {
            String[] hypoLine = hyponym.readLine().split(",");
            int x = Integer.parseInt(hypoLine[0]);
            for (int i = 1; i < hypoLine.length; i += 1) {
                int y = Integer.parseInt(hypoLine[i]);
                g.addEdge(x, y);
            }
        }
    }
      /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

      /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> ret = new TreeSet<String>();
        for (String key: relations.keySet()) {
            ret.addAll(relations.get(key));
        }
        return ret;

    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> ret = new TreeSet<String>();
        Set<Integer> valids = new TreeSet<Integer>();
        for (String key:relations.keySet()) {
            if (relations.get(key).contains(word)) {
                valids.add(Integer.parseInt(key));
            }
        }
        Set<Integer> accumulated =  new TreeSet<Integer>();
        accumulated.addAll(GraphHelper.descendants(g, valids));
        for (Integer i:accumulated) {
            ret.addAll(relations.get(Integer.toString(i)));
        }
        return ret;

    }
  





    
}
