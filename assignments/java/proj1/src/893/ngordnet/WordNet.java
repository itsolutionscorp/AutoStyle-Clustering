package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.StringTokenizer;
import edu.princeton.cs.introcs.In;

//"In" reads from file input

public class WordNet {

    //instance variables
    private HashMap<Integer, Synset> synsets = new HashMap<Integer, Synset>(); 
    private HashMap<String, ArrayList<Integer>> synsetsInverse = 
                                new HashMap<String, ArrayList<Integer>>();
    private Digraph helper;

    //private class, synset
    private class Synset {  
        Integer i;
        ArrayList<String> synonyms;
        public Synset(Integer a, ArrayList<String> synonymsList) {
            this.i = a;
            this.synonyms = synonymsList;
        }
    }  

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        String lineS;
        String delim = ",";
        String delimSpace = " ";

        int counterH = 0;
        In synsetRead = new In(synsetFilename);
        In hyponymRead = new In(hyponymFilename);

        //parsing
        while (synsetRead.hasNextLine()) {
            lineS = synsetRead.readLine();
            StringTokenizer synsetTokenizer = new StringTokenizer(lineS, delim);
            Integer i = Integer.parseInt(synsetTokenizer.nextToken());
            //split word up
            String words = synsetTokenizer.nextToken();
            StringTokenizer wordsTok = new StringTokenizer(words, delimSpace);
            ArrayList<String> wordsArray = new ArrayList<String>();
            while (wordsTok.hasMoreTokens()) {
                String word = wordsTok.nextToken();
                wordsArray.add(word);
                //adding into inverse
                if (synsetsInverse.containsKey(word)) {
                    synsetsInverse.get(word).add(i);
                } else {
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    temp.add(i);
                    synsetsInverse.put(word, temp);
                }
            }
            //new synset object
            Synset syn = new Synset(i, wordsArray);
            synsets.put(i, syn);
            counterH++;
        }

        helper = new Digraph(counterH);

        while (hyponymRead.hasNextLine()) {
            String[] lineH = hyponymRead.readLine().split(",");
            for (int e = 1; e < lineH.length; e++) {
                helper.addEdge(Integer.parseInt(lineH[0]), Integer.parseInt(lineH[e]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return (synsetsInverse.containsKey(noun));
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetsInverse.keySet();
    } 

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        //digraph store synset ID's and all descendents are hyponyms
        ArrayList<Integer> ids = synsetsInverse.get(word);
        HashSet<String> ret = new HashSet<String>();
        for (Integer i : ids) {
            HashSet<Integer> hs = new HashSet<Integer>();
            hs.add(i);
            Set<Integer> children = GraphHelper.descendants(helper, hs);
            for (Integer child : children) {
                for (String str : synsets.get(child).synonyms) {
                    ret.add(str);
                }
            }
            for (String s : synsets.get(i).synonyms) {
                ret.add(s);
            }
        }
        return ret;
    }
}
