package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordNet {
    private Digraph dgraph;
    private Map<String, Set<Integer>> wordsToId;
    private Map<Integer, String[]> idToWords;

    public WordNet(String synsetFilename, String hyponymFilename) {
        wordsToId = new HashMap<String, Set<Integer>>();
        idToWords = new HashMap<Integer, String[]>();
        int size = 0;
        //add all sysets into nouns map
        BufferedReader br = null;
        try {
            String line;
            String[] parsedLine = new String[3];
            int id;

            br = new BufferedReader(new FileReader(synsetFilename));
            while ((line = br.readLine()) != null) {
                parsedLine = line.split(",");
                // [0] 'id', [1] words, [2] def
                id = Integer.parseInt(parsedLine[0]);
                String[] words = parsedLine[1].split(" ");
                idToWords.put(id, words);
                size += 1;
                for (String w : words) {
                    if (wordsToId.get(w) == null) {
                        Set<Integer> idSet = new TreeSet<Integer>();
                        idSet.add(id);
                        wordsToId.put(w, idSet);
                    } else {
                        wordsToId.get(w).add(id);
                    }
                }
            }
            dgraph = new Digraph(size);
            //read from hyponym file
            br = new BufferedReader(new FileReader(hyponymFilename));
            while ((line = br.readLine()) != null) {
                parsedLine = line.split(",");
                // [0] 'id', [1:] hyponym ids 
                id = Integer.parseInt(parsedLine[0]);
                for (int i = 1; i < parsedLine.length; i++) {
                    dgraph.addEdge(id, Integer.parseInt(parsedLine[i]));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isNoun(String noun) {
        return (wordsToId.containsKey(noun));
    }

    public Set<String> nouns() {
        return (wordsToId.keySet());
    }

    public Set<String> hyponyms(String word) {
        Set<String> hyponymSet = new TreeSet<String>(); //to be returned
        if (wordsToId.get(word) == null) {
            System.out.println("Word is not available");
        } else {
            Set<Integer> intSet = GraphHelper.descendants(dgraph, wordsToId.get(word));
            for (int coorId: intSet) {
                for (String coorStr: idToWords.get(coorId)) {
                    hyponymSet.add(coorStr);
                }
            }
        }
        return hyponymSet;
    }
}
