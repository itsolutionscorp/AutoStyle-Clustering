package ngordnet;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Collections;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private In inSyn, inHyp;
    private int numSynsets = 1;
    private Digraph net;
    private String synsetFile;
    private String hyponymFile;
    private HashSet<String> words;
    private HashMap<String, HashSet<Integer>> hm;
    private HashMap<Integer, String[]> hm2;
    private HashSet<String> ret;

    public WordNet(String synsetFilename, String hyponymFilename) { 
        inSyn = new In(synsetFilename);
        inHyp = new In(hyponymFilename);

        synsetFile = synsetFilename;
        hyponymFile = hyponymFilename;
        String line;

        while (true) { //count number of synsets for # of V for digraph
            line = inSyn.readLine();
            if (line == null) { //reached end of file
                break;
            }
            numSynsets++;
        }
        //for nouns()
        inSyn = new In(synsetFilename);
        words = new HashSet<String>();
        String[] tokens, synonyms;

        while (true) {
            line = inSyn.readLine();
            if (line == null) { //reached end of file
                break;
            }
            tokens = line.split(",");
            synonyms = tokens[1].split(" ");

            Collections.addAll(words, synonyms);
        }
        inSyn.close();
        //for hyponyms()
        inHyp = new In(hyponymFile);
        net = new Digraph(numSynsets);
        
        String[] strHyp;
        int[] intHyp;

        while (true) {
            line = inHyp.readLine();
            if (line == null) { //reached end of file
                break;
            }
            strHyp = line.split(",");
            intHyp = new int[strHyp.length];
            
            intHyp = convertToIntArray(strHyp); //type of list of hyponyms String--> int

            for (int i = 1; i < intHyp.length; i++) { //add hyponyms to digraph net
                net.addEdge(intHyp[0], intHyp[i]);
            }
        }
        inHyp.close();
        //for getID
        hm = new HashMap<String, HashSet<Integer>>();
        hm2 = new HashMap<Integer, String[]>();
        inSyn = new In(synsetFile);
        HashSet<String> hs = new HashSet<String>();

        while (true) {
            line = inSyn.readLine();
            if (line == null) { //reached end of file
                break;
            }
            tokens = line.split(",");
            synonyms = tokens[1].split(" ");

            for (int i = 0; i < synonyms.length; i++) {
                if (hm.get(synonyms[i]) == null) {
                    hm.put(synonyms[i], new HashSet<Integer>());
                }
                hm.get(synonyms[i]).add(Integer.parseInt(tokens[0]));
            }
            hm2.put(Integer.parseInt(tokens[0]), synonyms);
        }
        inSyn.close();
    } 

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String n : nouns()) {
            if (noun.equals(n)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        //In inSyn = new In(synsetFile);
        //HashSet<String> words = new HashSet<String>();
        return words;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        //In inHyp = new In(hyponymFile);
        return getWords(GraphHelper.descendants(net, getID(word)));
    }

    private HashSet<Integer> getID(String word) { 
        return hm.get(word);
    }

    private HashSet<String> getWords(Set<Integer> synIDs) {
        ret = new HashSet<String>();
        for (String word : hm.keySet()) {
            Set<Integer> temp = new HashSet<Integer>();
            temp.addAll(synIDs);
            temp.retainAll(hm.get(word));
            for (int id : temp) { //checks if ID of current line is in synIDs
                Collections.addAll(ret, hm2.get(id));
            }
        }
        return ret;
    }

    private int[] convertToIntArray(String[] numbers) {
        int[] re = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) { //type of list of hyponyms String--> int
            re[i] = Integer.parseInt(numbers[i]);
        }
        return re;
    }
}




        


