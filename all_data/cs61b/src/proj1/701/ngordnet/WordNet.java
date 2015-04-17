package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map;



public class WordNet {
    private Digraph d;
    private static final int COMMA = 44;
    private static final int SPACE = 32;
    private Map<String, Set<Integer>> m;
     /** Creates a WordNet using files form SYNSET and HYPONYM */
    public WordNet(String synset, String hyponym) {
        In i1 = new In(hyponym);
        In i2 = new In(hyponym);
        int count = 0;
        while (i1.hasNextLine()) {
            String strin = i1.readLine();
            while (strin.indexOf(COMMA) != -1) {
                count++;
                strin = strin.substring(strin.indexOf(COMMA) + 1);
            }
            count++;
        }
        d = new Digraph(count);
        while (i2.hasNextLine()) {
            String stri = i2.readLine();
            int tail = Integer.parseInt(stri.substring(0, stri.indexOf(COMMA)));
            stri = stri.substring(stri.indexOf(COMMA) + 1);
            while (stri.indexOf(COMMA) != -1) {
                String synsets4 = stri.substring(0, stri.indexOf(COMMA));
                d.addEdge(tail, Integer.parseInt(synsets4));
                stri = stri.substring(stri.indexOf(COMMA) + 1);
            }
            d.addEdge(tail, Integer.parseInt(stri));
        }
        m = new HashMap<String, Set<Integer>>();
        In i4 = new In(synset);
        while (i4.hasNextLine()) {
            String st = i4.readLine();
            int key = Integer.parseInt(st.substring(0, st.indexOf(COMMA)));
            int count2 = st.indexOf(COMMA);
            st = st.substring(st.indexOf(COMMA) + 1);
            int count3 = st.indexOf(COMMA);
            st = st.substring(0, count3);
            while (st.indexOf(SPACE) != -1) {
                String synsets2 = st.substring(0, st.indexOf(SPACE));
                Set<Integer> valueSet = m.get(synsets2);
                if (valueSet != null) {
                    valueSet.add(key); 
                } else {
                    valueSet = new TreeSet<Integer>();
                    valueSet.add(key);
                }
                m.put(synsets2, valueSet);
                st = st.substring(st.indexOf(SPACE) + 1);
            }
            Set<Integer> valueSet2 = m.get(st);
            if (valueSet2 != null) {
                valueSet2.add(key); 
            } else {
                valueSet2 = new TreeSet<Integer>();
                valueSet2.add(key);
            }
            m.put(st, valueSet2);    
        }
    } 
     /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return m.containsKey(noun);
    }
 /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return m.keySet();
    }
/** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (m.get(word) != null) {
            Set<Integer> reachable = GraphHelper.descendants(d, m.get(word));
            Set<String> hyponym = new TreeSet<String>();
            for (int num : reachable) {
                hyponym.addAll(getSynonyms(num));
            }
            return hyponym;
        } else {
            return null;
        }
    }

    private Set<String> getSynonyms(int x) {
        Set<String> synonyms = new TreeSet<String>();
        for (String str : nouns()) {
            if (m.get(str).contains(x)) {
                synonyms.add(str);
            }
        }
        return synonyms;
    }




}
