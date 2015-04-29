package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {

    private In synsetsFile;
    private HashMap<Integer, Set> synsetsMap = new HashMap<Integer, Set>();
    private In hyponymsFile;
    private Digraph graph;
    private HashMap<String, Set> idMap = new HashMap<String, Set>();
    private HashMap<Integer, String> defMap = new HashMap<Integer, String>();
        
    
    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetsFile = new In(synsetFilename);
        hyponymsFile = new In(hyponymFilename);
        int key = 0;
        String value1 = "";
        String value2 = "";

        while (synsetsFile.hasNextLine()) {
            //Set<Integer> ids = new TreeSet<Integer>();
            Set<String> syns = new TreeSet<String>();
            String line = synsetsFile.readLine();
            String[] lineElements = line.split(",");
            key = Integer.parseInt(lineElements[0]);
            value1 = lineElements[1];
            String[] value1Split = value1.split(" ");
            for (String word : value1Split) {
                Set<Integer> ids = new TreeSet<Integer>();
                syns.add(word);
                if (idMap.containsKey(word)) {
                    idMap.get(word).add(key); 
                } else {
                    ids.add(key);
                    idMap.put(word, ids);
                }
            }
            synsetsMap.put(key, syns);
            value2 = lineElements[2];
            defMap.put(key, value2);
        }


        graph = new Digraph(synsetsMap.size());
        String hLine;
        int hypernym;
        int hyponym;

        while (hyponymsFile.hasNextLine()) {
            hLine = hyponymsFile.readLine();
            String[] hLineElements = hLine.split(",");
            hypernym = Integer.parseInt(hLineElements[0]);
            for (int j = 1; j < hLineElements.length; j++) {
                hyponym = Integer.parseInt(hLineElements[j]);
                graph.addEdge(hypernym, hyponym);
            }
        }
    }

            
         
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        Set<String> nouns = idMap.keySet();
        return nouns.contains(noun);
    }
        

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return idMap.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> allHypsSyns = new TreeSet<String>();
        Set<Integer> wordIDs = idMap.get(word);
        Set<Integer> synIDs = new TreeSet<Integer>();
        for (Integer id : wordIDs) {
            //Add all of the synonynms to our result set.
            if (synsetsMap.containsKey(id)) {
                allHypsSyns.addAll(synsetsMap.get(id));
                synIDs.add(id);
            }
            //Get all the hyponyms of each synset.
        }
        Set<Integer> H = GraphHelper.descendants(graph, synIDs);
        for (int num : H) {
            //Once have the hyponyms' ids, need to get the actual nouns.
            allHypsSyns.addAll(synsetsMap.get(num));
        }
        return allHypsSyns;
    }
}
