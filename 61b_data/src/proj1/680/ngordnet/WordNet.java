package ngordnet;

import edu.princeton.cs.algs4.Digraph;
// import edu.princeton.cs.algs4.GraphHelper;
// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.IOException;
import edu.princeton.cs.introcs.In;
// import edu.princeton.cs.introcs.StdIn;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/**
 * Write a description of class WordNet here.
 * 
 * @author Darius Kay
 * @version The best
 */
public class WordNet {

    private HashMap<Integer, String> synsetIdentityA;

    private HashMap<String, Set<Integer>> synsetIdentityB;

    private HashMap<Integer, Set<String>> synsetSynonyms;

    private HashMap<Integer, Set<Integer>> hyponymMap;

    private Digraph wordNetDigraph;

    // Constructor for WordNet class
    public WordNet(String synsetFilename, String hyponymFilename) {

        synsetIdentityA = new HashMap<Integer, String>();
        synsetIdentityB = new HashMap<String, Set<Integer>>();
        synsetSynonyms = new HashMap<Integer, Set<String>>();
        hyponymMap = new HashMap<Integer, Set<Integer>>();
        readHyponyms(hyponymFilename);
        readSynsets(synsetFilename);

        int graphSize = 0;
        for (Integer each : hyponymMap.keySet()) {
            for (Integer every : hyponymMap.get(each)) {
                graphSize += 1;
            }
        }

        // Now that the appropriate maps have been created for both hyponyms and synsets,
        // WordNet will create the appropriate Digraph for the file inputs.
        wordNetDigraph = new Digraph(graphSize + 1);
        for (Integer each : hyponymMap.keySet()) {
            for (Integer every : hyponymMap.get(each)) {
                wordNetDigraph.addEdge(each, every);
            }
        }
    }

    // Returns true of String noun is in the current WordNet, 
    // using the nameString-synonyms association
    public boolean isNoun(String noun) {
        for (Integer each : hyponymMap.keySet()) {
            if (synsetSynonyms.get(each).contains(noun)) {
                return true;
            }
            if (synsetIdentityA.get(each).equals(noun)) {
                return true;
            }
        }
        return false;
    }

    // Returns a Set of the words in the current WordNet, using the ID-nameString association
    public Set<String> nouns() {

        Set<String> returnSet = new HashSet<String>();

        for (Integer each : synsetSynonyms.keySet()) {
            for (String every : synsetSynonyms.get(each)) {
                returnSet.add(every);
            }
        }
        return returnSet;
    }

    // Returns a set of the hyponyms and synonyms of String word. Uses nameString and Integer ID
    public Set<String> hyponyms(String word) {


        Set<String> returnSet = new HashSet<String>();
        returnSet.add(word);

        if (synsetIdentityB.keySet().contains(word)) {
            Set<Integer> code = synsetIdentityB.get(word);

            Set<Integer> setOfIDs = GraphHelper.descendants(wordNetDigraph, code);
            for (Integer every : setOfIDs) {
                System.out.println("Descendant of " + code + ": " + every);
            }

            for (Integer each : setOfIDs) {
                if (synsetIdentityA.get(each) != null) {
                    returnSet.add(synsetIdentityA.get(each));
                }
            }
        }

        return returnSet;
    }

    // Reads file, inserts ID, nameString, and synonyms into synsetIdentityA and synsetIdentityB
    private void readSynsets(String text) {

        In inSyn = new In(text);
        String cut = ",";



        int counter = 0;

        while (inSyn.hasNextLine()) {

            String newLine = inSyn.readLine();
            String[] newLineThings = newLine.split(cut);
            Integer key = Integer.parseInt(newLineThings[0]);
            String keyval = newLineThings[1];

            HashSet<String> otherStuff = new HashSet<String>();
            String[] synonyms = keyval.split(" ");
            for (int i = 0; i < synonyms.length; i += 1) {
                otherStuff.add(synonyms[i]);
                if (synsetIdentityB.keySet().contains(synonyms[i])) {
                    synsetIdentityB.get(synonyms[i]).add(key);
                }
                HashSet<Integer> iDAssociation = new HashSet<Integer>();
                iDAssociation.add(key);
                synsetIdentityB.put(synonyms[i], iDAssociation);
            }

            synsetSynonyms.put(key, otherStuff);
            synsetIdentityA.put(key, keyval);
        }
    }

