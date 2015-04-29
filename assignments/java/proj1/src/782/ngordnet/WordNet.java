package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;

public class WordNet {

    private String[] synset;
    private List<int[]> hyponym;
    private Digraph graph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synIn = new In(synsetFilename);
        In hypIn = new In(hyponymFilename);
        int synLength = 0;

        while (synIn.hasNextLine()) { //Count length of synset file
            synIn.readLine();
            synLength++;
        }

        synset = new String[synLength];
        hyponym = new ArrayList<int[]>();
        synIn = new In(synsetFilename); //Reset synIn

        for (int i = 0; i < synLength; i++) { //Store synset words in String arry
            String line = synIn.readLine();
            synset[i] = line.split(",")[1];
        }

        while (hypIn.hasNextLine()) {
            String nextLine = hypIn.readLine();
            String[] splitLine = nextLine.split(",");
            int[] values = new int[splitLine.length];

            for (int i = 0; i < splitLine.length; i++) {
                values[i] = Integer.parseInt(splitLine[i]);
            }

            hyponym.add(values);
        }

        graph = new Digraph(synLength); //Instantiate Digraph with synLength vertices

        for (int i = 0; i < hyponym.size(); i++) {
            int[] line = hyponym.get(i);
            for (int j = 1; j < line.length; j++) {
                graph.addEdge(line[0], line[j]);
            }
        }


    }

    public boolean isNoun(String noun) {
        for (String word : synset) {
            if (noun.equals(word)) {
                return true;
            }
            if (word.contains(noun + " ")) {
                return true;
            }
            if (word.contains(" " + noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> set = new TreeSet<String>();
        for (int i = 0; i < synset.length; i++) {
            String[] word = synset[i].split(" ");
            for (int j = 0; j < word.length; j++) {
                if (!set.contains(word[j])) {
                    set.add(word[j]);
                }
            }
        }
        return set;
    }

    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            Set<Integer> indexSet = new TreeSet<Integer>();
            for (int i = 0; i < synset.length; i++) {
                if (synset[i].equals(word)) {
                    indexSet.add(i);
                } else if (synset[i].contains(word + " ")) {
                    indexSet.add(i);
                } else if (synset[i].contains(" " + word)) {
                    indexSet.add(i);
                }
            }

            Set<Integer> set = GraphHelper.descendants(graph, indexSet);
            Set<String> wordSet = new TreeSet<String>();

            for (Integer wIndex : set) {
                String[] line = synset[wIndex].split(" ");
                for (int j = 0; j < line.length; j++) {
                    if (!wordSet.contains(line[j])) {
                        wordSet.add(line[j]);
                    }
                }
            }

            return wordSet;
        } else {
            throw new RuntimeException("Word not contained in set");
        }
    }
}
