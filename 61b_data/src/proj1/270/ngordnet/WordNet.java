package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.io.File;
import java.util.Map;

public class WordNet {
    
    private Digraph digraph;
    private Map<Integer, Set<String>> synsets;


    public WordNet(String synsetFilename, String hyponymFilename) {
        
        //Filling map of synsets.
        synsets = new HashMap<Integer, Set<String>>();
        In in = new In(new File(synsetFilename));
        while (in.hasNextLine()) {
            String[] items = in.readLine().split(",");
            int num = Integer.parseInt(items[0]);
            Set<String> wordList = new HashSet<String>();
            String[] words = items[1].split(" ");
            for (String word : words) {
                wordList.add(word);
            }
            synsets.put(num, wordList);
        }

        //Filling digraph.
        digraph = new Digraph(synsets.keySet().size());
        In scanner = new In(new File(hyponymFilename));
        while (scanner.hasNextLine()) {
            String line = scanner.readLine();
            String[] items = line.split(",");
            int hypernym = Integer.parseInt(items[0]);
            for (int i = 1; i < items.length; i++) {
                int hyponym = Integer.parseInt(items[i]);
                digraph.addEdge(hypernym, hyponym);
            }
        }
    }

    public boolean isNoun(String noun) {
        Set<String> nouns = nouns();
        return nouns.contains(noun);
    }

    public Set<String> nouns() {         
        //String[] items = in.readLine().split(",");
        Set<String> nouns = new HashSet<String>();
        for (Integer i : synsets.keySet()) {
            Set<String> synset = synsets.get(i);
            for (String noun : synset) {
                nouns.add(noun);
            }
        }
        return nouns;
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> startVertices = new HashSet<Integer>();
        for (Integer i : synsets.keySet()) {
            if (synsets.get(i).contains(word)) {
                startVertices.add(i);
            }
        }
        //Adding words in the same synset.
        for (Integer i : startVertices) {
            Set<String> synset = synsets.get(i);
            for (String synsetWord : synset) {
                if (!synsetWord.equals(word)) {
                    hyponyms.add(synsetWord);
                }
            }
        }
        //Adding descendant hyponyms.
        Set<Integer> descendants = GraphHelper.descendants(digraph, startVertices);
        for (Integer i : descendants) {
            Set<String> synset = synsets.get(i);
            for (String hyponym : synset) {
                hyponyms.add(hyponym);
            }
                
        }
        return hyponyms;
    }
}
