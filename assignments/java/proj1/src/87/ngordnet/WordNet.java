package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class WordNet {
    
    // Private fields for constructor
    private String id;
    private String set;
    private String gloss;
    private String[] hypset;
    private String hypernym;
    private String[] synset;
    private Digraph wordDigraph;
        
    // Map of synsets with ID as the key
    private Map synsetsbyID;
        
    // Map of synsets with Noun as the key
    private Map synsetsbyNoun;

    public WordNet(String synsetFilename, String hyponymFilename) {
        try (Scanner synScanner = new Scanner(new File(synsetFilename))) {
            synScanner.useDelimiter(",");
            synsetsbyID = new HashMap();
            synsetsbyNoun = new HashMap();
            while (synScanner.hasNextLine()) {
                String[] line = synScanner.nextLine().split(",");
                id = line[0];
                set = line[1];
                gloss = line[2];
                synset = set.split(" ");
                Set<String> synsetSetForm = new HashSet<String>();
                for (String item : synset) {
                    synsetSetForm.add(item);
                }
                synsetsbyID.put(id, synsetSetForm);
                List<Integer> addAlist = new ArrayList<Integer>();
                for (int i = 0; i < synset.length; i += 1) {
                    if (!synsetsbyNoun.containsKey(synset[i])) {
                        addAlist = new ArrayList<Integer>();
                    } else {
                        addAlist = (ArrayList<Integer>) synsetsbyNoun.get(synset[i]);
                    }
                    addAlist.add(Integer.parseInt(id));
                    synsetsbyNoun.put(synset[i], addAlist); 
                }      
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);    
        }   
        try (Scanner hypScanner = new Scanner(new File(hyponymFilename))) {
            wordDigraph = new Digraph(synsetsbyID.size());
            while (hypScanner.hasNextLine()) {
                hypset = hypScanner.nextLine().split(",");
                hypernym = hypset[0];
                int index = 1;
                while (index < hypset.length) {
                    wordDigraph.addEdge(Integer.parseInt(hypernym), 
                        (Integer.parseInt(hypset[index])));    
                    index += 1;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
        }     
    }    
        
    public boolean isNoun(String noun) {
        if (synsetsbyNoun.containsKey(noun)) {
            return true;
        } 
        return false;
    }

    public Set<String> hyponyms(String word) {
        Set<String> returnlist = new HashSet<String>();   
        if (synsetsbyNoun.containsKey(word)) {
            for (Integer dID : (ArrayList<Integer>) synsetsbyNoun.get(word)) {
                returnlist.addAll((Set<String>) synsetsbyID.get(dID.toString()));
                returnlist.addAll(children(dID));
            }
        }    
        return returnlist;
    }    

    private Set<String> children(Integer digraphID) {
        Set<String> returnlist = new HashSet<String>();
        Iterable<Integer> hyponymIDs = wordDigraph.adj(digraphID);
        if (!hyponymIDs.iterator().hasNext()) {
            if (synsetsbyID.containsKey(digraphID.toString())) {
                returnlist.addAll((Set<String>) synsetsbyID.get(digraphID.toString()));
            }    
            return returnlist;
        }    
        for (Integer dID : hyponymIDs) {
            if (synsetsbyID.containsKey(dID.toString())) {
                returnlist.addAll((Set<String>) synsetsbyID.get(dID.toString()));
            }
            returnlist.addAll(children(dID));
        }
        return returnlist;
    }

    public Set<String> nouns() {
        return synsetsbyNoun.keySet();        
    }
}


