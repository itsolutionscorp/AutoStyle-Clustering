package ngordnet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.TreeSet;

public class WordNet {

    private Digraph graph;
    private ArrayList<Synset> synsets;
    private Set<Integer> roots;

    public WordNet(String synsetFilename, String hyponymFilename) {
        this.synsets = new ArrayList<Synset>();
        this.roots = new TreeSet<Integer>();

        In synInput = new In(synsetFilename);

        while (synInput.hasNextLine()) {
            String line = synInput.readLine();
            int firstDivider = commaIndex(line);
            int id = Integer.parseInt(line.substring(0, firstDivider));

            line = line.substring(firstDivider + 1);
            int nextDivider = commaIndex(line);
            String[] words = line.substring(0, nextDivider).split(" ");

            Set<String> wordSet = new TreeSet<String>();
            for (String word : words) {
                wordSet.add(word);
            }
            this.synsets.add(new Synset(id, wordSet));
        }

        this.graph = new Digraph(this.synsets.size());

        In hypInput = new In(hyponymFilename);

        while (hypInput.hasNextLine()) {
            String line = hypInput.readLine();
            int firstDivider = commaIndex(line);
            int startId = Integer.parseInt(line.substring(0, firstDivider));

            while (firstDivider != -1) {
                line = line.substring(firstDivider + 1);
                int nextDivider = commaIndex(line);
                int endId;
                if (nextDivider == -1) {
                    endId = Integer.parseInt(line);
                } else {
                    endId = Integer.parseInt(line.substring(0, nextDivider));
                }
                firstDivider = nextDivider;
                this.graph.addEdge(startId, endId);
                this.roots.add(startId);
                if (this.roots.contains(endId)) {
                    this.roots.remove(endId);
                }
            }
        }
    }

    public Set<String> hyponyms(String word) {
        Set<String> result = new TreeSet<String>();
        Set<Integer> hypernymIds = new TreeSet<Integer>();
        for (Synset synset : this.synsets) {
            if (synset.getWordSet().contains(word)) {
                hypernymIds.add(synset.getId());
            }
        }

        Set<Integer> descendants = GraphHelper.descendants(this.graph, hypernymIds);
        for (Integer i : descendants) {
            result.addAll(this.synsets.get(i).getWordSet());
        }
        return result;
    }

    public boolean isNoun(String noun) {
        for (Synset synset : this.synsets) {
            if (synset.getWordSet().contains(noun)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        Set<String> result = new TreeSet<String>();
        for (Synset synset : this.synsets) {
            result.addAll(synset.getWordSet());
        }
        return result;
    }

    /* ========== HELPER METHODS ======== */

    private int commaIndex(String s) {
        return s.indexOf(",");
    }
}
