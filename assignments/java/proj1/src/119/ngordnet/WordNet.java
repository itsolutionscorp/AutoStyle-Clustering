package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    private List<Synset> synsets;
    private Digraph hyponyms;

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hypernymFilename) {
        synsets = new ArrayList<Synset>();
        readSynset(synsetFilename);
        hyponyms = new Digraph(synsets.size());
        createHyponym(hypernymFilename);
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Synset s : synsets) {
            if (s.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> all = new HashSet<String>();
        for (Synset s : synsets) {
            all.addAll(s.getSet());
        }
        return all;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> holder = new HashSet<String>();
        holder.add(word);
        for (Synset s : synsets) {
            if (s.contains(word)) {
                holder.addAll(s.getSet());
                holder.addAll(s.getHypo());
            }
        }
        return holder;
    }

    private void readSynset(String synsetFilename) {
        In set = new In(synsetFilename);
        while (set.hasNextLine()) {
            String[] line = (set.readLine()).split(",");
            String[] synonyms = (line[1].split(" "));
            String define = line[2];
            int id = Integer.parseInt(line[0]);
            if (synonyms.length > 0) {
                Synset syn = new Synset(id, synonyms, define);
                synsets.add(id, syn);
            }
        }
    }

    private void createHyponym(String hypernymFilename) {
        In set = new In(hypernymFilename);
        while (set.hasNextLine()) {
            String[] line = (set.readLine()).split(",");
            int hyper = Integer.parseInt(line[0]);
            Synset point = synsets.get(hyper);
            for (int i = 1; i < line.length; i = i + 1) {
                int hypo = Integer.parseInt(line[i]);
                point.addHypo(synsets.get(hypo));
                hyponyms.addEdge(hyper, hypo);
            }
        }
    }

}
