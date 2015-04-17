package ngordnet;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private HashMap<Integer, String> id2Synset;
    private HashMap<String, Integer> synset2Id;
    private HashMap<String, ArrayList<Integer>> nounAndId;
    private Digraph graph;


    public WordNet(String synset, String hyponyms) {
        // stnset = [(int)synset id, synonym set, dictionary definition]
        // hyponyms = [synset id, hyponyms...]
        id2Synset = new HashMap<Integer, String>();
        synset2Id = new HashMap<String, Integer>();
        nounAndId = new HashMap<String, ArrayList<Integer>>();
        synBuilder(synset);
        graph = hypBuilder(hyponyms);

    }

    public boolean isNoun(String noun) {
        return nounAndId.containsKey(noun);
    }

    public Set<String> nouns() {
        return nounAndId.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<String> temp = new HashSet<String>();
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> container;
        if (synset2Id.get(word) != null) {
            set.add(synset2Id.get(word));
        }
        for (Integer i : nounAndId.get(word)) {
            set.add(i);
        }
        try {
            container = GraphHelper.descendants(graph, set);
        } catch (NullPointerException e) {
            container = null;
            System.err.println("null set");
        }



        ArrayList<String> output = new ArrayList<String>();
        if (container != null) {
            for (int i : container) {
                output.add(id2Synset.get(i));
            }
        }
        // hypernyms
        if (output != null) {
            for (String noun: output) {
                for (String brayk: noun.split(" ")) {
                    if (!temp.contains(brayk)) {
                        temp.add(brayk);
                    }
                }
            }
        }

        // synonyms
        if (nounAndId.get(word) != null) {
            for (int key : nounAndId.get(word)) {
                for (String n : id2Synset.get(key).split(" ")) {
                    if (!temp.contains(n)) {
                        temp.add(n);
                    }
                }
            }
        }
        return temp;
    }



    private void synBuilder(String synset) {
        In file = new In(synset);
        String[] line;
        int id;
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (!file.isEmpty()) {
            line = file.readLine().split(","); // split by commas
            // Note: line = [id, synset, gloss]
            id = Integer.parseInt(line[0]);
            id2Synset.put(id, line[1]);
            synset2Id.put(line[1], id);
            // synset = [nouns]
            // nouns can be found through various ids and we want all of those IDs
            for (String noun : line[1].split(" ")) {
                list = nounAndId.get(noun);  // needs to be here and not in the if statement
                        // so that the else statement can append onto this list
                if (list == null) { // if you don't yet have the noun
                    list = new ArrayList<Integer>(); // need to initialize a new blank
                    list.add(id);
                    nounAndId.put(noun, list);
                } else { // there is already an instance for the noun
                    list.add(id);
                    nounAndId.put(noun, list);
                }
            }
        }
    }

    private Digraph hypBuilder(String hyponyms) {
        Digraph d = new Digraph(id2Synset.size());
        In file = new In(hyponyms);
        String[] line;
        int id;
        while (!file.isEmpty()) {
            line = file.readLine().split(",");
            id = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++) {
                d.addEdge(id, Integer.parseInt(line[i]));
            }
        }
        return d;
    }
}
