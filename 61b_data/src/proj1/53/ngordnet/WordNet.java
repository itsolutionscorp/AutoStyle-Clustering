package ngordnet;

import java.util.Scanner;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.io.File;
import java.io.IOException;

import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private Map<Integer, HashSet<String>> table;
    private Map<String, HashSet<Integer>> inverseTable;
    private Digraph graph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        // Build the table:
        //      {   0: {action},
        //          1: {change}, ...
        //      }
        table = new HashMap<Integer, HashSet<String>>();

        // inverseTable:
        //      {
        //          action: {0},
        //          change: {1}, ...
        //      }
        inverseTable = new HashMap<String, HashSet<Integer>>();

        // Parse CSV
        Scanner sc;
        try {
            sc = new Scanner(new File(synsetFilename));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        sc.useDelimiter(System.getProperty("line.separator"));
        while (sc.hasNext()) {
            String[] line = sc.next().split(",");
            int id = Integer.parseInt(line[0]);
            String words = line[1];

            HashSet<String> synonym = new HashSet<String>();
            String[] tmp = words.split(" ");
            for (String s : tmp) {
                synonym.add(s);
            }
            table.put(id, synonym);

            for (String s : tmp) {
                HashSet<Integer> ids;
                if (inverseTable.containsKey(s)) {
                    ids = inverseTable.get(s);
                } else {
                    ids = new HashSet<Integer>();
                }
                ids.add(id);
                inverseTable.put(s, ids);
            }
        }

        // Build the graph
        graph = new Digraph(table.size());
        try {
            sc = new Scanner(new File(hyponymFilename));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        sc.useDelimiter(System.getProperty("line.separator"));
        while (sc.hasNext()) {
            String[] line = sc.next().split(",");
            int synsetId = Integer.parseInt(line[0]);
            for (int i = 1; i < line.length; i++) {
                int hyponymId = Integer.parseInt(line[i]);
                graph.addEdge(synsetId, hyponymId);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (inverseTable.containsKey(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return inverseTable.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (inverseTable.containsKey(word)) {
            Set<Integer> hyponymIds;
            hyponymIds = GraphHelper.descendants(graph, inverseTable.get(word));

            Set<String> hyponymWords = new HashSet<String>();
            for (Integer id : hyponymIds) {
                for (String s : table.get(id)) {
                    hyponymWords.add(s);
                }
            }
            return hyponymWords;
        }
        return new HashSet<String>();
    }
}
