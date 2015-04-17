package ngordnet;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private List<Synset> synsets;
    private Set<String> words;
    private Digraph digraph;

    private static final int BUFF = 1;

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hypernymFilename) {
        synsets = new ArrayList<Synset>();
        // Construct the synsets
        In file = new In(synsetFilename);
        int id = 0; // Keep track of the id
        while (file.hasNextLine()) {
            String line = file.readLine();
            String[] inputs = line.split(",");
            id = Integer.parseInt(inputs[0]); // Risking indexOutofBounds
            String[] inputWords = inputs[1].split(" ");
            Synset synset = new Synset(id, inputWords);
            synsets.add(synset);
        }

        // Construct Digraph
        digraph = new Digraph(id + BUFF);
        file = new In(hypernymFilename);
        while (file.hasNextLine()) {
            String line = file.readLine();
            String[] inputs = line.split(",");
            int hyperId = Integer.parseInt(inputs[0]);
            for (int i = 1; i < inputs.length; i++) {
                digraph.addEdge(hyperId, Integer.parseInt(inputs[i]));
            }
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (words != null) {
            return words.contains(noun);
        }
        formWordsSet();
        return words.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        if (words != null) {
            return words;
        }
        formWordsSet();
        return words;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> result = new HashSet<String>();
        result.add(word);
        Set<Integer> hyponymIDs = GraphHelper.descendants(digraph, hypernymIDsOf(word));
        for (Integer i : hyponymIDs) {
            for (String s : synsets.get(i)) {
                result.add(s);
            }
        }
        return result;
    }

    private void formWordsSet() {
        words = new HashSet<String>();
        for (Synset s : synsets) {
            for (String word : s) {
                words.add(word);
            }
        }
    }

    private Set<Integer> hypernymIDsOf(String noun) {
        Set<Integer> result = new HashSet<Integer>();
        for (Synset s : synsets) {
            if (s.contains(noun)) {
                result.add(s.getId());
            }
        }
        return result;
    }

    private class Synset extends AbstractList<String> {
        private int _id;
        private String[] _words;

        public Synset(int id, String[] inwords) {
            _id = id;
            _words = inwords;
        }

        public int getId() {
            return _id;
        }

        // public String getDescription() {
        // return description;
        // }

        // public Set<Synset> getHyponyms() {
        // return hyponyms;
        // }

        @Override
        public String get(int index) {
            // TODO Auto-generated method stub
            return _words[index];
        }

        @Override
        public int size() {
            // TODO Auto-generated method stub
            return _words.length;
        }
    }
}
