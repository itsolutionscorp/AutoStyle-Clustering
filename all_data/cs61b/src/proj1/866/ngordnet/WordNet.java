package ngordnet;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Set;

import edu.princeton.cs.introcs.In;

public class WordNet {

    private HashMap<Integer, String[]> synsets = new HashMap<Integer, String[]>(); // map
    // of
    // synsets.
    // key
    // is
    // id.
    // value
    // is
    // an
    // array
    // of
    // each
    // word

    private HashMap<Integer, ArrayList<Integer>> hypoMap = 
            new HashMap<Integer, ArrayList<Integer>>(); // map
    // of
    // hyponyms.
    // key
    // is

    private TreeSet<String> hypoSet = new TreeSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {

        // Sets up map of synsets
        In synsetInput = new In(synsetFilename);
        while (synsetInput.hasNextLine()) { // while there is still a next line
            String currentLine = synsetInput.readLine();
            String[] temp = currentLine.split(","); // splits each line into an
                                                    // array of strings: [index,
                                                    // words, definition]
            Integer index = new Integer(Integer.parseInt(temp[0])); // converts
                                                                    // index
                                                                    // from
                                                                    // string to
                                                                    // integer
            String[] words = temp[1].split(" "); // splits words into each word
                                                 // separate by a space
            synsets.put(index, words); // insert index and array of words into
                                       // map
        }

        // Sets up 2D array of hyponyms
        In hypoInput = new In(hyponymFilename);
        while (hypoInput.hasNextLine()) {
            String currentLine = hypoInput.readLine();
            if (currentLine == null) {
                continue;
            }
            String[] temp = currentLine.split(",");
            ArrayList<Integer> words1 = new ArrayList<Integer>(0);
            for (String s : temp) {
                Integer wordID = Integer.parseInt(s);
                String[] synonyms = synsets.get(wordID);
                words1.add(wordID);
                for (String s1 : synonyms) {
                    hypoSet.add(s1);
                }
            }
            Integer index1 = words1.get(0);
            words1.remove(0);
            hypoMap.put(index1, words1);
        }
    }

    public boolean isNoun(String noun) {
        return hypoSet.contains(noun);
    }

    public Set<String> nouns() {
        return hypoSet;
    }

    private ArrayList<Integer> findSynset(String word) {
        ArrayList<Integer> returnArray = new ArrayList<Integer>(0);
        for (Integer i : synsets.keySet()) {
            String[] currentArray = synsets.get(i);
            for (String s : currentArray) {
                if (s.equals(word)) {
                    returnArray.add(i);
                }
                continue;
            }
        }
        return returnArray;
    }

    private Set<String> findHypo(ArrayList<Integer> hyponyms) {
        TreeSet<String> returnSet = new TreeSet<String>();
        for (Integer i : hyponyms) {
            try {
                returnSet.addAll(findHypo(hypoMap.get(i)));
            } catch (NullPointerException e) {
                // continue loop
            }

            for (String s : synsets.get(i)) {
                returnSet.add(s);
            }
        }
        return returnSet;
    }

    public Set<String> hyponyms(String word) {
        TreeSet<String> returnSet = new TreeSet<String>();
        if (isNoun(word)) {
            for (Integer i : findSynset(word)) {
                for (String s : synsets.get(i)) {
                    returnSet.add(s);
                }
                ArrayList<Integer> hyponyms = hypoMap.get(i);
                if (hyponyms == null) {
                    continue;
                } else {
                    returnSet.addAll(findHypo(hyponyms));
                }
            }

        }
        return returnSet;
    }

}
