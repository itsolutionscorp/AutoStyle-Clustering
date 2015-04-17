package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

import java.util.TreeSet;
import java.util.Set;
import java.util.ArrayList;
// import java.util.HashMap; // something for maps later

//exception handling
// import java.io.IOException;


/*
*  Rundong Tian, proj1
*
*/



public class WordNet { 

    // instance variables:

    private Digraph dg; 
    private ArrayList<TreeSet<String>> numberedSynsetArray;
    // HashMap<String, Integer[]> wordMap;


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename)  { // throwsIOException
        
        // first start reading the inputs
        In synsetStream = new In(synsetFilename);
        In hyponymStream = new In(hyponymFilename);

        // if (!synsetStream.exists() || !hyponymStream.exists() ) {
        //     // throw new java.io.IOException("file not found");
        // }
        
        // create an arrayList of treesets. (default length = 10)
        numberedSynsetArray = new ArrayList<TreeSet<String>>(); 

        // read through the synset stream, and create an arrayList of treeSets
        while (synsetStream.hasNextLine()) {
            String sLine = synsetStream.readLine();
            String[] sLineSplit =  sLine.split(",");
            String[] sLineWords = sLineSplit[1].split(" ");

            TreeSet<String> s = new TreeSet<String>();
            // add the elements to the synset array
            for (int i = 0; i < sLineWords.length; i++) {

                s.add(sLineWords[i]);
            }

            numberedSynsetArray.add(s);


        }

        // set the number of vertices (i.e. the number of synsets), and create the digraph
        int numSynsets = numberedSynsetArray.size(); 
        dg = new Digraph(numSynsets);
        
        // read through the hyponym stream
        while (hyponymStream.hasNextLine()) {
            String hLine = hyponymStream.readLine();
            String[] hLineSplit =  hLine.split(",");

            // add new edges into the digraph
            int start = Integer.parseInt(hLineSplit[0]);            
            for (int i = 1; i < hLineSplit.length; i++) {
                dg.addEdge(start, Integer.parseInt(hLineSplit[i]));
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {

        for (int i = 0; i < numberedSynsetArray.size(); i++) {

            TreeSet<String> synset = numberedSynsetArray.get(i);
            if (synset.contains(noun)) {
                return true;
            }
        }
        return false;

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {

        TreeSet<String> ret = new TreeSet<String>(); // object to be returned

        //traverse through the arrayList, and add everything to ret
        for (int i = 0; i < numberedSynsetArray.size(); i++) { // through the arrayList
            // TreeSet don't want your duplicates.  convenient!
            ret.addAll(numberedSynsetArray.get(i)); 
        }

        return ret;

        
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {

        TreeSet<Integer> numberSet = new TreeSet<Integer>(); // object to be returned

        // get all synset containing the word
        for (int i = 0; i < numberedSynsetArray.size(); i++) { // through the arrayList
            if (numberedSynsetArray.get(i).contains(word)) {
                numberSet.add(i); // add the number to the set
            }
        }
        
        Set<Integer> vertices = GraphHelper.descendants(dg, numberSet);

        TreeSet<String> ret = new TreeSet<String>();
        for (int x: vertices) {
            ret.addAll(numberedSynsetArray.get(x));
        }
        
        
        return ret;

    }
}
