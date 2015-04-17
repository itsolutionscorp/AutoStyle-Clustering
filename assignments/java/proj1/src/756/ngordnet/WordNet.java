package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private HashMap<String, Set<Integer>> nounID = new HashMap<String, Set<Integer>>();
    private HashMap<Integer, String[]> idNoun = new HashMap<Integer, String[]>();
    private Digraph words;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetsScanner = new In(synsetFilename);
        In hyponymsScanner = new In(hyponymFilename);
        int count = 0;
        while (synsetsScanner.hasNextLine()) {
            String line = synsetsScanner.readLine();
            String[] synsets = line.split(",");
            int idN = Integer.parseInt(synsets[0]);
            String[] nouns = synsets[1].split(" ");

            idNoun.put(idN, nouns);
            count++;

            for (String n : nouns) {
                Set<Integer> id = nounID.get(n);
                if (id == null) {
                    Set<Integer> id1 = new TreeSet<Integer>();
                    id1.add(idN);
                    nounID.put(n, id1);
                } else {
                    id.add(idN);
                }
            }
        }

        words = new Digraph(count);
        while (hyponymsScanner.hasNextLine()) {
            String line1 = hyponymsScanner.readLine();
            String[] hyponyms = line1.split(",");
            int startVertex = Integer.parseInt(hyponyms[0]);
            for (int i = 1; i < hyponyms.length; i += 1) {
                words.addEdge(startVertex, Integer.parseInt(hyponyms[i]));
            }
        }
    }

    
    public Set<String> hyponyms(String word) {
        Set<String> hypo = new TreeSet<String>(); 
        Set<Integer> id = nounID.get(word);
        Set<Integer> hypoIDS = GraphHelper.descendants(words, id);
        for (int hypoID : hypoIDS) {
            String[] hypos = idNoun.get(hypoID);
            for (String hyp: hypos) {
                hypo.add(hyp);
            }
        }
        return hypo;
    }

    public boolean isNoun(String noun) {
        return nounID.containsKey(noun);
    }

    public Set<String> nouns() {
        if (nounID == null) {
            return null;
        }
        return nounID.keySet();
    }
}
