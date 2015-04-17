package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;

public class WordNet {

    //Instance Variables\\

    private Digraph DG;
    private ArrayList<HashSet<String>> synsetIDs;
    private int size;

    //Constructor\\

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        //read in files
        In synsetsFile = new In(synsetFilename);
        In hyponymsFile = new In(hyponymFilename);

        //find total number of synsets so that resizing doesn't increase runtime
        String[] lines = synsetsFile.readAll().split("\n");
        String last = lines[lines.length - 1];
        this.size =  Integer.parseInt(last.split(",")[0]);

        //make the data structures of the right size
        this.synsetIDs = new ArrayList<HashSet<String>>(size);
        this.DG = new Digraph(size + 1);

        //reread synsets to store synsets
        synsetsFile = new In(synsetFilename);

        //read synsetsFile line by line and add the syns to a HashSet for storage
        for (String sline : synsetsFile.readAll().split("\n")) {
            String[] slist = sline.split(",");
            HashSet<String> newsynsetIDs = new HashSet<String>();
            for (String syn : slist[1].split(" ")) {
                newsynsetIDs.add(syn);
            }
            int id = Integer.parseInt(slist[0]);
            this.synsetIDs.add(id, newsynsetIDs);
        }

        //read hyponymFile line by line and add the relations of the synsets indecies to Digraph
        for (String hline : hyponymsFile.readAll().split("\n")) {
            String[] hlist = hline.split(",");
            int hypernym = Integer.parseInt(hlist[0]);
            for (int i = 1; i < hlist.length; i += 1) {
                int hyponym = Integer.parseInt(hlist[i]);
                this.DG.addEdge(hypernym, hyponym);
            }
        }

    }

    //Other Methods\\

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (HashSet<String> hs : synsetIDs) {
            if (hs.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> allNouns = new HashSet<String>(size);
        for (HashSet<String> hs : synsetIDs) {
            allNouns.addAll(hs);
        }
        return allNouns;
        
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> occurances = getID(word);
        HashSet<String> allHyps = new HashSet<String>(1);
        for (int j : GraphHelper.descendants(DG, occurances)) {
            allHyps.addAll(synsetIDs.get(j));
        }
        return allHyps;
    }

    //returns a set of all the indecies for the occurance of the word
    private HashSet<Integer> getID(String word) {
        HashSet<Integer> occurances = new HashSet<Integer>(1);
        for (int i = 0; i < synsetIDs.size(); i += 1) {
            if (synsetIDs.get(i).contains(word)) {
                occurances.add(i);
            }
        }
        return occurances;
    }

}
