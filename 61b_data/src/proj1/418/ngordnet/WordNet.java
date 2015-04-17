package ngordnet;

import edu.princeton.cs.algs4.Digraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WordNet {

    private HashMap<String, HashSet<Integer>> byNouns = new HashMap<String, HashSet<Integer>>();
    private HashMap<Integer, String[]> synsetByID = new HashMap<Integer, String[]>();
    private Digraph digraph;

    public WordNet(String synsetFilename, String hyponymFilename) {

        try {
            FileReader synsetFile = new FileReader(synsetFilename);
            BufferedReader synsetReader = new BufferedReader(synsetFile);
            FileReader hyponymFile = new FileReader(hyponymFilename);
            BufferedReader hyponymReader = new BufferedReader(hyponymFile);

            while (true) {
                String line = synsetReader.readLine();
                if (line == null) {
                    break;
                }
                String[] sepLine = line.split(",");
                Integer id = Integer.parseInt(sepLine[0]);
                String[] ns = sepLine[1].split(" "); // SYNSET (STRING)

                synsetByID.put(id, ns);
                for (String n : ns) {
                    if (byNouns.containsKey(n)) {
                        byNouns.get(n).add(id);

                    } else {
                        HashSet<Integer> idSet = new HashSet<Integer>();
                        idSet.add(id);
                        byNouns.put(n, idSet); // Each noun mapped to ID number
                    }
                }
            }
            // DIGRAPH
            digraph = new Digraph(synsetByID.size());
            while (true) { // FIX
                String line = hyponymReader.readLine();
                if (line == null) {
                    break;
                }

                String[] sepLine = line.split(",");
                int hypernymID = Integer.parseInt(sepLine[0]);

                for (int i = 1; i < sepLine.length; i++) {
                    int hypo = Integer.parseInt(sepLine[i]);
                    digraph.addEdge(hypernymID, hypo);
                }
            }
            synsetReader.close();
            hyponymReader.close();
        } catch (IOException e) {
            System.out.println(e); // TODO
        }
    }

    // Returns a HashSet of the IDs represented by the Noun, else returns null
    private HashSet<Integer> getIDs(String noun) {
        if (byNouns.containsKey(noun)) {
            return byNouns.get(noun);
        } else {
            return null;
        }
    }

    public boolean isNoun(String noun) {
        return byNouns.containsKey(noun);
    }

    public Set<String> nouns() {
        return byNouns.keySet();
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> ids = new HashSet<Integer>();
        ids.addAll(getIDs(word));
        ids.addAll(GraphHelper.descendants(digraph, ids));

        Set<String> hypSet = new HashSet<String>();
        for (int id : ids) {
            String[] synset = synsetByID.get(id);
            for (String syn : synset) {
                hypSet.add(syn);
            }
        }
        return hypSet;
    }

}
