package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private In synset;
    private In hyponym;
    private HashMap<Integer, String> synsetMap = new HashMap<Integer, String>();
    private HashMap<Integer, List<String>> synonyms = new HashMap<Integer, List<String>>();
    private Digraph hyponymD;
    private Set<String> nouns = new TreeSet<String>();
    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new In(synsetFilename);
        hyponym = new In(hyponymFilename);
        //Creates hashmap of synsets
        while (synset.hasNextLine()) {
            //reads the current line
            String line = synset.readLine();
            //take out all commas
            String[] splitLine = line.split(",");
            //identify the key
            int key = Integer.parseInt(splitLine[0]);
            //identify the value
            String value = splitLine[1];
            //set of nouns
            String[] splitValue = value.split(" ");
            for (int i = 0; i < splitValue.length; i++) {
                nouns.add(splitValue[i]);
            }
            //put first word into HashMap
            synsetMap.put(key, splitValue[0]);
            //map of synonyms
            List<String> temp = new ArrayList<String>();
            for (int i = 0; i < splitValue.length; i++) {
                temp.add(i, splitValue[i]);
            }
            synonyms.put(key, temp);
        }
        hyponymD = new Digraph(synsetMap.size());
        //Creates digraph of hyponyms
        while (hyponym.hasNextLine()) {
            //read the current line
            String line2 = hyponym.readLine();
            //take out all commas
            String[] splitLine2 = line2.split(",");
            //identify the vertex
            int vertex = Integer.parseInt(splitLine2[0]);
            //add Edges
            for (int i = 1; i < splitLine2.length; i++) {
                hyponymD.addEdge(vertex, Integer.parseInt(splitLine2[i]));
            }
        }
    }
    
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }
    
    //Use a Set
    
    public Set<String> nouns() {
        return nouns;
    }
    
    //first we have to identify the numbers associated with the word
    //then we have to find the descendants of all those numbers
    //finally we match the decendants with the words and return it as a new Set<String>
    
    public Set<String> hyponyms(String word) {
        HashSet<Integer> synsetIDs = new HashSet<Integer>();
        Set<Integer> set = synonyms.keySet();
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            int key = iter.next();
            List<String> syn = synonyms.get(key);
            for (int j = 0; j < syn.size(); j++) {
                if (word.equals(syn.get(j))) {
                    synsetIDs.add(key);
                }           
            }
        }
        Set<Integer> hyponymIDs = GraphHelper.descendants(hyponymD, synsetIDs);
        HashSet<String> hyponyms = new HashSet<String>();
        for (int i = 0; i < synsetMap.size(); i++) {
            if (hyponymIDs.contains(i)) {
                hyponyms.add(synsetMap.get(i));
                if (synonyms.containsKey(i)) {
                    List<String> temp = synonyms.get(i);
                    for (int j = 1; j < temp.size(); j++) {
                        hyponyms.add(temp.get(j));
                    }
                }       
            }
        }
        return hyponyms;
    }
    
//  public static void main(java.lang.String[] args) {
//      
//  }
    
}
