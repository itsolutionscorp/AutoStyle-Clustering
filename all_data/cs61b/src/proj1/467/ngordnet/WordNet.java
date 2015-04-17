package ngordnet; 
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class WordNet {
    private Digraph wordGraph;
    private HashMap<Integer, String[]> wordMap;
    private HashMap<String, Integer[]> wordMapFlip;
    private Set<String> nounSet;

    /** Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In syns = new In(synsetFilename);
        wordMap = new HashMap<Integer, String[]>();
        wordMapFlip = new HashMap<String, Integer[]>();
        int counter = 0;
        nounSet = new HashSet<String>();
        //Reads the synset file:
        while (syns.hasNextLine()) {
            //read the line, pull strings from it
            counter += 1;
            String line = syns.readLine();
            String[] rawTokens = line.split(",");
            //the synset id:
            String idIntStr = rawTokens[0];
            String tempTokens = rawTokens[1];
            String[] tokens;
            if (tempTokens.contains(" ")) {
                String[] tokensHolder = tempTokens.split(" ");
                tokens = new String[tokensHolder.length];
                tokens = tokensHolder;
            } else {
                tokens = new String[1];
                tokens[0] = tempTokens;
            }
            //adds the synonyms to wordMap:
            int idInt = Integer.parseInt(idIntStr);
            wordMap.put(idInt, tokens);
            //adds the nouns to nounSet:
            for (int i = 0; i < tokens.length; i++) {
                if (!wordMapFlip.containsKey(tokens[i])) {
                    Integer[] idArray = new Integer[1];
                    idArray[0] = idInt;
                    wordMapFlip.put(tokens[i], idArray);
                } else {
                    Integer[] prevArray = wordMapFlip.get(tokens[i]);
                    Integer[] newArray = new Integer[prevArray.length + 1];
                    for (int j = 0; j < newArray.length - 2; j++) {
                        newArray[j] = prevArray[j];
                    }
                    newArray[newArray.length - 1] = idInt;
                    wordMapFlip.put(tokens[i], newArray);
                }
                nounSet.add(tokens[i]);
            }
        }
        //Creates a wordGraph of appropriate size:
        int elementCount = counter;
        wordGraph = new Digraph(elementCount);
        In hyps = new In(hyponymFilename);
        //Reads hyponym file, adds edges:
        while (hyps.hasNextLine()) {
            String hline = hyps.readLine();
            String[] hrawTokens = hline.split(",");
            int parent = Integer.parseInt(hrawTokens[0]);
            Integer[] children = new Integer[hrawTokens.length - 1];
            for (int i = 0; i < children.length; i++) {
                children[i] = Integer.parseInt(hrawTokens[i + 1]);
            }
            for (int i = 0; i < children.length; i++) {
                wordGraph.addEdge(parent, children[i]);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (nounSet.contains(noun)) {
            return true;
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
        if (!isNoun(word)) {
            throw new NoSuchElementException();
        }
        Set<Integer> synIdSet = new HashSet<Integer>();
        Set<String> hypSet = new HashSet<String>();
        for (Integer i : wordMap.keySet()) {
            String[] possibilities = wordMap.get(i);
            for (String s : possibilities) {
                if (s.equals(word)) {
                    synIdSet.add(i);
                }
            }
        }
        Set<Integer> idNums = GraphHelper.descendants(wordGraph, synIdSet);
        for (Integer s : idNums) {
            for (int i = 0; i < wordMap.get(s).length; i++) {
                hypSet.add(wordMap.get(s)[i]);
            }   
        }
        return hypSet;
    }
}
