package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
public class WordNet {

    private Digraph dg;
    private Map<Integer, String> synsets = new HashMap<Integer, String>();
    private Set<String> allNouns = new HashSet<String>();
    private Set<Integer> allverts;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        In hyp = new In(hyponymFilename);
        Set<String> synlines = new HashSet<String>();
        Set<String> hyplines = new HashSet<String>();

        while (syn.hasNextLine()) {
            String line = syn.readLine();
            synlines.add(line);
            String[] synvals = line.split(",");
            String[] wordstotal = synvals[1].split(" ");

            for (int i = 0; i < wordstotal.length; i = i + 1) {
                synsets.put(Integer.parseInt(synvals[0]), synvals[1]);
                allNouns.add(wordstotal[i]);
            }
        }
        dg = new Digraph(synlines.size());

        while (hyp.hasNextLine()) {
            String line = hyp.readLine();
            hyplines.add(line);
            String[] hypvals = line.split(",");
            for (int i = 1; i < hypvals.length; i = i + 1) {
                dg.addEdge(Integer.parseInt(hypvals[0]), Integer.parseInt(hypvals[i]));
            }
        }
        allverts = synsets.keySet();
    }

    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }

    public Set<String> nouns() {
        return allNouns;
    }

    public Set<String> hyponyms(String word) {
        List<String> synos0 = new ArrayList<String>();
        List<String> hypos0 = new ArrayList<String>();
        List<Integer> verticies0 = new ArrayList<Integer>();
        String [] words;
        for (Integer id : allverts) {
            words = synsets.get(id).split(" ");
            for (String word1 : words) {
                if (word.equals(word1)) {
                    synos0.add(word);
                    verticies0.add(id);
                }
            }
        }
        Set<String> synos = new HashSet<String>(synos0);
        Set<Integer> verticies = new HashSet<Integer>(verticies0);
        Set<Integer> hypoverts = new HashSet<Integer>(GraphHelper.descendants(dg, verticies));

        for (Integer id : hypoverts) {
            words = synsets.get(id).split(" ");
            for (String word2 : words) {
                hypos0.add(word2);
            }
        }
        Set<String> hypos = new HashSet<String>();
        hypos.addAll(hypos0);
        hypos.addAll(synos);
        return hypos;
    }
}
