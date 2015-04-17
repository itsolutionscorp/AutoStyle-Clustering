package ngordnet;

import edu.princeton.cs.algs4.Digraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.ArrayList;


public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Digraph digraph;
    // ID matches to string of synsets
    private Map<Integer, ArrayList<String>> mapofSynset;
    private int numNodes;
    private Set<String> nouns;

    public WordNet(String synsetFilename, String hyponymFilename) {
        numNodes = 0;
        mapofSynset = new HashMap<Integer, ArrayList<String>>();
        nouns = new HashSet<String>();
        try {
            String[] parts;
            String[] synonyms;  

            BufferedReader in = new BufferedReader(new FileReader(
                      synsetFilename));
            String line = " ";
            while ((line = in.readLine()) != null) {
                parts = line.split(",");
                synonyms = parts[1].split(" ");
                ArrayList<String> synsetArray = new ArrayList<String>();
                for (String word : synonyms) {
                    synsetArray.add(word);
                }
                mapofSynset.put(Integer.parseInt(parts[0]), synsetArray);
                nouns.add(parts[1]);
                numNodes++;
            }
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        digraph = new Digraph(numNodes);
        try {
            BufferedReader in2 = new BufferedReader(new FileReader(
                    hyponymFilename));
            String line2 = " ";
            String[] hyponyms; 
            while ((line2 = in2.readLine()) != null) {
                hyponyms = line2.split(",");
                int i = 1;
                while (i < hyponyms.length) {
                    digraph.addEdge(Integer.parseInt(hyponyms[0]),
                        Integer.parseInt(hyponyms[i]));
                    i++;
                }
            }
            in2.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Set<String> toAdd = new HashSet<String>();
        Set<String> toRemove = new HashSet<String>();
        for (String word : nouns) {

            if (word.contains(" ")) {
                String[] newWords = word.split(" ");
                toRemove.add(word);
                for (String newWord : newWords) {
                    toAdd.add(newWord);
                }
            }
        }
        for (String addThis : toAdd) {
            nouns.add(addThis);
        }
        for (String removeThis : toRemove) {
            nouns.remove(removeThis);
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.contains(noun);

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {

        return nouns;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> hypsandsyms = new HashSet<String>();
        Set<Integer> hypsandsymsNum = new HashSet<Integer>();
        Set<Integer> ints = new HashSet<Integer>();


        for (Integer key : mapofSynset.keySet()) {
            ArrayList<String> words = mapofSynset.get(key);
            for (String word1 : words) {
                if (word1.equals(word)) {
                    ints.add(key);
                }

            }
        }


        GraphHelper helpMe = new GraphHelper();

        hypsandsymsNum = helpMe.descendants(digraph, ints);
        for (Integer key : hypsandsymsNum) {
            ArrayList<String> hypWords = mapofSynset.get(key);
            for (String words : hypWords) {
                hypsandsyms.add(words);
            }
        }
        return hypsandsyms;

    }

}