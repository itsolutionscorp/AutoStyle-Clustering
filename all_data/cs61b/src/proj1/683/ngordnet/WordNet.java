package ngordnet;
import edu.princeton.cs.algs4.Digraph; 
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
// style checker: python /Users/jug/work/61b/course-materials/lib/style61b.py *.java
//collocation = two words frequently used as one

public class WordNet {
    private HashMap<String, ArrayList<Integer>> nounsList = 
                        new HashMap<String, ArrayList<Integer>>();
    //List of nouns <noun, List<synsetIds>>
    private HashMap<Integer, ArrayList<String>> synset = new HashMap<Integer, ArrayList<String>>();
    // List of synsets <synsetId, List<nouns>>
    private Digraph graph;


    public WordNet(String synsets, String hyponyms) {
        In synsetIn = new In(synsets);
        In hypoIn = new In(hyponyms);
        int size = 0;
        String currentLine;

        while (synsetIn.hasNextLine()) {
            size++;
            currentLine = synsetIn.readLine();
            String[] args = currentLine.split(",");
            int id = Integer.parseInt(args[0]);
            String[] synsetList = args[1].split(" ");

            for (String noun : synsetList) {
                ArrayList<Integer> b = nounsList.get(noun); 
                if (b == null) {
                    b = new ArrayList<Integer>();
                    b.add(id);
                    nounsList.put(noun, b);
                } else {
                    b.add(id);
                }
                ArrayList<String> s = synset.get(id);
                if (s == null) {
                    s = new ArrayList<String>();
                    s.add(noun);
                    synset.put(id, s);
                } else {
                    s.add(noun);
                }
            } 
        }

        graph = new Digraph(size);
        while (hypoIn.hasNextLine()) {
            String currentLine2 = hypoIn.readLine();
            String[] numbers = currentLine2.split(",");
            int a = Integer.parseInt(numbers[0]);
            for (int i = 0; i < numbers.length; i++) {
                graph.addEdge(a, Integer.parseInt(numbers[i]));
            }
        }
    }

    public boolean isNoun(String noun) {
        return this.nounsList.containsKey(noun);
    }

    public Set<String> nouns() {
        Set<String> allNouns = nounsList.keySet();
        return allNouns;
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> allHypos = new HashSet<String>();
        if (!this.isNoun(word)) {
            return null;
        } else {
            Set<Integer> synsetIDs = new HashSet<>(nounsList.get(word));
            Set<Integer> hypos = GraphHelper.descendants(graph, synsetIDs); 
            for (Integer hyps : hypos) {
                ArrayList<String> listyoo = synset.get(hyps); 
                for (String yoo : listyoo) {
                    allHypos.add(yoo);
                }
            }
        }
        return allHypos;
    }
}

