package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;

public class WordNet {
    private HashMap<Integer, ArrayList<String>> noun = new HashMap<Integer, ArrayList<String>>();
    //private HashMap<Integer, String> map = new HashMap<Integer, String>();
    private ArrayList<ArrayList<Integer>> hypoList = new ArrayList<ArrayList<Integer>>();
    private HashMap<Integer, Set<Integer>> children = new HashMap<Integer, Set<Integer>>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) { 
        In synset = new In(synsetFilename);
        In hypo = new In(hyponymFilename);

        while (synset.hasNextLine()) {
            String input = synset.readLine();
            String[] read = input.split(",");
            //map.put(Integer.parseInt(read[0]), read[2]);
            ArrayList<String> n = new ArrayList<String>();
            for (String word : read[1].split(" ")) {
                n.add(word);
            }
            noun.put(Integer.parseInt(read[0]), n);
        }

        while (hypo.hasNextLine()) {
            String input = hypo.readLine();
            String[] line = input.split(",");
            ArrayList<Integer> values = new ArrayList<Integer>();
            for (String n : line) {
                values.add(Integer.parseInt(n));
            }
            //hypoList.put(Integer.parseInt(line[0]), values);
            hypoList.add(values);
        }

        Digraph graph = new Digraph(noun.size());
        for (ArrayList lst: hypoList) {
            int key = (int) lst.get(0);
            for (int i = 1; i < lst.size(); i++) {
                graph.addEdge(key, (int) lst.get(i));
            }
        }
        //System.out.println(graph);
        
        for (Integer key: noun.keySet()) {
            Set<Integer> temp = new HashSet<Integer>();
            temp.add(key);
            //System.out.println(GraphHelper.descendants(graph, temp));
            children.put(key, GraphHelper.descendants(graph, temp));
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String n) {
        for (String str: nouns()) {
            if (str.equals(n)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> t = new HashSet<String>();
        for (Integer k: noun.keySet()) {
            for (int i = 0; i < noun.get(k).size(); i++) {
                t.add(noun.get(k).get(i));
            }
        }
        return t;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> t2 = new HashSet<String>();
        ArrayList<Integer> key = new ArrayList<Integer>();
        for (Integer k: noun.keySet()) {
            if (noun.get(k).contains(word)) {
                key.add(k);
            }
        }
        if (key == null) {
            throw new NullPointerException("word not found");
        } else {
            for (Integer k1: key) {
                for (Integer child: children.get(k1)) {
                    for (String n: noun.get(child)) {
                        // System.out.println(n);
                        t2.add(n);
                    }
                }
            }
        }
        return t2;
    }
}
