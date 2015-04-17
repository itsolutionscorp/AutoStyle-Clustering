package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private In synsetsScanner;
    private In hyponymsScanner;
    private Digraph dg;
    private ArrayList<String []> synsets;
    private HomonymsHashMap nouns;

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetsPath, String hyponymsPath) {
         //get size from last element of synsets
        synsets = new ArrayList<String []>();
        nouns = new HomonymsHashMap();
        synsetsScanner = new In(synsetsPath);
        hyponymsScanner = new In(hyponymsPath);
        while (synsetsScanner.hasNextLine()) {
            String[] currentLine = synsetsScanner.readLine().split(",");
            int synsetID = Integer.parseInt(currentLine[0]);
            String synsetWords = currentLine[1];
            String[] synonyms = synsetWords.split(" ");
            synsets.add(synonyms);

            for (String synonym : synonyms) {
                nouns.add(synonym, synsetID);
            }

        }

        dg = new Digraph(synsets.size());

        while (hyponymsScanner.hasNextLine()) {
            String[] currentLine = hyponymsScanner.readLine().split(",");
            int currentWordID = Integer.parseInt(currentLine[0]);
            for (int i = 1; i < currentLine.length; i += 1) {
                dg.addEdge(currentWordID, Integer.parseInt(currentLine[i]));
            }
        }

        

    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {

        TreeSet<String> hyponyms = new TreeSet<String>();
        ArrayList<Integer> synsetsOfWord = nouns.get(word);
        for (int i = 0; i < synsetsOfWord.size(); i += 1) {
            int synsetID = synsetsOfWord.get(i);
            for (String synonym: synsets.get(synsetID)) {
                hyponyms.add(synonym);
            }
        }

        Set<Integer> descendantsIDs = GraphHelper.descendants(dg, nouns.getSet(word));
        for (Integer i : descendantsIDs) {
            for (String hyponym : synsets.get(i)) {
                hyponyms.add(hyponym);
            }
        }

        return hyponyms;

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {

        return nouns.keySet();
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String word) {

        return nouns.containsKey(word);
    }

}
