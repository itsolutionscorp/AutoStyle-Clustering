package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;


public class WordNet {
    private Digraph graph;

    private Map<Integer, List<String>> wordId = new HashMap<Integer, List<String>>();
    private Map<String, List<Integer>> keys = new HashMap<String, List<Integer>>();

    private Set<String> wordSets = new HashSet<String>();


    public WordNet(String synsetFilename, String hypernymFilename) {
        In synsetFile = new In(synsetFilename);
        In hypernymFile = new In(hypernymFilename);

        int id = 0;
        while (synsetFile.hasNextLine()) {
            List<String> words = new ArrayList<String>();
            List<Integer> wordIds = new ArrayList<Integer>();
            String line = synsetFile.readLine();
            id = Integer.parseInt(line.split(",")[0]);
            String lineParts = line.split(",")[1];
            String [] wordParts = lineParts.split(" ");
            for (int i = 0; i < wordParts.length; i++) {
                wordSets.add(wordParts[i]);
                if (!words.contains(wordParts[i])) {
                    words.add(wordParts[i]);
                }
                if (keys.containsKey(wordParts[i])) {
                    wordIds = new ArrayList<Integer>(keys.get(wordParts[i]));
                }
                if (!wordIds.contains(id)) { //catches repetition
                    wordIds.add(id);
                }
                keys.put(wordParts[i], wordIds);
                wordId.put(id, words);
            }
        }
        graph = new Digraph(id + 1);

        while (hypernymFile.hasNextLine()) {
            String thisLine = hypernymFile.readLine();
            List<String> line = Arrays.asList(thisLine.split(",")); //From StackOverflow #10631715
            for (int i = 1; i < line.size(); i++) {
                graph.addEdge(Integer.parseInt(line.get(0)), Integer.parseInt(line.get(i)));
            }
        }
    }


    public boolean isNoun(String noun) {
        return wordSets.contains(noun);
    }

    public Set<String> hyponyms(String word) {
        List<Integer> tags = keys.get(word);
        Set<Integer> completeTags = hyponymId(tags);
        Set<String> returnWordset = wordIdMatch(completeTags);
        returnWordset.add(word); //Add the word itself back in
        return returnWordset;
    }

    private Set<Integer> hyponymId(List<Integer> tags) {
        Set<Integer> returnIds = new HashSet<Integer>();
        if (tags.size() <= 0) {
            return returnIds;
        }
        for (int tag: tags) {
            returnIds.add(tag); //Add the tags back in
            Iterable<Integer> adjacent = graph.adj(tag);
            for (int edge: adjacent) {
                returnIds.add(edge);
                //get hyponyms of hyponyms
                List<Integer> arg = new ArrayList<Integer>();
                arg.add(edge);
                Set<Integer> hyponymIds = hyponymId(arg);
                if (!hyponymIds.isEmpty()) {
                    // System.out.println("Going to level 2");
                    for (int id: hyponymIds) {
                        returnIds.add(id);
                    }
                }
            }
        }
        return returnIds;
    }

    private Set<String> wordIdMatch(Set<Integer> tags) {
        Set<String> returnWords = new HashSet<String>();
        for (int tag: tags) {
            List<String> words = wordId.get(tag);
            for (String word: words) {
                returnWords.add(word);
            }
        }
        return returnWords;
    }

    public Set<String> nouns() {
        return wordSets;
    }

}
