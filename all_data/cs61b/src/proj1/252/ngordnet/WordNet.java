package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private HashMap<String, TreeSet<Integer>> dataToId;
    private ArrayList<TreeSet<String>> array;
    private Digraph graph;

    // Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
    public WordNet(String synsetFilename, String hyponymFilename) {
        dataToId = new HashMap<String, TreeSet<Integer>>();
        array = new ArrayList<TreeSet<String>>();
        In readDataFile = new In(synsetFilename);
        In readRelationFile = new In(hyponymFilename);
        int line = 0;
        while (!readDataFile.isEmpty()) {
            String s = readDataFile.readLine();
            line += 1;
            String[] tokens = s.split("[,]");
            String[] arrayOfString = tokens[1].split(" ");
            int id = Integer.parseInt(tokens[0]);
            for (int i = 0; i < arrayOfString.length; i++) {
                if (dataToId.get(arrayOfString[i]) == null) {
                    TreeSet<Integer> intList = new TreeSet<Integer>();
                    intList.add(id);
                    dataToId.put(arrayOfString[i], intList);
                } else {
                    dataToId.get(arrayOfString[i]).add(id);
                }
            }
            TreeSet<String> stringList = new TreeSet<String>();
            for (int i = 0; i < arrayOfString.length; i++) {
                stringList.add(arrayOfString[i]);
            }
            array.add(stringList);
        }
        graph = new Digraph(line);
        while (!readRelationFile.isEmpty()) {
            String s1 = readRelationFile.readLine();
            String[] tokens = s1.split("[,]");
            int firstVertex = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                graph.addEdge(firstVertex, Integer.parseInt(tokens[i]));
            }
        }
        readDataFile.close();
        readRelationFile.close();
    }

    // Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
    public boolean isNoun(String noun) {
        return dataToId.get(noun) != null;
    }

    public Set<String> nouns() {
        Set<String> allNouns = new TreeSet<String>();
        allNouns = dataToId.keySet();
        return allNouns;
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponymsString = new TreeSet<String>();
        if (!isNoun(word)) {
            hyponymsString.add("");
            return hyponymsString;
        }
        Set<Integer> allInteger = new TreeSet<Integer>();
        allInteger = dataToId.get(word);
        Set<Integer> allRelativeInteger = new TreeSet<Integer>();
        allRelativeInteger = GraphHelper.descendants(graph, allInteger);
        Iterator<Integer> itor = allRelativeInteger.iterator();
        while (itor.hasNext()) {
            Iterator<String> itorString = array.get(itor.next()).iterator();
            while (itorString.hasNext()) {
                hyponymsString.add(itorString.next());
            }
        }
        return hyponymsString;
    }
}
