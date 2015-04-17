package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

/* Digraph:
 * 
 * new Digraph with v verticies: 
 * Digraph(int V);
 * 
 * add an edge between vertex v and w:
 * addEdge(int v, int w);
 * 
 */

/*
 * current ideas:
 * synSet contains all the words, no repetitions                            X
 * synMap contains words as keys with corresponding numbers as values       X
 * hypD 
 */

public class WordNet {
    private In syn;
    private In hyp;
    private Map<Integer, String> synMap = new HashMap<Integer, String>();
    private Digraph hypD;
    private Set<String> nouns = new HashSet<String>();

    public WordNet(String synsetFilename, String hyponymFilename) {
        syn = new In(synsetFilename);
        hyp = new In(hyponymFilename);
        int numSynsets = 0;
        // adding stuff to synSet and synMap
        while (syn.hasNextLine()) {
            String curr = syn.readLine();
            String[] line = curr.split(",");
            int num = Integer.parseInt(line[0]);
            String word = line[1];
            if (word.contains(" ")) {
                String[] words = word.split(" ");
                for (String w : words) {
                    nouns.add(w);
                }
            } else {
                nouns.add(word);
            }
            synMap.put(num, word);
            numSynsets += 1;
        }

        // making the digraph
        hypD = new Digraph(numSynsets);
        int first;
        Stack<Integer> second = new Stack<Integer>();
        while (hyp.hasNextLine()) {
            String curr = hyp.readLine();
            String[] line = curr.split(",");
            int length = line.length;
            int[] numLine = new int[length];
            for (int i = 0; i < length; i++) {
                numLine[i] = Integer.parseInt(line[i]);
            }
            first = numLine[0];
            int[] rest = new int[length - 1];
            System.arraycopy(numLine, 1, rest, 0, length - 1);
            for (int i : rest) {
                second.push(i);
            }
            while (!second.isEmpty()) {
                hypD.addEdge(first, second.pop().intValue());
            }
        }

    }

    public boolean isNoun(String noun) {
        for (String word : nouns) {
            if (noun.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        return nouns;
    }

    private Stack<Integer> getWordNums(String word) {
        // get the num equivalent
        Stack<Integer> wordNums = new Stack<Integer>();
        for (Integer i : synMap.keySet()) {
            String st = synMap.get(i);
            if (st.contains(" ")) {
                String[] words = st.split(" ");
                for (String w : words) {
                    if (word.equals(w)) {
                        wordNums.push(i);
                    }
                }
            } else {
                if (word.equals(st)) {
                    wordNums.push(i);
                }
            }
        }
        return wordNums;
    }

    public Set<String> hyponyms(String word) {
        Stack<Integer> wordNums = getWordNums(word);
        Set<String> thing = new HashSet<String>();
        while (!wordNums.isEmpty()) {
            Integer wordNum = wordNums.pop();
            // get the hyponyms
            Set<Integer> wordNumSet = new TreeSet<Integer>();
            wordNumSet.add(wordNum);
            wordNumSet = GraphHelper.descendants(hypD, wordNumSet);
            for (Integer i : wordNumSet) {
                Stack<String> words = new Stack<String>(); // list of words with
                                                           // same num
                // loop through to find matching i
                for (Integer key : synMap.keySet()) {
                    // String key =
                    // "canine canine_tooth eyetooth eye_tooth dogtooth cuspid";
                    // adds all words to words
                    if (i.equals(key)) {
                        String st = synMap.get(key);
                        if (st.contains(" ")) {

                            String[] wArray = st.split(" ");
                            for (String w : wArray) {
                                words.push(w);
                            }
                        } else {
                            words.push(st);
                        }
                    }
                }
                while (!words.isEmpty()) {
                    thing.add(words.pop());
                }
            }
        }
        return thing;
    }

}
