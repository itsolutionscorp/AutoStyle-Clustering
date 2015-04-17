package ngordnet;
//import GraphHelper;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class WordNet {
    private Digraph hyponymDG;
    private HashMap<String, ArrayList<Integer>> wordMap; 
    private ArrayList<String> words;
    
    /* Creates new Wordnet object. */
    public WordNet(String synsetFileName, String hyponymFileName) {
        In synsetReader = new In(synsetFileName);
        In hyponymReader = new In(hyponymFileName);
        wordMap = new HashMap<String, ArrayList<Integer>>();
        words = new ArrayList<String>();
        int synsetLength = 0;

        while (synsetReader.hasNextLine()) {
            String[] temp = synsetReader.readLine().split(",");
            int id = Integer.parseInt(temp[0]);
            words.add(id, temp[1]);
            String[] wordsArray = temp[1].split("\\s+");
            for (String word : wordsArray) {
                addWord(word, id);
            }
            synsetLength += 1;
        }

        hyponymDG = new Digraph(synsetLength);

        while (hyponymReader.hasNextLine()) {
            String[] temp = hyponymReader.readLine().split(",");
            int hypernym = Integer.parseInt(temp[0]);
            for (int i = 1; i < temp.length; i++) {
                hyponymDG.addEdge(hypernym, Integer.parseInt(temp[i]));
            }
        }
    }

    private void addWord(String word, Integer id) {
        if (wordMap.containsKey(word)) {
            wordMap.get(word).add(id); 
        } else {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(id);
            wordMap.put(word, temp);
        }
    }

    public Set<String> hyponyms(String word) {
        ArrayList<Integer> ids = wordMap.get(word);  
        Set<String> hypSet = new TreeSet<String>();
        Set<Integer> targetWord = new TreeSet<Integer>();
        for (int id : ids) {
            targetWord.add(id);
        }
        Set<Integer> hyponyms = GraphHelper.descendants(hyponymDG, targetWord);
        for (int hyp : hyponyms) {
            hypSet.addAll(Arrays.asList(words.get(hyp).split("\\s+")));
        }
        return hypSet;
    }

    
    public boolean isNoun(String word) {
        Set<String> words1 = wordMap.keySet();
        return words1.contains(word);

    }

    /* Returns iterable datatype containing all nouns. */
    public Set<String> nouns() {
        return wordMap.keySet();

    }

}
