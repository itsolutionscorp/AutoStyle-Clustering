package ngordnet;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private HashMap<String, Integer[]> nounMaster;
    private Digraph graph;
    private Map<Integer, String[]> nodes;

    public WordNet(String synset, String hyponyms) {

        In synsetFile = new In(synset);
        In hyponymFile = new In(hyponyms);

        String[] allLines = synsetFile.readAllLines();
        synsetFile = new In(synset);
        int numLines = allLines.length;

        nounMaster = new HashMap<String, Integer[]>(numLines);
        graph = new Digraph(numLines); 
        nodes = new HashMap<Integer, String[]>(numLines); 

        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine();
            String[] temp = line.split(",");
            Integer node = Integer.parseInt(temp[0]);
            Integer[] nodeArr = {node};
            String[] nouns = temp[1].split(" ");

            nodes.put(node, nouns);
            for (String noun : nouns) {
                if (nounMaster.containsKey(noun)) {
                    int arraySize = nounMaster.get(noun).length + 1;
                    Integer[] newNode = new Integer[arraySize];
                    newNode[0] = node;
                    int i = 1;
                    for (int x : nounMaster.get((noun))) {
                        newNode[i] = x;
                        i++;
                    }
                    nounMaster.put(noun, newNode);
                } else {
                    nounMaster.put(noun, nodeArr);
                }
            }
        }

        while (hyponymFile.hasNextLine()) {
            String hypLine = hyponymFile.readLine();
            String[] hypTemp = hypLine.split(",");
            int vert1 = Integer.parseInt(hypTemp[0]);

            for (int x = 1; x < hypTemp.length; x++) {
                int tempVert = Integer.parseInt(hypTemp[x]);
                graph.addEdge(vert1, tempVert);
            }
        }
    }

    public boolean isNoun(String noun) {
        return nounMaster.containsKey(noun);
    }

    public Set<String> nouns() {
        return nounMaster.keySet();
    }

    public Set<String> hyponyms(String noun) {
        Integer[] id = nounMaster.get(noun);
        if (id != null) {
            Set<Integer> synsetID = new HashSet<Integer>(id.length);
            for (int x : id) {
                synsetID.add(x);    
            }

            Set<Integer> hyponymID = GraphHelper.descendants(graph, synsetID);
            int sizeOfSet = hyponymID.size();
            HashSet<String> allHyps = new HashSet<String>(sizeOfSet);

            for (int x : hyponymID) {
                for (String y : nodes.get(x)) {
                    allHyps.add(y);
                }
            }
            return allHyps;
        } else {
            HashSet<String> ans = new HashSet<String>(0);
            return ans;
        }   
    }
}
