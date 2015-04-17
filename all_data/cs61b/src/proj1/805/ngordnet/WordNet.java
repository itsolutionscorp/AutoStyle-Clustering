package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class WordNet {

    private int numEdges;
    private String[] allSynStrings;
    private Map<Integer, Set<String>> synMap = new HashMap<Integer, Set<String>>();
    private Map<Set<String>, Integer> wordToId = new HashMap<Set<String>, Integer>();

    private Digraph masterDigraph;
    private String s;
    private String synString;
    private String[] z;
    private String t;
    private String[] q;
    private int hypoVertex;
    private Set<Integer> descSet = new TreeSet<Integer>();
    private Set<String> strAdd;
    private String[] hypInts;
    private String[] splitStrAdd;
    private String[] parts;
    private int synId;
    private String[] splitSynStr;
    private int hyperId;
    private String[] partz;

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hypernymFilename) {

        In synIn = new In(synsetFilename);
        In hypIn = new In(hypernymFilename);

        allSynStrings = synIn.readAllLines();
        hypInts = hypIn.readAllLines();
        numEdges = allSynStrings.length;
        Digraph d = new Digraph(numEdges);
        masterDigraph = d;

        /* Makes map of all synIds to their corresponding synSets */
        for (String line : allSynStrings) {
            parts = line.split(",");
            synId = Integer.parseInt(parts[0]);
            synString = parts[1];
            splitSynStr = synString.split(" "); //begin check to see if synset is many words
            
            if (splitSynStr.length > 1) {
                Set<String> synStringSet = new HashSet<String>();
                for (String word : splitSynStr) {
                    synStringSet.add(word);
                }
                synMap.put(synId, synStringSet);
                wordToId.put(synStringSet, synId);
            } else {
                Set<String> synStringSet = new HashSet<String>();
                synStringSet.add(synString);
                synMap.put(synId, synStringSet);
                wordToId.put(synStringSet, synId);
            }
        }

        /* Draws edges on Digraph between hypernyms and hyponyms */
        for (String hypLine : hypInts) {
            partz = hypLine.split(",");
            hyperId = Integer.parseInt(partz[0]);
            for (int i = 1; i < partz.length; i += 1) {
                int inputz = Integer.parseInt(partz[i]);
                masterDigraph.addEdge(hyperId, inputz);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return this.nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounz = new HashSet<String>();

        /** CITATION 1: I learned how to do a for-each loop through entries of a Map from
         * this stackoverflow.com post: 
         * http://stackoverflow.com/questions/1066589/iterate-through-a-hashmap */

        for (Map.Entry<Integer, Set<String>> entry : synMap.entrySet()) {
            Set<String> setNoun = entry.getValue();
            for (String word : setNoun) {
                nounz.add(word);
            }
        }
        return nounz;
    }

    
    /* Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> returnStringSet = new HashSet<String>();
        Set<Integer> intLookup = new TreeSet<Integer>();

        /** CITATION 1: See WordNet.java line 91 */
        for (Map.Entry<Set<String>, Integer> entry : wordToId.entrySet()) {
            Set<String> eSet = entry.getKey();
            int ind = entry.getValue();
            if (eSet.contains(word)) {
                intLookup.add(ind);
                // for (String eWord : eSet) {
                //     returnStringSet.add(eWord);
                // }
            }
        }

        descSet = GraphHelper.descendants(masterDigraph, intLookup);

        for (int vertex : descSet) {
            strAdd = synMap.get(vertex);
            for (String nounItem : strAdd) {
                splitStrAdd = nounItem.split(" ");
                if (splitStrAdd.length > 1) {
                    for (String wordzz : splitStrAdd) {
                        returnStringSet.add(wordzz);
                    }
                } else {
                    returnStringSet.add(nounItem);
                }
            }
        }
        
        return returnStringSet;
    }


}

  



