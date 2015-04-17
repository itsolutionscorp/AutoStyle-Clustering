package ngordnet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;

//Kevin Cheng

public class WordNet {
    /**
     * Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     * 
     * @throws FileNotFoundException
     */
    private ArrayList<HashSet<String>> wordSet;
    private HashMap<String, HashSet<Integer>> allWords;
    private HashSet<String> allNouns;
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        wordSet = new ArrayList<HashSet<String>>();
        allNouns = new HashSet<String>();
        allWords = new HashMap<String, HashSet<Integer>>();
        int wordCount = 0;
        int key = 0;
        String line = null;
        String[] lineArray = null;
        String[] words = null;
        File synset = new File(synsetFilename);
        Scanner input = null;
        try {
            input = new Scanner(synset);
            while (input.hasNextLine()) {
                HashSet<String> temp = new HashSet<String>();
                line = input.nextLine();
                lineArray = line.split(",");
                key = Integer.parseInt(lineArray[0]);
                words = lineArray[1].split(" ");
                for (String element : words) {
                    HashSet<Integer> numTemp = null;
                    if (!allNouns.contains(element)) {
                        numTemp = new HashSet<Integer>();
                    } else {
                        numTemp = allWords.remove(element);
                    }
                    numTemp.add(key);
                    temp.add(element);
                    allWords.put(element, numTemp);
                    allNouns.add(element);
                }
                wordSet.add(temp);
                wordCount++;
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        g = new Digraph(wordCount);
        File hyponym = new File(hyponymFilename);
        int v = 0;
        int w = 0;
        try {
            input = new Scanner(hyponym);
            while (input.hasNextLine()) {
                line = input.nextLine();
                lineArray = line.split(",");
                v = Integer.parseInt(lineArray[0]);
                for (int x = 1; x < lineArray.length; x++) {
                    w = Integer.parseInt(lineArray[x]);
                    g.addEdge(v, w);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allNouns;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        HashSet<String> temp = new HashSet<String>();
        if (!allWords.containsKey(word)) {
            return temp;
        }
        HashSet<Integer> keys = allWords.get(word);

        Set<Integer> setOfInts = GraphHelper.descendants(g, keys);
        for (Integer key : setOfInts) {
            temp.addAll(wordSet.get(key));
        }
        return temp;
    }
}
