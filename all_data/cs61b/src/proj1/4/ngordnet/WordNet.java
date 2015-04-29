package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

//import com.sun.javafx.collections.MappingChange.Map;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Number of elements in the WordNet.*/
    private int vertices;
    /** Graph of the connections between Synsets corresponding to their IDs.*/
    private Digraph connections;
    /** Map from ID value to the Synset objects in the WordNet.*/
    private HashMap<Integer, Synset> synsetBankID;
    /** Map from noun to all of its Synset object IDs in the WordNet.*/
    private HashMap<String, HashSet<Integer>> synsetBankNoun;
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename)  {
        vertices = 0;
        connections = null;
        synsetBankID = new HashMap<Integer, Synset>();
        synsetBankNoun = new HashMap<String, HashSet<Integer>>();
        try {    
            BufferedReader in = new BufferedReader(new FileReader(synsetFilename));
            String line = "";
            while ((line = in.readLine()) != null) {
                String[] parts = line.split(",");
                Synset element = new Synset(Integer.parseInt(parts[0]), parts[1], parts[2]);
                synsetBankID.put(element.getId(), element);
                for (String s : element.getNouns()) {
                    if (synsetBankNoun.containsKey(s)) {
                        synsetBankNoun.get(s).add(element.getId());
                    } else {
                        HashSet<Integer> newSet = new HashSet<Integer>();
                        newSet.add(element.getId());
                        synsetBankNoun.put(s, newSet);
                    }
                }
            }            
            in.close();
            
            vertices = synsetBankID.size();
            connections = new Digraph(vertices);
            
            BufferedReader in2 = new BufferedReader(new FileReader(hyponymFilename));
            String line2 = "";
            while ((line2 = in2.readLine()) != null) {
                String[] lineElements = line2.split(",");
                int parent = Integer.parseInt(lineElements[0]);
                for (int i = 1; i < lineElements.length; i++) {
                    connections.addEdge(parent, Integer.parseInt(lineElements[i]));
                }
            }            
            in2.close();
        } catch (FileNotFoundException e) {
            System.out.println(synsetFilename + " file not found");
        } catch (IOException e) {
            System.out.println(synsetFilename + ": unexpected end of line");
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return synsetBankNoun.containsKey(noun);
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetBankNoun.keySet();
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        // Set of the Synset objects of all hyponyms.
        Set<Synset> setOfSynsets = new HashSet<Synset>();
        
        Set<Integer> idsOfWord = synsetBankNoun.get(word); 
        for (int iD: idsOfWord) {
            setOfSynsets.add(synsetBankID.get(iD));
        }
        
        Set<Integer> childrenId = GraphHelper.descendants(connections, idsOfWord);
        for (int iD: childrenId) {
            setOfSynsets.add(synsetBankID.get(iD));
        }
        
        return convertSynsetSet(setOfSynsets);
    }
    
    /** 
     * Returns the set of all nouns contained in the set of Synset objects. 
     * @param s a set of Synset objects
     * @return returns set of strings containing all the nouns in the set of synsets
     **/
    private Set<String> convertSynsetSet(Set<Synset> s) {
        HashSet<String> nouns = new HashSet<String>();
        for (Synset e : s) {
            String[] nounsOfCurrentSynset = e.getNouns();
            for (String n : nounsOfCurrentSynset) {
                nouns.add(n);
            } 
        }
        return nouns;
    }
}
