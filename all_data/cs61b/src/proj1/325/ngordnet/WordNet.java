package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {
    private HashMap<Integer, String> hashy;
    private HashSet<String> stry;
    private Digraph digy;
    public WordNet(String syn, String hyp) {
        hashy = new HashMap<Integer, String>();
        stry = new HashSet<String>();

        In synIn = new In(syn);
        while (synIn.hasNextLine()) {
            String line = synIn.readLine();
            String[] string = line.split(",");
            hashy.put(Integer.parseInt(string[0]), string[1]);
            if (string[1].contains(" ")) {
                String[] word = string[1].split(" ");
                for (String s: word) {
                    stry.add(s);
                }
            } else {
                stry.add(string[1]);
            }
        }
        digy = new Digraph(stry.size());

        In hypIn = new In(hyp);
        while (hypIn.hasNextLine()) {
            String ints = hypIn.readLine();
            String[] ids = ints.split(",");
            for (int s = 1; s < ids.length; s++) {
                digy.addEdge(Integer.parseInt(ids[0]), Integer.parseInt(ids[s]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset.*/
    public boolean isNoun(String noun) {
        return stry.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return stry;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> wordSet = new HashSet<String>();
        Set<Integer> index = new HashSet<Integer>();
        Set<Integer> indexSet = new HashSet<Integer>();
        for (Integer i : hashy.keySet()) {
            if (hashy.get(i).contains(" ")) {
                String[] words = hashy.get(i).split(" ");
                for (String ss: words) {
                    if (ss.equals(word)) {
                        for (String s: words) {
                            wordSet.add(s);
                        }
                        index.add(i);
                        indexSet.addAll(GraphHelper.descendants(digy, index));
                    }
                }
            } else {
                if (hashy.get(i).equals(word)) {
                    index = new HashSet<Integer>();
                    index.add(i);
                    indexSet.addAll(GraphHelper.descendants(digy, index));
                }
            }
        }

        for (Integer i : indexSet) {
            if (hashy.get(i).contains(" ")) {
                String[] words = hashy.get(i).split(" ");
                for (String s : words) {
                    wordSet.add(s);
                }
            } else {
                wordSet.add(hashy.get(i));
            }
        }
        // for (Integer i : indexSet2) {
        //     String[] words = hashy.get(i).split(" ");
        //     for (String ss: words) {
        //         wordSet.add(ss);
        //     }
        // }

        return wordSet;
    }
}
