package ngordnet;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashMap<Integer, ArrayList<String>> synsets;
    private HashMap<String, ArrayList<Integer>> synsetsInverted;
    private Digraph hyponymsGraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
    /* Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
        In hypo = new In(hyponymFilename);
        In syn = new In(synsetFilename);

        synsets = new HashMap<Integer, ArrayList<String>>();
        synsetsInverted = new HashMap<String, ArrayList<Integer>>();
        int numOfVertices = 0;

        while (syn.hasNextLine()) {
            String line = syn.readLine();
            numOfVertices++;
            String[] rawTokens = line.split("\\,");
            int idNum = Integer.parseInt(rawTokens[0]);
            String[] tokens = rawTokens[1].split(" ");

            for (int i = 0; i < tokens.length; i++) {
                ArrayList<String> tokensList = new ArrayList<String>();
                ArrayList<Integer> ids = new ArrayList<Integer>();
                if (synsets.containsKey(idNum)) {
                    tokensList = synsets.get(idNum);
                }
                if (synsetsInverted.containsKey(tokens[i])) {
                    ids = synsetsInverted.get(tokens[i]);
                }
                tokensList.add(tokens[i]);
                ids.add(idNum);
                synsets.put(idNum, tokensList);
                synsetsInverted.put(tokens[i], ids);
            }
        }

        hyponymsGraph = new Digraph(numOfVertices);     

        while (hypo.hasNextLine()) {
            String line = hypo.readLine();
            String[] tokens = line.split("\\,");
            int synsetID = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                int hyponymID = Integer.parseInt(tokens[i]);
                hyponymsGraph.addEdge(synsetID, hyponymID);
            }
        }
    }

    public Set<String> hyponyms(String word) {
    /* Returns the set of all hyponyms of WORD as well as all synonyms of WORD. */
        Set<String> hyponymsSet = new HashSet<String>();
        Set<Integer> vertices = new HashSet<Integer>();
        for (Integer i : synsetsInverted.get(word)) {
            vertices.add(i);
        }
        for (Integer i : GraphHelper.descendants(hyponymsGraph, vertices)) {
            hyponymsSet.addAll(synsets.get(i));
        }
        return hyponymsSet;
    } 

    public boolean isNoun(String noun) {
        return synsetsInverted.containsKey(noun);

    }

    public Set<String> nouns() {
        Set<String> nounCollection = new HashSet<String>();
        for (String key : synsetsInverted.keySet()) {
            nounCollection.add(key);
        }
        return nounCollection;
    }
}
