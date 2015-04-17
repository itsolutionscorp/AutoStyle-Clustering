package ngordnet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class WordNet {
    private TreeMap<Integer, TreeSet<String>> map;
    private TreeSet<String> set;
    private Digraph graph;

    public WordNet(String synsetStr, String hyponymStr) {
        String line = null;
        String synID = null;
        String words = null;
        map = new TreeMap<Integer, TreeSet<String>>();
        set = new TreeSet<String>();
        TreeSet<String> setForEachSynset; 
        int edgeCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(synsetStr)));
            while ((line = reader.readLine()) != null) {

                StringTokenizer strtokLine = new StringTokenizer(line, ",");
                synID = strtokLine.nextToken();
                words = strtokLine.nextToken();
                setForEachSynset = new TreeSet<String>();
                StringTokenizer strtokWords = new StringTokenizer(words, " ");
                while (strtokWords.hasMoreTokens()) {
                    String wordToAdd = strtokWords.nextToken();
                    setForEachSynset.add(wordToAdd);
                    set.add(wordToAdd);
                }
                map.put(Integer.parseInt(synID), setForEachSynset);
                edgeCount += 1;
            }
            reader.close();

            graph = new Digraph(edgeCount);


            reader = new BufferedReader(new FileReader(new File(hyponymStr)));
            while ((line = reader.readLine()) != null) {
                StringTokenizer strtokLine = new StringTokenizer(line, ",");
                int parent = Integer.parseInt(strtokLine.nextToken());
                while (strtokLine.hasMoreTokens()) {
                    int child = Integer.parseInt(strtokLine.nextToken());
                    graph.addEdge(parent, child);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found error");
        } catch (IOException e) {
            System.out.println("IOException");
        }

    }

    public boolean isNoun(String word) {
        if (word == null) {
            throw new IllegalArgumentException();
        }
        return set.contains(word);
    }

    public Set<String> nouns() {
        return set;
    }

    public Set<String> hyponyms(String targetStr) {
        if (targetStr == null) {
            throw new IllegalArgumentException();
        }
        TreeSet<Integer> hyponymsIDs;
        TreeSet<String> hyponyms = new TreeSet<String>();
        TreeSet<Integer> synsetIDs = new TreeSet<Integer>();

        for (Integer id: map.keySet()) {
            for (String word: map.get(id)) {
                if (word.equals(targetStr)) {
                    synsetIDs.add(id);
                }
            }
        }

        if (synsetIDs.size() != 0) {
        // found the word
            hyponymsIDs = (TreeSet<Integer>) GraphHelper.descendants(graph, synsetIDs);
            for (Integer id: hyponymsIDs) {
                for (String wordFound: map.get(id)) {
                    hyponyms.add(wordFound);
                }
            }
        }
        return hyponyms;
    }
}


