package ngordnet;

import edu.princeton.cs.algs4.Digraph;

import java.util.Set;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordNet {
    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    private Digraph net;
    private TreeMap<String, TreeSet<Integer>> dict;
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        
        /* creates net */
        int totalVert = dictMaker(synsetFilename);
        diGraphMaker(totalVert, hyponymFilename);
        
    }
    
    private int dictMaker(String synsetFilename) {
        try {
            int totalVert = 0;
            dict = new TreeMap<String, TreeSet<Integer>>();
            
            Scanner words = new Scanner(new File(synsetFilename));
            while (words.hasNextLine()) {
                totalVert += 1; // number of Digraph vertices
                String line = words.nextLine();
                String[] tokens = line.split(",");
                
                Integer value = Integer.parseInt(tokens[0]); // vertice number
                
                String[] keyList = tokens[1].split(" "); // synset
                
                /* creates dict */
                for (String s : keyList) {
                    if (dict.containsKey(s)) {
                        dict.get(s).add(value);
                    } else {
                        TreeSet<Integer> newList = new TreeSet<Integer>();
                        newList.add(value);
                        dict.put(s, newList);
                    }
                }
            }
            words.close();
            return totalVert;
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return 0;
        }
        
    }
    
    private void diGraphMaker(int numVertices, String hyponymFilename) {
        try {
            net = new Digraph(numVertices);
            Scanner nums = new Scanner(new File(hyponymFilename));
            
            while (nums.hasNextLine()) {
                String line = nums.nextLine();
                String[] tokens = line.split(",");
                Integer head = Integer.parseInt(tokens[0]);
                for (int i = 1; i < tokens.length; i++) {
                    net.addEdge(head, Integer.parseInt(tokens[i]));
                }
            }
            
            nums.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return dict.containsKey(noun);
        
    }
    
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return dict.keySet();
    }
    
    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        
        Set<Integer> vertNumSet = dict.get(word);
        Set<Integer> children = GraphHelper.descendants(net, vertNumSet); // set
                                                                          // of
                                                                          // all
                                                                          // vertices
                                                                          // reachable
                                                                          // from
                                                                          // word
        Set<String> hyponyms = new TreeSet<String>();
        
        // loop through dict keys and check if any of the vertices in
        // children appear in the treeset
        for (String s : dict.keySet()) {
            for (Integer i : children) {
                if (dict.get(s).contains(i)) {
                    hyponyms.add(s);
                }
            }
        }
        hyponyms.add(word);
        return hyponyms;
        
        
    }
}
