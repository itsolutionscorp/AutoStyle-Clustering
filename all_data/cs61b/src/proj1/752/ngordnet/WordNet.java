package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph; 
import java.util.Set;
import java.util.TreeSet; 
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class WordNet {
    private Map<Integer, String> synsetMap;
    private Map<String, Set<Integer>> revSynsetMap;
    private Digraph hyponymDigraph;
    public WordNet(String synsetFile, String hyponymFile) {
        In synsetInput = new In(new File(synsetFile));
        constructSynsets(synsetInput);
        In hyponymInput = new In(new File(hyponymFile));
        In hyponymInput2 = new In(new File(hyponymFile));
        constructHyponyms(hyponymInput, hyponymInput2);
    }

    private void constructSynsets(In synsetInput) {
        String line = "";
        String[] synset = new String[2];
        synsetMap = new TreeMap<Integer, String>();
        revSynsetMap = new TreeMap<String, Set<Integer>>();
        while (synsetInput.hasNextLine()) {
            line = synsetInput.readLine();
            synset = line.split(",");
            Integer id = Integer.parseInt(synset[0]);
            synsetMap.put(id, synset[1]);
            String[] tokens = synset[1].split(" ");
            for (int i = 0; i < tokens.length; i++) {
                if (!revSynsetMap.containsKey(tokens[i])) {
                    TreeSet<Integer> idSet = new TreeSet<Integer>();
                    idSet.add(id);
                    revSynsetMap.put(tokens[i], idSet);
                    // System.out.println("adding new key to revSynsetMap");
                } else {
                    Set<Integer> idSet = revSynsetMap.get(tokens[i]);
                    idSet.add(id); //changes revSynsetMap.get(tokens[i]) which is the original idSet
                    // System.out.println("adding " + id + "to idSet of " + tokens[i]);
                }   

            }
        }
    }

    private void constructHyponyms(In hyponymInput, In hyponymInput2) {
        String line; //error otherwise?
        Set<Integer> verticeSet = new TreeSet<Integer>(); //need generic???
        int verticeCount = 0;
        //count vertices
        while (hyponymInput.hasNextLine()) {
            line = hyponymInput.readLine();
            String[] hyponym = line.split(",");
            for (int i = 0; i < hyponym.length; i++) {
                Integer id = Integer.parseInt(hyponym[i]);
                if (!verticeSet.contains(id)) {
                    verticeSet.add(id);
                }
            }
        }

        verticeCount = verticeSet.size();

        hyponymDigraph = new Digraph(verticeCount);

        while (hyponymInput2.hasNextLine()) {
            line = hyponymInput2.readLine();
            String[] hyponym = line.split(",");
            Integer v = Integer.parseInt(hyponym[0]);
            for (int i = 1; i < hyponym.length; i++) {
                hyponymDigraph.addEdge(v, Integer.parseInt(hyponym[i]));                
            }
        } 
    }

    public boolean isNoun(String noun) {
        if (revSynsetMap.containsKey(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return revSynsetMap.keySet();
    }

    public Set<String> hyponyms(String word) {
        if (revSynsetMap == null) {
            System.out.println("revSynsetMap is null");
        }

        Set<Integer> descendants = GraphHelper.descendants(hyponymDigraph, revSynsetMap.get(word));
        Set<String> result = new TreeSet<String>();
        for (int i : descendants) {
            String[] split = synsetMap.get(i).split(" ");
            for (int j = 0; j < split.length; j++) {
                result.add(split[j]);
            }
        }
        return result;
    }
}
