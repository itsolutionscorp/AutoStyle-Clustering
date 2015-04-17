package ngordnet;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;

import edu.princeton.cs.introcs.In;

public class WordNet {
    
    // Maps synset number to words in synset, including synonyms in same synset
    private TreeMap<Integer, Set<String>> synsetWords;

    // Maps each word to all of the different synset numbers that the word is located in
    private TreeMap<String, Set<Integer>> wordToSyn;

    // Maps each synset number to all the other synset numbers of its hyponyms
    private TreeMap<Integer, Set<Integer>> synRelations;

    // Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        synsetWords = new TreeMap<Integer, Set<String>>();
        wordToSyn = new TreeMap<String, Set<Integer>>();

        while (!synsetFile.isEmpty()) {
            Set<String> synsetWordsSet = new HashSet<String>();
            String line = synsetFile.readLine();
            String[] split1 = line.split(",", 3);
            int num = Integer.parseInt((String) split1[0]);
            String words = split1[1];
            String[] split2 = words.split(" ");

            for (String word : split2) {
                // add the word into the synsetWords map
                synsetWordsSet.add(word);
                // add the synset number to the wordToSyn map
                if (wordToSyn.containsKey(word)) {
                    Set<Integer> toUpdate = wordToSyn.get(word);
                    toUpdate.add(num);
                    wordToSyn.put(word, toUpdate);
                } else {
                    Set<Integer> toPut = new HashSet<Integer>();
                    toPut.add(num);
                    wordToSyn.put(word, toPut);
                }
            }
            synsetWords.put(num, synsetWordsSet);
        }

        In hyponymFile = new In(hyponymFilename);
        synRelations = new TreeMap<Integer, Set<Integer>>();

        while (!hyponymFile.isEmpty()) {
            String line = hyponymFile.readLine();
            String[] split3 = line.split(",");
            int num = Integer.parseInt((String) split3[0]);
            String[] split4 = new String[split3.length - 1];

            System.arraycopy(split3, 1, split4, 0, split3.length - 1);
            for (String relation : split4) {
                int link = Integer.parseInt(relation);
                if (synRelations.containsKey(num)) {
                    Set<Integer> toAdd = synRelations.get(num);
                    toAdd.add(link);
                    synRelations.put(num, toAdd);
                } else {
                    Set<Integer> toReturn = new HashSet<Integer>();
                    toReturn.add(link);
                    synRelations.put(num, toReturn);
                }
            }
        }
    }

    // Returns true if NOUN is a word in some synset.
    public boolean isNoun(String noun) {
        Set<String> nouns = this.nouns();
        return nouns.contains(noun);
    }

    // Returns the set of all nouns.
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (Set<String> set : synsetWords.values()) {
            for (String noun : set) {
                nouns.add(noun);
            } 
        }
        return nouns;
    }

    // Returns the set of all hyponyms of WORD as well as all 
    // synonyms of WORD. If WORD belongs to multiple synsets, 
    // return all hyponyms of all of these synsets. See 
    // http://goo.gl/EGLoys for an example. Do not include 
    // hyponyms of synonyms.
    public Set<String> hyponyms(String word) {

        Set<String> finalReturn = new HashSet<String>();
        Set<Integer> synsetsOfWord = wordToSyn.get(word);

        for (Integer synset : synsetsOfWord) {
            Set<String> almostThere = this.hyponymsRecursive(word, synset);
            for (String member : almostThere) {
                finalReturn.add(member);
            }
        }
        return finalReturn;
    }

    private Set<String> hyponymsRecursive(String word, Integer toRecurse) {
        Set<String> toReturn = new HashSet<String>();
        Set<String> valuesInSynset = synsetWords.get(toRecurse);
        for (String value : valuesInSynset) {
            toReturn.add(value);
        }
        if (synRelations.containsKey(toRecurse)) {
            Set<Integer> subSynsets = synRelations.get(toRecurse);
            for (Integer subSynset : subSynsets) {
                Set<String> addTotoReturn = this.hyponymsRecursive(word, subSynset);
                for (String member : addTotoReturn) {
                    toReturn.add(member);
                }
            }
        }
        return toReturn;
    }
}
