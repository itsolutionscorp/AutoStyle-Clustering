package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import edu.princeton.cs.introcs.In;


public class WordNet {
    private HashMap<Integer, ArrayList<String>> id = new HashMap<Integer, ArrayList<String>>();
    // Inspired by the great Tony Situ
    private HashMap<String, ArrayList<Integer>> words = new HashMap<String, ArrayList<Integer>>();
    private int maxVertex = 0;
    private Digraph graph;

    /** Creates a WordNet using files form ArrayList<String>FILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synput = new In(synsetFilename);
        In synput2 = new In(synsetFilename);
        In hyput = new In(hyponymFilename);

        while (synput.hasNextLine()) {
            String line = synput.readLine();
            String[] rawTokens = line.split(",");   
            int synId = Integer.parseInt(rawTokens[0]);
            if (synId > maxVertex) {
                maxVertex = synId;
            }
            String[] midtokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, midtokens, 0, rawTokens.length - 1);
            
            String[] tokens = midtokens[0].split(" ");

            ArrayList<String> temp = new ArrayList<String>();
            for (String syn : tokens) {
                temp.add(syn);
            }
            id.put(synId, temp);


            ArrayList<Integer> temp2 = new ArrayList<Integer>();
            temp2.add(synId);
            for (String syn : tokens) {
                if (words.containsKey(syn)) {
                    words.get(syn).add(synId);
                } else {
                    words.put(syn, (ArrayList<Integer>) temp2.clone());
                }

            }
        }

        graph = new Digraph(maxVertex + 1);


        while (hyput.hasNextLine()) {
            String line = hyput.readLine();
            String[] rawTokens = line.split(",");
            int vertex = Integer.parseInt(rawTokens[0]);
            String[] tokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
            for (String x : tokens) {
                graph.addEdge(vertex, Integer.parseInt(x));
            }
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (words.containsKey(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns.  Thanks stackoverflow */
    public Set<String> nouns() {
        return words.keySet();
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        ArrayList<Integer> ids; //ids associated with the word.
        if (this.words.containsKey(word)) {
            ids = this.words.get(word);
        } else {
            return null;
        }
        

        Set<Integer> helper = new TreeSet<Integer>();
        for (int temp : ids) {
            helper.add(temp);
        }
        Set<Integer> temp = GraphHelper.descendants(graph, helper); //hyponym ids

        Set<String> ret = new HashSet();
        for (int x : temp) { //for each hyponym id
            for (String ass : id.get(x)) { //add nouns in that id to ret
                ret.add(ass);
            }
        }

        return ret;

    }

}
