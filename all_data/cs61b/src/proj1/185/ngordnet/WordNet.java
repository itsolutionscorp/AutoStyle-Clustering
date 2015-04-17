package ngordnet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;


import edu.princeton.cs.algs4.Bag; //Will be used in Map datastructure
import edu.princeton.cs.algs4.Digraph; 
import edu.princeton.cs.introcs.In; //Needed to read in files

public class WordNet {
    //edu.princeton.cs.algs4.Digraph
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Digraph graphHyponym;
    private Map<String, Bag<Integer>> mapNounVertex; 
    private Map<Integer, String> mapVertexSynset;

    public WordNet(String synsetFilename, String hyponymFilename) {
        mapNounVertex = new HashMap<String, Bag<Integer>>();
        mapVertexSynset = new HashMap<Integer, String>();

        parseSynset(synsetFilename);
        graphHyponym = new Digraph(mapNounVertex.size());
        parseHyponym(hyponymFilename);
    }
    private void parseSynset(String synsetFilename) {

        In synsetfile = null; //starts with null file to be safe
        synsetfile = new In(synsetFilename); 
        //I realized that using readAllLines without method sig is more speed effic.
        int valExpected = 0;
        while (synsetfile.hasNextLine()) {
            String stLine = synsetfile.readLine();
            // example from file :37481,electronic_instrument ...,a
            //(Cont) musical instrument that generates sounds electronically
            String[] lineargument = stLine.split(",");
            int valueid = Integer.parseInt(lineargument[0]);
            String stSynset = lineargument[1];
                
            if (valExpected != valueid) { //should not execute!
                throw new RuntimeException("Node:" + valueid + " is not expected:" + valExpected);
            }
            valExpected++;
            String[] lineargumentnoun = stSynset.split(" "); 
            //splits synsets up if there are multiple
            mapVertexSynset.put(valueid, stSynset); 
            //if entry is singular,will add to map
                
            for (String stNoun : lineargumentnoun) { //if multiple syns
                stNoun = stNoun.trim();
                if (stNoun.length() > 0) {
                    Bag<Integer> val = mapNounVertex.get(stNoun);
                    if (val == null) {
                        val = new Bag<Integer>();
                        mapNounVertex.put(stNoun, val);
                    }
                    val.add(valueid);
                }
            }
        }

        
    }
    private void parseHyponym(String hyponymFilename) {
        In hyponymfile = null;
        int edgenu = 0;
        hyponymfile = new In(hyponymFilename);
        while (hyponymfile.hasNextLine()) {
            String linefield = hyponymfile.readLine();

            // 35449,73260,35635,31223
            String[] entries = linefield.split(",");
            int valueid = Integer.parseInt(entries[0]); //first entry


            for (int i = 1; i < entries.length; i++) { //in case of multiple entries
                int hypid = Integer.parseInt(entries[i]);
                graphHyponym.addEdge(valueid, hypid);
                edgenu++;
            }
        }
         
    }
    public boolean isNoun(String noun) {
        return mapNounVertex.containsKey(noun); 
    }
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return mapNounVertex.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> setResult = new HashSet<String>();
        Bag<Integer> ov = mapNounVertex.get(word);
        if (ov == null) {
            //word is not known to use...
            setResult.add(word);
            return setResult;
        }
        Set<Integer> set = new HashSet<Integer>();
        for (int v : ov) {
            set.add(v);
        }
        Set<Integer> setVertex = GraphHelper.descendants(graphHyponym, set);

        for (int v: setVertex) {
            String stSynset = mapVertexSynset.get(v);
            String[] aStWords = stSynset.split(" ");
            for (String stWord : aStWords) {
                setResult.add(stWord);
            }
        }
        if (setResult.isEmpty()) {
            setResult.add(word);
        }
        return setResult; 
    }
}
