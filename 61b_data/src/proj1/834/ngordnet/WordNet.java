package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Map<Integer, HashSet<String>> mapofsysnet = new HashMap();
    private Map<Integer, HashSet<Integer>> mapofhyponym = new HashMap();
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        while (synset.hasNextLine()) {
            String line = synset.readLine();
            String[] rawTokens = line.split(",");
            String[] syns = rawTokens[1].split(" ");
            HashSet<String> synonymsss = new HashSet();
            for (int i = 0; i < syns.length; i += 1) {
                synonymsss.add(syns[i]);
            }
            mapofsysnet.put(Integer.parseInt(rawTokens[0]), synonymsss);
        }
        while (hyponym.hasNextLine()) {
            HashSet<Integer> hypooo = new HashSet();
            String line = hyponym.readLine();
            String[] rawTokens = line.split(",");
            for (int i = 1; i < rawTokens.length; i += 1) {
                hypooo.add(Integer.parseInt(rawTokens[i]));
            }
            if (mapofhyponym.get(Integer.parseInt(rawTokens[0])) != null) {
                hypooo.addAll(mapofhyponym.get(Integer.parseInt(rawTokens[0])));
                mapofhyponym.remove(Integer.parseInt(rawTokens[0]));
                mapofhyponym.put(Integer.parseInt(rawTokens[0]), hypooo);
            } else {
                mapofhyponym.put(Integer.parseInt(rawTokens[0]), hypooo);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer key : mapofsysnet.keySet()) {  
            if (mapofsysnet.get(key).contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> nounnn = new HashSet();
        for (Integer key : mapofsysnet.keySet()) {
            for (String wordss : mapofsysnet.get(key)) {
                nounnn.add(wordss);
            }
        }
        return nounnn;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> result = new HashSet();
        for (Integer key : mapofsysnet.keySet()) {
            if (mapofsysnet.get(key).contains(word)) {
                Set<Integer> temp = onlyhypo(key);
                for (Integer index : temp) {
                    result.addAll(mapofsysnet.get(index));
                }
            }
        }
        return result;
    }

    private Set<Integer> onlyhypo(Integer posi) {
        HashSet<Integer> result = new HashSet();
        result.add(posi);
        if (mapofhyponym.get(posi) != null) {
            for (Integer number : mapofhyponym.get(posi)) {
                result.addAll(onlyhypo(number));
            }
        }
        return result;
    }
}
