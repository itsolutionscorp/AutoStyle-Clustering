
package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;



public class WordNet {
    private HashMap<Integer, Set<String>> dictionary; 
    private Digraph hdigraph;
    private Set<String> nouns = new HashSet<String>();
    private String[] lastline;
    

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        dictionary = new HashMap<Integer, Set<String>>();
        In sInput = new In(synsetFilename);
        In hInput = new In(hyponymFilename);
        In hInputcopy = new In(hyponymFilename);


  //construct the synset dictionary. Map(int: set of strings)
        while (sInput.hasNextLine()) {
            String[] sline = sInput.readLine().split(",");
            int id = Integer.parseInt(sline[0]);
            String[] svalue = sline[1].split(" "); 
            Set<String> svalueSet = new HashSet<String>(Arrays.asList(svalue));
            dictionary.put(id, svalueSet);
        }

 //return all nouns in the dictionary
        Set<Set<String>> tempnoun = new HashSet<Set<String>>(dictionary.values());
        for (Set<String> element: tempnoun) {
            for (String s: element) {
                nouns.add(s);
            }
        }


 //count the number of vertices
        int vcount = 0;
        while (hInputcopy.hasNextLine()) {
            lastline = hInputcopy.readLine().split(",");
            for (String s:lastline) {
                int num = Integer.parseInt(s);
                if (num > vcount) {
                    vcount = num;
                }
            }
        }

//construct the hyponym digraphs
        hdigraph = new Digraph(vcount + 1);  
        while (hInput.hasNextLine()) {
            String[] hline = hInput.readLine().split(","); 
            int hid = Integer.parseInt(hline[0]);  
            for (int i = 1; i < hline.length; i = i + 1) {
                hdigraph.addEdge(hid, Integer.parseInt(hline[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */


// if the word is not present in the list, just return an empty set
    public Set<String> hyponyms(String word) {
        Set<Integer> keyset = dictionary.keySet();
        Set<Integer> resultSet = new TreeSet<Integer>();
        for (Integer key : keyset) {
            if (dictionary.get(key).contains(word)) {
                resultSet.add(key);
            }
        }

//decendents of the keys
        Set<Integer> resultkey = GraphHelper.descendants(hdigraph, resultSet);
        Set<Set<String>> tempvalue = new HashSet<Set<String>>();
        Set<String> resultvalue = new HashSet<String>();
        for (Integer key : resultkey) {
            tempvalue.add(dictionary.get(key));
        }

        for (Set<String> element : tempvalue) {
            for (String s : element) {
                resultvalue.add(s);
            }
        }
        return resultvalue;     
    }
}
