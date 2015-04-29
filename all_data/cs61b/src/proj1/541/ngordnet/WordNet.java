package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class WordNet {
    private Digraph graph;
    private List<ArrayList<String>> synsets = new ArrayList<ArrayList<String>>();
    public WordNet(String synsetFilename, String hyponymFilename) {
        String splitBy = ",";
        String line;
        int numVertices = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(synsetFilename))) {
            while ((line = br.readLine()) != null) {
                String[] b = line.split(splitBy);
                String[] wordArray = b[1].split(" ");
                ArrayList<String> wordList = new ArrayList<String>();
                for (String word : wordArray) {
                    wordList.add(word);
                }
                synsets.add(wordList);
                numVertices += 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        try (BufferedReader br2 = new BufferedReader(new FileReader(hyponymFilename))) {
            graph = new Digraph(numVertices);
            while ((line = br2.readLine()) != null) {
                String[] b = line.split(splitBy);
                for (int i = 1; i < b.length; i++) {
                    graph.addEdge(Integer.parseInt(b[0]), Integer.parseInt(b[i]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Set<String> hyponyms(String word) {
        int synsetsSize = synsets.size();
        Set<String> hyponyms = new TreeSet<String>();
        for (int i = 0; i < synsetsSize; i++) {
            List<String> cur = synsets.get(i);
            if (cur.contains(word)) {
                Set<Integer> start = new TreeSet<Integer>();
                start.add(i);
                Set<Integer> reachable = GraphHelper.descendants(graph, start);
                for (int descendant : reachable) {
                    ArrayList<String> descendStringList = synsets.get(descendant);
                    for (int j = 0; j < descendStringList.size(); j++) {
                        if (!hyponyms.contains(descendStringList.get(j))) {
                            hyponyms.add(descendStringList.get(j));
                        }
                    }
                }
            }
        }
        return hyponyms;
    }

    public boolean isNoun(String noun) {
        int synsetsSize = synsets.size();
        for (int i = 0; i < synsetsSize; i++) {
            ArrayList<String> cur = synsets.get(i);
            if (cur.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        int synsetsSize = synsets.size();
        Set<String> nouns = new TreeSet<String>();
        for (int i = 0; i < synsetsSize; i++) {
            ArrayList<String> curList = synsets.get(i);
            for (int j = 0; j < curList.size(); j++) {
                if (!nouns.contains(curList.get(j))) {
                    nouns.add(curList.get(j));
                }
            }
        }
        return nouns;
    }
}
