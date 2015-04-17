package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.TreeMap;

public class WordNet {
    private Digraph d;
    private List<String> synsets;
    private List<String> hyponymList;
    private Scanner synScanner;
    private Scanner hypScanner; 
    private Set<String> nounSet;
    private Map<Integer, String> synsetIDS;
    private Map<Integer, String> hypPairs;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new ArrayList<String>();
        hyponymList = new ArrayList<String>();
        nounSet = new TreeSet<String>();
        synsetIDS = new TreeMap<Integer, String>();
        hypPairs = new TreeMap<Integer, String>();
        try {
            synScanner = new Scanner(new File(synsetFilename), "UTF-8");
            hypScanner = new Scanner(new File(hyponymFilename), "UTF-8");
            while (synScanner.hasNextLine()) {
                synsets.add(synScanner.nextLine());
            }
            while (hypScanner.hasNextLine()) {
                hyponymList.add(hypScanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }
        d = new Digraph(synsets.size());
        splitHyponyms();
        splitSynsetFile();
    }

    private void splitHyponyms() {
        for (String hyponym : hyponymList) {
            String[] split = hyponym.split(",", 2);
            int hypID = Integer.parseInt(split[0]);
            if (hypPairs.get(hypID) != null) {
                String newValue = hypPairs.get(hypID) + (",") + split[1];
                hypPairs.put(hypID, newValue);
            } else {
                hypPairs.put(hypID, split[1]);
            }
        }
    }

    private void splitSynsetFile() {
        for (String synset : synsets) {
            String[] splitLine = synset.split(",");
            int id = Integer.parseInt(splitLine[0]);
            synsetIDS.put(id, splitLine[1]);
            addNouns(splitLine[1]);
            if (hypPairs.get(id) != null) {
                String[] hyp = hypPairs.get(id).split(",");
                for (int i = 0; i < hyp.length; i++) {
                    d.addEdge(id, Integer.parseInt(hyp[i]));
                }
            }
        }
    }

    private void addNouns(String synset) {
        String[] synonyms = synset.split(" ");
        for (String synonym : synonyms) {
            nounSet.add(synonym);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String n : nounSet) {
            if (n.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns true if a String containing a set of synsets has NOUN. */
    private boolean hasNoun(String synset, String noun) {
        String[] split = synset.split(" ");
        for (String s: split) {
            if (s.equals(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> wordSynsets = new TreeSet<Integer>();
        for (Map.Entry<Integer, String> entry : synsetIDS.entrySet()) {
            if (hasNoun(entry.getValue(), word)) {
                wordSynsets.add(entry.getKey());
            }
        }
        Set<Integer> hyps = GraphHelper.descendants(d, wordSynsets);
        Set<String> ret = new TreeSet<String>();
        for (Integer h : hyps) {
            String[] split = synsetIDS.get(h).split(" ");
            for (String s : split) {
                ret.add(s);
            }
        }
        return ret;

    }

}
