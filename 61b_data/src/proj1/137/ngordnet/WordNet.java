package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private BiDirectionalMap bdm;
    private Digraph g;
    private int numNodes;
    private int capacity;

    public WordNet(String synsetPath, String hyponymPath) {
        In synsetIn = new In(synsetPath);
        this.bdm = new BiDirectionalMap(this.capacity);
        while (synsetIn.hasNextLine()) {
            String l = synsetIn.readLine();
            String[] strArray = l.split(",");
            int id = Integer.valueOf(strArray[0]);
            String synset = strArray[1];
            String definition = strArray[2];
            numNodes += 1;
            bdm.put(id, synset, definition);
        }
        synsetIn.close();
        
        In hyponymIn = new In(hyponymPath);
        this.g = new Digraph(numNodes);
        while (hyponymIn.hasNextLine()) {
            String l = hyponymIn.readLine();
            String[] strArray = l.split(",");
            int parent = (int) Integer.valueOf(strArray[0]);
            int child = 0;
            for (int i = 1; i < strArray.length; i++) {
                child = (int) Integer.valueOf(strArray[i]);
                this.g.addEdge(parent, child);
            }
        }
        hyponymIn.close();
    }

    public boolean isNoun(String noun) {
        return this.bdm.containsNoun(noun);
    }

    public Set<String> nouns() {
        return this.bdm.nounSet();
    }

    public Set<String> hyponyms(String word) {
        Set<String> toReturn = new TreeSet<String>();
        // Synonyms
        toReturn.addAll(this.getSyns(word));
        // Hyponyms
        Set<Integer> ids = this.bdm.getID(word);
        Set<Integer> children = GraphHelper.descendants(g, ids);
        for (int i : children) {
            toReturn.addAll(this.bdm.getSynset(i));
        }
        return toReturn;
    }

    private Set<String> getSyns(String word) {
        Set<Integer> ids = this.bdm.getID(word);
        Set<String> toReturn = new TreeSet<String>();
        for (int i : ids) {
            toReturn.addAll(this.bdm.getSynset(i));
        }
        return toReturn;
    }
}
