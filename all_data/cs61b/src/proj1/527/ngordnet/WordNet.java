package ngordnet;
/* An object that stores the WordNet graph in a manner useful 
   for extracting all hyponyms of a word. */ 

/* read input files and store them in ADTs of your choice that allow for rapid queries */ 

/* WordNet groups words into sets of synonyms called synsets
   and describes semantic relationships between them. */ 

/* read in the provided synset and hypernym datafiles */ 

import edu.princeton.cs.algs4.Digraph; 
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import java.util.Set; 
import java.util.HashSet; 
import java.util.List; 
import java.util.ArrayList; 

import java.util.TreeSet;

public class WordNet extends java.lang.Object {
    private int[] hypTokens; 
    private Digraph digraph; 

    //Map <Integer, String> map = new Map<Integer, String>();
    //private Set<String> set = new HashSet<String>(); 
    private Map<Integer, String> map = new HashMap<Integer, String>();

    private Set<String> set = new HashSet<String>(); 


    public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename) {
        In synset = new In(synsetFilename); 
        
        while (synset.hasNextLine()) { 
            String synStr = synset.readLine();
            //System.out.println(synStr); 
            String[] synTokensStr = synStr.split(",");
            //System.out.println(Integer.parseInt(synTokensStr[0]));
            //System.out.println(synTokensStr[1]); 

            map.put(Integer.parseInt(synTokensStr[0]), synTokensStr[1]); 
            String[] valueSpace = synTokensStr[1].split(" ");
            if (valueSpace.length == 1) {
                //System.out.println(Integer.parseInt(synTokensStr[0]) + "," + synTokensStr[1]);
                set.add(synTokensStr[1]); 
            } else {
                for (int i = 0; i < valueSpace.length; i++) {
                    //System.out.println(Integer.parseInt(synTokensStr[0]) + "," + valueSpace[i]);
                    set.add(valueSpace[i]); 
                }
            } 
            //System.out.println(Integer.parseInt(synTokensStr[0]) + "," + synTokensStr[1]);
            //map.put(Integer.parseInt(synTokensStr[0]), synTokensStr[1]); 
        }
        //System.out.println(map.size());  
        digraph = new Digraph(map.size()); 
        
        In hyponym = new In(hyponymFilename);
        while (hyponym.hasNextLine()) { 
            String hypStr = hyponym.readLine();
            String[] hypTokensStr = hypStr.split(",");
            hypTokens = new int[hypTokensStr.length]; 
            for (int i = 0; i < hypTokens.length; i++) {
                hypTokens[i] = Integer.parseInt(hypTokensStr[i]);
                //System.out.println(hypTokensStr[i]); 
            }

            int v = hypTokens[0]; 
            for (int i = 1; i < hypTokens.length; i++) {
                int w = hypTokens[i]; 
                //System.out.println(v + "," + w); 
                digraph.addEdge(v, w); 
            }
        }
    }
    
    public boolean isNoun(java.lang.String noun) {
        return set.contains(noun); 
    }
    
    public java.util.Set<java.lang.String> nouns() {
        /*Iterator iterator = set.iterator();

        while(iterator.hasNext(){
            String element = (String) iterator.next();
        } */ 
        return set; 
    }  

    /* Returns the set of all hyponyms of WORD as well as all synonyms of WORD. 
       If WORD belongs to multiple synsets, return all hyponyms of all of these synsets. 
       See http://goo.gl/EGLoys for an example. Do not include hyponyms of synonyms. */ 
    
    public java.util.Set<java.lang.String> hyponyms(java.lang.String word) {
        Set<String> hypSet = new HashSet<String>(); 
        List<Integer> keys = new ArrayList<Integer>();  
        if (!this.isNoun(word)) {
            return null; 
        } else {
            /* go through all the words of synset. see where the values are. return the values in 
               an int array */ 
            for (int i = 0; i < map.size(); i++) {
                String string = map.get(i); 
                String[] strTokens = string.split(" ");
                if (strTokens.length == 1) {
                    if (strTokens[0].equals(word)) {
                        keys.add(i); 
                    }
                } else {
                    for (int j = 0; j < strTokens.length; j++) {
                        //System.out.println(strTokens[j]); 
                        if (strTokens[j].equals(word)) {
                            keys.add(i); 
                        }
                    }
                }
            } 
        }

        Set<Integer> setKeys = new TreeSet<Integer>();
        for (int a = 0; a < keys.size(); a++) {
            setKeys.add(keys.get(a)); 
        } 

        Set<Integer> hypInts = GraphHelper.descendants(digraph, setKeys); 
        // System.out.println(hypInts);

        List<Integer> list = new ArrayList<Integer>(hypInts); 


        Set<String> toReturn = new HashSet<String>(); 
        for (int b = 0; b < list.size(); b++) {
            String string1 = map.get(list.get(b)); 
            String[] str1Tokens = string1.split(" ");
            if (str1Tokens.length == 1) {
                toReturn.add(str1Tokens[0]); 
            } else {
                for (int c = 0; c < str1Tokens.length; c++) {
                    //System.out.println(strTokens[j]); 
                    toReturn.add(str1Tokens[c]);
                }
            }
        } 
        return toReturn; 
    }
}
