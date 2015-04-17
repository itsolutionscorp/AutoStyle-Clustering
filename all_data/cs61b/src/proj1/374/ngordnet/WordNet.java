
package ngordnet;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class WordNet {
    private In synsetFile;
    private In hyponymFile;
    private HashMap<Integer, String> nounList = new HashMap<Integer, String>();
    private Set<String> nounSet = new HashSet<String>(100);
    private Digraph g;

    

    public WordNet(String synsetFilename, String hyponymFilename) {

        //In file = new In(filename);
        synsetFile = new In(synsetFilename);
        hyponymFile = new In(hyponymFilename);

        while (!synsetFile.isEmpty()) {
            String line = synsetFile.readLine();
            String[] splitline = line.split(",");
            Integer id = Integer.parseInt(splitline[0]);
            nounList.put(id, splitline[1]);

            String[] splitnoun = splitline[1].split(" ");

            for (int i = 0; i < splitnoun.length; i++) {
                nounSet.add(splitnoun[i]);
            }
            
        }

        g = new Digraph(nounList.size());

        while (!hyponymFile.isEmpty()) {
            String line = hyponymFile.readLine();
            String[] splitline = line.split(",");
            Integer id = Integer.parseInt(splitline[0]);
            Integer[] hyponyms = new Integer[splitline.length - 1];
            for (int a = 0; a < splitline.length - 1; a++) {
                hyponyms[a] = Integer.parseInt(splitline[a + 1]);
            }
            for (int b = 0; b < hyponyms.length; b++) {
                g.addEdge(id, hyponyms[b]);
            }

        }   


    }

    public boolean isNoun(String noun) {
        if (nounSet.contains(noun)) {
            return true;
        }
        return false;
    }
    public Set<String> nouns() {
        
        return nounSet;

    }
    public Set<String> hyponyms(String word) {
        //first find synsetIDs
        Set<Integer> synsetId = new TreeSet<Integer>();
        for (Map.Entry<Integer, String> entry : nounList.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            String[] splitvalue = value.split(" ");
            for (int i = 0; i < splitvalue.length; i++) {
                if (splitvalue[i].equals(word)) {
                    synsetId.add(key);
                }
            }
        }

        Set<Integer> descendantsSet = new TreeSet<Integer>();

        descendantsSet = GraphHelper.descendants(g, synsetId);

        Integer[] descendantsArray = descendantsSet.toArray(new Integer[descendantsSet.size()]);
        Set<String> hyponymsSet = new TreeSet<String>();
        for (int a = 0; a < descendantsArray.length; a++) {
            String value = nounList.get(descendantsArray[a]);

            String[] splitvalue = value.split(" ");
            for (int i = 0; i < splitvalue.length; i++) {
                hyponymsSet.add(splitvalue[i]);
            }
        }
        return hyponymsSet;
    }


}
