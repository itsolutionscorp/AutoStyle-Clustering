package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet; 
import java.util.TreeMap;
import edu.princeton.cs.introcs.In;

/** An object that stores the WordNet graph 
  * in a manner useful for extracting all hyponyms of a word.
  * @author Anjali Suresh
  */
public class WordNet {

    private TreeMap<Integer, TreeSet<String>> idToSyns; 
    private TreeMap<String, TreeSet<Integer>> wordToIDs;
    private Digraph hyponymGraph; 

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        idToSyns = idMap(synsetFilename);
        wordToIDs = wordMap(idToSyns);
        hyponymGraph = hypGraphMaker(hyponymFilename);

    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (wordToIDs == null || noun == null) {
            return false; 
        }
        return wordToIDs.containsKey(noun);
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        if (wordToIDs == null) {
            return null; 
        }
        return wordToIDs.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, returns all hyponyms of
      * all of these synsets. 
      * Does not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (wordToIDs == null || word == null || wordToIDs.get(word) == null) {
            return null; 
        }

        TreeSet<String> hyponyms = new TreeSet<String>();

        TreeSet<Integer> ids = wordToIDs.get(word);

        Set<Integer> hypIDs = GraphHelper.descendants(hyponymGraph, ids);

        for (Integer id : hypIDs) {
            for (String syn : idToSyns.get(id)) {
                hyponyms.add(syn);
            }
        }

        return hyponyms;
    }

    /** Private method, returns a TreeMap that maps an ID number to a TreeSet set of synonyms,
      * using information gathered from SYNSETFILENAME.
      */
    private TreeMap<Integer, TreeSet<String>> idMap(String synsetFilename) {
        if (synsetFilename == null) {
            return null; 
        }

        Integer id;
        TreeMap<Integer, TreeSet<String>>  map = new TreeMap<Integer, TreeSet<String>>();

        In synput = new In(synsetFilename);
        while (synput.hasNextLine()) {

            TreeSet<String> syns = new TreeSet<String>();

            String line = synput.readLine();
            //citation: http://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
            String[] split = line.split(",");

            if (split.length < 3) {
                return null; 
            }

            //citation: https://blog.udemy.com/java-string-to-integer/
            id = Integer.valueOf(split[0]);
            String words = split[1];
            String[] synlist = words.split(" "); 
            for (String word : synlist) {
                syns.add(word);
            }

            map.put(id, syns);
        }


        return map; 

    }

    /** Private method, returns a TreeMap that maps a word to set of all ID numbers 
      * that map to it, using WORDMAP, created above, and effectively reversing it.
      */
    private TreeMap<String, TreeSet<Integer>> wordMap(TreeMap<Integer, TreeSet<String>> inMap) {

        if (inMap == null) {
            return null; 
        }

        TreeMap<String, TreeSet<Integer>> outMap = new TreeMap<String, TreeSet<Integer>>();

        for (Integer key : inMap.keySet()) {

            for (String val : inMap.get(key)) {

                if (outMap.get(val) == null) {
                    TreeSet<Integer> newSet = new TreeSet<Integer>();
                    newSet.add(key);
                    outMap.put(val, newSet); 
                
                } else {
                    outMap.get(val).add(key);
                }
            }
        }

        return outMap;
    }

    /** Private method, returns a Digraph that takes in HYPONYMFILENAME, and creates an edge
      * from each ID to the IDs of its hyponyms, as dictated in the file. 
      */
    private Digraph hypGraphMaker(String hyponymFilename) {
        if (hyponymFilename == null) {
            return null; 
        }

        Integer first;
        Digraph hyponyms = new Digraph(idToSyns.size()); 

        In hypinput = new In(hyponymFilename);
        while (hypinput.hasNextLine()) {
            String line = hypinput.readLine();
            String[] split = line.split(",");
            first = Integer.valueOf(split[0]);

            for (int i = 1; i < split.length; i += 1) {
                hyponyms.addEdge(first, Integer.valueOf(split[i]));
            }
        }

        return hyponyms;
    }
}
