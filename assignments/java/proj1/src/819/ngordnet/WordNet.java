package ngordnet; 

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File; 
import java.io.FileNotFoundException;


public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, HashSet<Integer>> hyponyms = new HashMap<Integer, HashSet<Integer>>();
    private HashMap<Integer, HashSet<String>> words = new HashMap<Integer, HashSet<String>>();
    private HashMap<HashSet<String>, Integer> wordKeys = new HashMap<HashSet<String>, Integer>(); 
    private HashSet<String> nouns = new HashSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            Scanner scan0 = new Scanner(new File(synsetFilename)).useDelimiter(",");
            Scanner scan1 = new Scanner(new File(hyponymFilename)).useDelimiter(",");
            Scanner lineScanner;
            Scanner spaceScanner; 

            while (scan0.hasNextLine()) {
                lineScanner = new Scanner(scan0.nextLine()).useDelimiter(",");
                int index = Integer.parseInt(lineScanner.next()); 
                String word = lineScanner.next();
                String definition = lineScanner.next();

                spaceScanner = new Scanner(word);
              
                words.put(index, new HashSet<String>());
                while (spaceScanner.hasNext()) {
                    words.get(index).add(spaceScanner.next());
                }
                
                wordKeys.put(words.get(index), index);
                nouns.addAll(words.get(index));
            }

            while (scan1.hasNextLine()) {
                lineScanner = new Scanner(scan1.nextLine()).useDelimiter(",");
                int first = Integer.parseInt(lineScanner.next());

                if (!(hyponyms.keySet().contains(first))) {
                    hyponyms.put(first, new HashSet<Integer>());
                    hyponyms.get(first).add(first);
                }
                while (lineScanner.hasNext()) {
                    int item = Integer.parseInt(lineScanner.next());
                    hyponyms.get(first).add(item);
                }      
            }
      
            scan0.close(); 
            scan1.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
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

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    private boolean hasHyponyms(HashSet<Integer> indices, Integer index) {
        return (!(indices.contains(index)) && hyponyms.keySet().contains(index));
    }

    private HashSet<Integer> modifyIndices(HashSet<Integer> indices) {
        HashSet<Integer> newIndices = new HashSet<Integer>(); 
        newIndices.addAll(indices);

        for (Integer index : indices) {
            if (hyponyms.keySet().contains(index)) {
                newIndices.addAll(hyponyms.get(index));
            }
        }

        return newIndices;
    }

    private HashSet<String> getHyponyms(HashSet<Integer> reference) {
        HashSet<String> hypoWords = new HashSet<String>();
        HashSet<Integer> indices = modifyIndices(reference);

        for (Integer index : indices) {
            hypoWords.addAll(words.get(index));
            if (hasHyponyms(reference, index)) {
                hypoWords.addAll(getHyponyms(indices));
            }
        }
        return hypoWords;
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> hypoWords = new HashSet<String>();
        HashSet<Integer> indices = new HashSet<Integer>(); 

        for (HashSet<String> value : words.values()) {
            if (value.contains(word)) {
                indices.add(wordKeys.get(value));
            }
        }
        hypoWords.addAll(getHyponyms(indices));
        return hypoWords;
    }

}
