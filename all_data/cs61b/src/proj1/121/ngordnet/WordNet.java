package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


public class WordNet {
    // numberToWord now has (Key: number) and (Value: word)
    private Map<Integer, Set<String>> numberToWord = new HashMap<Integer, Set<String>>();
    //public Map<String, Integer> wordMultiple = new HashMap<String, Integer>();
    private Map<String, Set<Integer>> wordToNumber = new HashMap<String, Set<Integer>>();
    // wordRelations have all edges 
    private Digraph wordRelations;
    //public int count;

    public WordNet(String synsetFilename, String hyponymFilename) {
        /* readLine example
        file:  0,action,dummy     1,change,dummy     2,demotion,dummy  3,  .........
        In wordFile = new In("this file");
        String words = wordFile.readLine();
        System.out.println(words);
        Print:  0,action,dummy
        
        String[] wordSplit = words.split(",");
        wordSplit[0] = 0
        wordSplit[1] = action
        wordSplit[2] = dummy
        */

        In wordFile = new In(synsetFilename);
        In hypoFile = new In(hyponymFilename);
        int count = 0;
        while (wordFile.hasNextLine()) {
            count++;
            String[] wordsSplittedComma = wordFile.readLine().split(",");
            int numInt = Integer.parseInt(wordsSplittedComma[0]);
            String[] splitted = wordsSplittedComma[1].split(" "); 
            // list of words within a number
            Set<String> allWords = new HashSet<String>();

            for (int x = 0; x < splitted.length; x++) {
                allWords.add(splitted[x]);
                if (wordToNumber.containsKey(splitted[x])) {
                    Set<Integer> temp = wordToNumber.get(splitted[x]);
                    temp.add(numInt);
                    wordToNumber.put(splitted[x], temp);
                } else {
                    Set<Integer> temp = new HashSet<Integer>();
                    temp.add(numInt);
                    wordToNumber.put(splitted[x], temp);
                }
            }
            numberToWord.put(numInt, allWords);
        }

        wordRelations = new Digraph(count);
        while (hypoFile.hasNextLine()) {
            String[] numSplittedComma = hypoFile.readLine().split(",");
            int loc0 = Integer.parseInt(numSplittedComma[0]);
            for (int x = 1; x < numSplittedComma.length; x++) {
                wordRelations.addEdge(loc0, Integer.parseInt(numSplittedComma[x]));
            }
        }
    }

    public boolean isNoun(String noun) {
        return wordToNumber.containsKey(noun);
    }


    public Set<String> nouns() {
        return wordToNumber.keySet();
    }

    public Set<String> hyponyms(String word) {
        if (!wordToNumber.containsKey(word)) {
            return new HashSet<String>();
        }
        Set<Integer> allInts = wordToNumber.get(word);
        Set<String> toReturn = new HashSet<String>();
        Set<Integer> descendants = GraphHelper.descendants(wordRelations, allInts);
        for (int count: descendants) {
            for (String y : numberToWord.get(count)) {
                toReturn.add(y);
            }
        }
        return toReturn;
    }
}
