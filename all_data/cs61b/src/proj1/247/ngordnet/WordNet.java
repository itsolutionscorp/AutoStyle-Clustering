package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//author: Hongzhe Li
public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    //V means box, E means arrow
    private ArrayList<HashSet<String>> list;
    //one id may have multiple corresponding strings.
    private HashMap<String, HashSet<Integer>> map;
    //one word may have multiple Ids.
    private Digraph graph;

    //System.out.println(wn.isNoun("jump"));
    public WordNet(String synsetFilename, String hyponymFilename) {
        In in = new In(synsetFilename); 
        list = new ArrayList<HashSet<String>>();
        map = new HashMap<String, HashSet<Integer>>();

        while (in.hasNextLine()) {
            String[] set = in.readLine().split(",");
            int id = Integer.parseInt(set[0]);
            HashSet<String> words = new HashSet<String>();
            for (String word : set[1].split(" ")) {
                words.add(word);
                if (!map.containsKey(word)) {
                    map.put(word, new HashSet<Integer>());
                }
                map.get(word).add(id);
            }
            list.add(words);
        }

        graph = new Digraph(list.size());

        in = new In(hyponymFilename);
        while (in.hasNextLine()) {
            String[] num = in.readLine().split(",");
            int up = Integer.parseInt(num[0]);
            for (int i = 1; i < num.length; i += 1) {
                int low = Integer.parseInt(num[i]);
                graph.addEdge(up, low);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return map.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> ret = new HashSet<String>(); 
        for (Set<String> words : list) {
            ret.addAll(words);
        }
        return ret;
    }


    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<String> ret = new HashSet<String>();
        if (map.containsKey(word)) {
            for (int id : GraphHelper.descendants(graph, map.get(word))) {
                ret.addAll(list.get(id));
            }
        } else {
            return null;
        }
        ret.add(word);
        return ret;
    }
}