    // Reads CSV file and inserts Integer ID and Integer[] of that ID's hyponyms
    private void readHyponyms(String text) {

        In inText = new In(text);
        String split = ",";


        while (inText.hasNextLine()) {

            
            String thisLine = inText.readLine();
            String[] divvyString = thisLine.split(",");

            HashSet<Integer> values = new HashSet<Integer>();

            int key = Integer.parseInt(divvyString[0]);

            if (hyponymMap.keySet().contains(key)) {
                for (int i = 1; i < divvyString.length; i += 1) {
                    hyponymMap.get(key).add(Integer.parseInt(divvyString[i]));
                }
            } else {
                for (int j = 1; j < divvyString.length; j += 1) {
                    values.add(Integer.parseInt(divvyString[j]));
                }
                hyponymMap.put(key, values);
            }
        }

    }

        // Set<Integer> wordHypoSet = new HashSet<Integer>();

        // if (synsetIdentityB.keySet().contains(word)) {
        //     wordHypoSet = synsetIdentityB.get(word);
        // }

        // Set<Integer> wordHyponymsSet = new HashSet<Integer>();

        // for (Integer each : wordHypoSet) {
        //     if (hyponymMap.keySet().contains(each)) {
        //         for (Integer every : hyponymMap.get(each)) {
        //             wordHyponymsSet.add(every);
        //             if (hyponymMap.keySet().contains(every)) {
        //                 for (Integer all : hyponymMap.get(every)) {
        //                     wordHyponymsSet.add(all);
        //                     if (hyponymMap.keySet().contains(every)) {
        //                         for (Integer all : hyponymMap.get(every)) {
        //                             wordHyponymsSet.add(all);
        //                             if (hyponymMap.keySet().contains(every)) {
        //                                 for (Integer all : hyponymMap.get(every)) {
        //                                     wordHyponymsSet.add(all);
        //                                 }
        //                             }
        //                         }
        //                     }
        //                 }
        //             }
        //         }
        //     }
        // }

        // for (Integer number : wordHyponymsSet) {
        //     if (synsetIdentityA.keySet().contains(number)) {
        //         returnSet.add(synsetIdentityA.get(number));
        //     }
        // }
        // for (Integer number2 : wordHypoSet) {
        //     if (synsetIdentityA.keySet().contains(number2)) {
        //         returnSet.add(synsetIdentityA.get(number2));
        //     }
        // }

/////////////////////////////////////////////////////////////////
        // HashSet<Integer> numSet = synsetIdentityB.get(word);
        // HashSet<Integer> hyponymSet = new HashSet<Integer>();
        // for (Integer each : numSet) {
        //     if (hyponymMap.keySet().contains(each)) {
        //         hyponymSet.add(hyponymMap.get(each));
        //     }
        // }
        // for (Integer this : hyponymSet) {
        //     if (synsetSynonyms.keySet().contains(this)) {
        //         for (String wordling : synsetSynonyms.get(this)) {
        //             returnSet.add(wordling);
        //         }
        //     }
        // }
        // for (Integer that : numSet) {
        //     if (synsetSynonyms.keySet().contains(that)) {
        //         for (String wordbit : synsetSynonyms.get(that)) {
        //             returnSet.add(wordbit);
        //         }
        //     }
        // }

        // returnSet.add(word);



        // System.out.println("Here's the word I'm looking for: " + word);
        // for (String every : synsetIdentityB.keySet()) {
        //     System.out.println("Here's a word in this WordNet: " + every);
        // }

        // Set<String> returnSet = new HashSet<String>();
        // Set<Integer> littleSet = new HashSet<Integer>();
        // if (synsetIdentityB.keySet().contains(word)) {
        //     for (Integer digit : synsetIdentityB.get(word)) {
        //         littleSet.add(digit);
        //     }
        //     Set<Integer> passInSet = GraphHelper.descendants(wordNetDigraph, littleSet);
        //     for (Integer num : passInSet) {
        //         for (String text : synsetIdentityB.keySet()) {
        //             for (Integer number : synsetIdentityB.get(text)) {
        //                 if (number == num) {
        //                     returnSet.add(text);
        //                 }
        //             }
        //         }
        //     }
        // }
        // returnSet.add(word);

        // if (synsetIdentityB.keySet().contains(word)) {
        //     int code = synsetIdentityB.get(word);
        //     Set<Integer> otherCodes = hyponymMap.get(code);
        //     otherCodes.add(code);

        //     for (Integer num : otherCodes) {
        //         for (String text : synsetIdentityB.keySet()) {
        //             if (synsetIdentityB.get(text).equals(num)) {
        //                 returnSet.add(text);
        //             }
        //         }
        //     }
}
