//Citing ADT Decision Help: Arielle Spencer 

package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In; 
import java.util.HashMap; 
import java.util.HashSet; 
import java.util.Set;
import java.util.TreeSet;  

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */

    private HashMap<Integer, String[]> synsetmap = new HashMap<Integer, String[]>();
    private HashMap<Integer, String[]> hyponymmap = new HashMap<Integer, String[]>();

    public WordNet(String synsetFilename, String hyponymFilename) {

        In sysnetfile = new In(synsetFilename);
        In hyponymfile = new In(hyponymFilename);
        String line = new String("000");
        String line2 = new String("000");
        String[] syString = new String[3];
        String[] hypString = new String[100];


        while ((line = sysnetfile.readLine()) != null) {
            syString = line.split(",");
            synsetmap.put(Integer.parseInt(syString[0]), syString[1].split(" "));
        }


        while ((line2 = hyponymfile.readLine()) != null) {
            hypString = line2.split(",");
            String[] directHyp = new String[hypString.length];
            for (int i = 1; i < hypString.length; i++) {
                directHyp[i - 1] = hypString[i];
            }

            hyponymmap.put(Integer.parseInt(hypString[0]), directHyp);  
        } 

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {

        for (String[] value : synsetmap.values()) {
            for (int i = 0; i < value.length; i++) {
                if (value[i].equals(noun)) {
                    return true;
                } 
            }
        }

        return false; 

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
      //returns all the values of synsetmap
      
        HashSet<String> nounSet = new HashSet<String>();

        for (String[] value : synsetmap.values()) {
            for (int i = 0; i < value.length; i++) {
                nounSet.add(value[i]);
            }
        }

        return nounSet; 
    }

    /* Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */

    public Set<String> hyponyms(String word) {

      
        Digraph hypGraph = new Digraph(synsetmap.size());

        for (Integer key : hyponymmap.keySet()) {
            String[] temp = hyponymmap.get(key);
            int size = temp.length;
            for (int i = 0; i < size - 1; i++) {
                hypGraph.addEdge(key, Integer.parseInt(temp[i])); 
            }
        }


        Set<Integer> wordInts = new TreeSet<Integer>();

        for (Integer key : synsetmap.keySet()) {
            String[] temp2 = synsetmap.get(key);
            int size2 = temp2.length;
            for (int i = 0; i < size2; i++) {
                if (temp2[i].equals(word)) {
                    wordInts.add(key);
                }
            }
        }




        Set<Integer> graphhelp = GraphHelper.descendants(hypGraph, wordInts);
        HashSet<Integer> strs = new HashSet<Integer>(graphhelp.size());
        HashSet<String> hyps = new HashSet<String>(graphhelp.size());
        for (Integer integer : graphhelp) {
            strs.add(integer); 
        }

        for (Integer keys : strs) {
            for (String value : synsetmap.get(keys)) {
                hyps.add(value);
            }
        }

        return hyps;
    }



}

