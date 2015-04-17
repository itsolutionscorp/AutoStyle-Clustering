package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;


public class WordNet {
    private Map<Integer, List<String>> sMap = new HashMap<Integer, List<String>>();
    private Map<Integer, List<Integer>> hMap = new HashMap<Integer, List<Integer>>();
 

    private String line;
    private List<String> synLineList = new ArrayList<String>();
    private List<String> hypLineList = new ArrayList<String>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
    /** Creates a HashMap of the synsets. Results looks like this:
      * {0:"action"}, {1:"change"}, {2,"demotion"}
      */
        In syn = new In(synsetFilename);
        while ((line = syn.readLine()) != null) {
            synLineList.add(line);
        }
        for (String x : synLineList) {
            String[] eachLine = x.split(",");
            Integer num = Integer.parseInt(eachLine[0]);
            String[] synset = eachLine[1].split(" ");
            sMap.put(num, Arrays.asList(synset));
        }
        In hyp = new In(hyponymFilename);
        while ((line = hyp.readLine()) != null) {
            hypLineList.add(line);
        }
        /** Stores the first synset id as the key in the map, and the hypoyms
        * as a list of Integers
        */ 
        for (String x : hypLineList) {
            String[] eachLine = x.split(",");
            Integer id = Integer.parseInt(eachLine[0]);
            List<Integer> hyponyms = new ArrayList<Integer>();
            if (hMap.containsKey(id)) {
                hyponyms = hMap.get(id);
                for (int i = 1; i < eachLine.length; i++) {
                    hyponyms.add(Integer.parseInt(eachLine[i]));
                }
            } else {
                for (int i = 1; i < eachLine.length; i++) {
                    hyponyms.add(Integer.parseInt(eachLine[i]));
                }
                hMap.put(id, hyponyms);
            }
        }
    }

  /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer id : sMap.keySet()) {
            for (int i = 0; i < sMap.get(id).size(); i++) {
                if (noun.equals(sMap.get(id).get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

  /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> allNouns = new HashSet<String>();
        for (Integer id : sMap.keySet()) {
            for (int i = 0; i < sMap.get(id).size(); i++) {
                allNouns.add(sMap.get(id).get(i));
            }
        }
        return allNouns;
    }

  /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        Set<Integer> ids = getKey(sMap, word);


        Digraph g = new Digraph(sMap.size());      
        for (Integer ident : hMap.keySet()) {
            for (int i = 0; i < hMap.get(ident).size(); i++) {
                g.addEdge(ident, hMap.get(ident).get(i));
            }
        }

        Set<Integer> setOfID = GraphHelper.descendants(g, ids);

        Set<String> rtn = new TreeSet<String>();

        for (Integer x : setOfID) {
            for (int i = 0; i < sMap.get(x).size(); i++) {
                rtn.add(sMap.get(x).get(i));
            }
        }
        return rtn;
    }

    private Set<Integer> getKey(Map<Integer, List<String>> map, String value) {
        Set<Integer> ids = new TreeSet<Integer>();
        for (Integer o : map.keySet()) {
            for (int i = 0; i < map.get(o).size(); i++) {
                if (map.get(o).get(i).equals(value)) {
                    ids.add(o);
                }
            }
        }
        return ids; 
    }
}
