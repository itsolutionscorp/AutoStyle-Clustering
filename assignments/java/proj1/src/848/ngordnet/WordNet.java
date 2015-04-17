package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordNet extends java.lang.Object {
    private Map<Integer, Set<String>> idSynsetPair;
    private Set<String> nouns;
    private Digraph hyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {

        idSynsetPair = new HashMap<Integer, Set<String>>();
        nouns = new HashSet<String>();

        In synsetreader = new In(synsetFilename);
        In hyponymreader = new In(hyponymFilename);

        ArrayList<String> synsetLines = new ArrayList<String>();
        ArrayList<String> hyponymLines = new ArrayList<String>();

        while (synsetreader.hasNextLine()) {
            synsetLines.add(synsetreader.readLine());
        }

        for (String line: synsetLines) {
            String[] info = line.split(",");
            String [] wordsArray = info[1].split(" ");
            Set<String> wordsSet = new HashSet<String>();
            for (String elem: wordsArray) {
                wordsSet.add(elem);
                nouns.add(elem);
            }

            idSynsetPair.put(Integer.parseInt(info[0]), wordsSet);
        }

        hyponyms = new Digraph(nouns.size());

        while (hyponymreader.hasNextLine()) {
            hyponymLines.add(hyponymreader.readLine());
        }

        for (String line: hyponymLines) {
            String[] info = line.split(",");
            for (int i = 1; i < info.length; i++) {
                hyponyms.addEdge(Integer.parseInt(info[0]), Integer.parseInt(info[i]));
            }
        }

    }

    public Set<String> hyponyms(String word) {
        Set<Integer> argument = new HashSet<Integer>();
        for (Entry<Integer, Set<String>> entry : idSynsetPair.entrySet()) {
            for (String item: entry.getValue()) {
                if (item.equals(word)) {
                    argument.add(entry.getKey());
                }
            }
        }
        Set<Integer> ids = GraphHelper.descendants(hyponyms, argument);
        Set<String> result = new HashSet<String>();
        for (Integer id: ids) {
            result.addAll(idSynsetPair.get(id));
        }

        return result;
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    public Set<String> nouns() {
        return nouns;
    }
}
