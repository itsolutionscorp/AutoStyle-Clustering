package ngordnet;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private Digraph digraph;
    private TreeMap<String, TreeSet<Integer>> sortedNounTree;
    private TreeMap<Integer, String> sortedIDTree;
    private TreeMap<Integer, Integer[]> hyponyms;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);            
        int vertices = 0;
        
        sortedIDTree = new TreeMap<Integer, String>();
        sortedNounTree = new TreeMap<String, TreeSet<Integer>>();
        hyponyms = new TreeMap<Integer, Integer[]>();

        
        while (!synsetFile.isEmpty()) {
            String currentLine = synsetFile.readLine();            
            String[] tokens = currentLine.split(",");      
            int id = Integer.parseInt(tokens[0]);      
            String currentSyn = tokens[1];      
            String[] synElem = currentSyn.split(" ");

            sortedIDTree.put(id, currentSyn);
            
            for (int i = 0; i < synElem.length; i += 1) {                
                try {
                    TreeSet<Integer> checkKey = sortedNounTree.get(synElem[i]);
                    checkKey.add(id);
                    sortedNounTree.put(synElem[i], checkKey);
                } catch (NullPointerException e) {
                    TreeSet<Integer> intTSet = new TreeSet<Integer>();
                    intTSet.add(id);
                    sortedNounTree.put(synElem[i], intTSet);
                }
            }
            vertices += 1;
        }
        
        while (!hyponymFile.isEmpty()) {
            String currentLine = hyponymFile.readLine();
            String[] temp = currentLine.split(",");
            int hyper = Integer.parseInt(temp[0]);
            Integer[] hyp = new Integer[temp.length - 1];
            for (int i = 1; i < temp.length; i += 1) {
                hyp[i - 1] = Integer.parseInt(temp[i]);
            }
            if (!hyponyms.containsKey(hyper)) {
                hyponyms.put(hyper, hyp);
            } else {
                Integer[] prevArray = hyponyms.get(hyper);
                int prevArrayLen = prevArray.length;
                Integer[] newRelation = new Integer[prevArrayLen + hyp.length];
                for (int i = 0; i < prevArrayLen; i += 1) {
                    newRelation[i] = prevArray[i];
                }
                for (int i = 0; i < hyp.length; i += 1) {
                    newRelation[prevArrayLen + i] = hyp[i];
                }
                hyponyms.put(hyper, newRelation);
            }            
        }
        

        digraph = new Digraph(vertices + 1);
        Set<Integer> hypernyms = hyponyms.keySet();
        for (Integer hyperID : hypernyms) {
            for (Integer i : hyponyms.get(hyperID)) {
                this.digraph.addEdge(hyperID, i);
            }
        }

        /* Formulated contructor ideas with Raymond Yang */
        
    }
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return sortedNounTree.get(noun) != null;
    }
    
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return this.sortedNounTree.keySet();    
    }
    
    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> temp = new HashSet<String>();
        TreeSet<Integer> wordID = sortedNounTree.get(word);

        Set<Integer> intVertex = GraphHelper.descendants(digraph, wordID);

        for (Integer i : intVertex) {
            String[] hypo = sortedIDTree.get(i).split(" ");
            for (String item : hypo) {
                temp.add(item);
            }
        }
        return temp;
        
    }
    
}
