package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;



public class WordNet {
    private HashMap<Integer, HashSet<String>> intString;
    private HashMap<String, HashSet<Integer>> stringInt;
    private HashSet<String> nouns;
    private Digraph g;

    private void mapInsert(HashMap<String, HashSet<Integer>> map, String key, Integer val) {
        if (map.get(key) == null) {
            HashSet<Integer> i = new HashSet<Integer>();
            i.add(val);
            map.put(key, i);
        } else {
            HashSet<Integer> temp = map.get(key);
            temp.add(val);
        }
    }
    private void mapInsert(HashMap<Integer, HashSet<String>> map, Integer key, String val) {
        if (map.get(key) == null) {
            HashSet<String> s = new HashSet<String>();
            s.add(val);
            map.put(key, s);
        } else {
            HashSet<String> temp = map.get(key);
            temp.add(val);
        }
    }
    
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synset = new In(synsetFilename);
        In hyponym = new In(hyponymFilename);
        String line;
        int size = 0;
        nouns = new HashSet<String>();
        intString = new HashMap<Integer, HashSet<String>>();
        stringInt = new HashMap<String, HashSet<Integer>>();
        while (synset.hasNextLine()) {
            line = synset.readLine();
            if (line.length() != 0) {
                int c1 = line.indexOf(",", 0);
                int c2 = line.indexOf(",", c1 + 1);
                int num = Integer.parseInt(line.substring(0, c1));

                int prev = c1;
                int index = line.indexOf(" ", prev + 1);
//                Debugging credit to Anthony Tan
                if (index > 0) {
                    while (index > 0 && index < c2) {
                        String word = line.substring(prev + 1, index);
                        nouns.add(word);

                        mapInsert(intString, num, word);
                        mapInsert(stringInt, word, num);

                        prev = index;
                        index = line.indexOf(" ", prev + 1);
                    }
                    String word = line.substring(prev + 1, c2);
                    nouns.add(word);
                    mapInsert(intString, num, word);
                    mapInsert(stringInt, word, num);
                } else {
                    String n = line.substring(c1 + 1, c2);
                    mapInsert(intString, num, n);
                    mapInsert(stringInt, n, num);
                    nouns.add(n);

                }

                size += 1;
            }
        }

        g = new Digraph(size);

        while (hyponym.hasNextLine()) {
            line = hyponym.readLine();
            int c = line.indexOf(",");
            int hypo = Integer.parseInt(line.substring(0, c));

            int prev = c;
            int index = line.indexOf(",", prev + 1);
            if (index > 0) {
                while (index > 0) {
                    g.addEdge(hypo, Integer.parseInt(line.substring(prev + 1, index)));
                    prev = index;
                    index = line.indexOf(",", prev + 1);
                }
                g.addEdge(hypo, Integer.parseInt(line.substring(prev + 1)));
            } else {
                g.addEdge(hypo, Integer.parseInt(line.substring(c + 1)));
            }
        }


    }
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }


    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        HashSet<String> result = new HashSet<String>();
        result.add(word);

        if (stringInt.containsKey(word)) {
            Set<Integer> s = GraphHelper.descendants(g, stringInt.get(word));
            for (Integer i : s) {
                HashSet<String> hs = intString.get(i);
                for (String nm : hs) {
                    result.add(nm);
                }
            }
        }
        return result;


    }

}
