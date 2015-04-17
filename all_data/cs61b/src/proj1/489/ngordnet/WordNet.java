package ngordnet;

import java.util.Scanner;
import java.io.File;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import java.io.FileNotFoundException;

public class WordNet {

    private TreeMap<Integer, TreeSet<String>> synsetDict = new TreeMap<Integer, TreeSet<String>>();
    private TreeMap<String, TreeSet<Integer>> nouns = new TreeMap<String, TreeSet<Integer>>();
    private int digraphSize = 0;
    private Digraph g;

    /**
     * Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     */
    public WordNet(String synsetFilename, String hypnonymFilename) {
        try {
            Scanner synScan = new Scanner(new File(synsetFilename));
            Scanner hypScan = new Scanner(new File(hypnonymFilename));
            buildSynsetDictionary(synScan);
            g = new Digraph(digraphSize);
            buildHyponymDictionary(hypScan);

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    /* Builds synset Dictionary, gets digraph size. */
    private void buildSynsetDictionary(Scanner synScan) {

        Integer holdInt = 0;
        String holdStr;
        synScan.useDelimiter(",");

        while (synScan.hasNext()) {
            holdInt = (Integer) synScan.nextInt();
            holdStr = synScan.next();

            String[] syns = holdStr.split(" ");
            TreeSet<String> tempSet = new TreeSet<String>();
            for (String word : syns) {
                tempSet.add(word);
                TreeSet<Integer> newIntSet = new TreeSet<Integer>();
                if (nouns.containsKey(word)) {
                    newIntSet = nouns.get(word);
                    newIntSet.add(holdInt);
                } else {
                    newIntSet.add(holdInt);
                }
                digraphSize += 1;
                nouns.put(word, newIntSet);
            }

            synsetDict.put(holdInt, tempSet);
            synScan.nextLine();
        }
    }

    /* Builds hyponym Dictionary. */
    private void buildHyponymDictionary(Scanner hypScan) {

        while (hypScan.hasNextLine()) {
            String line = hypScan.nextLine();
            String[] splitted = line.split(",");
            Integer first = Integer.parseInt(splitted[0]);
            for (String splat : splitted) {
                g.addEdge(first, Integer.parseInt(splat));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.keySet().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns.keySet();
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {

        TreeSet<String> returnSet = new TreeSet<String>();
        returnSet.add(word);
        TreeSet<Integer> wordIDs = nouns.get(word);
        for (Integer id : wordIDs) {
            returnSet.addAll(synsetDict.get(id));
        }
        for (Integer otherID : GraphHelper.descendants(g, wordIDs)) {
            returnSet.addAll(synsetDict.get(otherID));
        }
        return returnSet;
    }
}
