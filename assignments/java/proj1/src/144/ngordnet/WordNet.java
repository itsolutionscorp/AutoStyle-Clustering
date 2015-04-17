package ngordnet;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private Map<Integer, Entry> idToEntry;
    private Map<String, Set<Integer>> nounToID;
    private Set<String> nounEntries;
    private Digraph hypohypernyms;

    public WordNet(java.lang.String synsetFilename, java.lang.String hyponymFilename) {
        this.idToEntry = new HashMap<Integer, Entry>();
        this.nounToID = new HashMap<String, Set<Integer>>();
        this.nounEntries = new HashSet<String>();

        In scanner = new In(synsetFilename);
        while (scanner.hasNextLine()) {
            String line = scanner.readLine();
            String[] lineSplit = line.split(",");
            int id = Integer.parseInt(lineSplit[0]);
            String[] nouns = lineSplit[1].split(" ");
            String definition = lineSplit[2];

            for (int n = 3; n < lineSplit.length; ++n) {
                definition += "," + lineSplit[n];
            }

            Set<String> nounsSet = new HashSet<String>();

            for (String noun : nouns) {
                nounsSet.add(noun);

                Set<Integer> ids = nounToID.get(noun);
                if (ids == null) {
                    ids = new HashSet<Integer>();
                }
                ids.add(id);
                nounToID.put(noun, ids);
            }

            nounEntries.addAll(nounsSet);
            idToEntry.put(id, new Entry(id, nounsSet, definition));
        } 

        hypohypernyms = new Digraph(idToEntry.size());

        In scanner2 = new In(hyponymFilename);

        while (scanner2.hasNextLine()) {
            String line = scanner2.readLine();
            String[] lineSplit = line.split(",");

            int hypernym = Integer.parseInt(lineSplit[0]);

            for (int i = 1; i < lineSplit.length; i++) {
                int hyponym = Integer.parseInt(lineSplit[i]);
                hypohypernyms.addEdge(hypernym, hyponym);
            }
        } 
    }

    public boolean isNoun(java.lang.String noun) {
        return nounEntries.contains(noun);
    }

    public java.util.Set<java.lang.String> nouns() {
        return nounEntries;
    }

    private Set<String> hyponyms(Integer word) {
        Set<String> hyponymsSet = new HashSet<String>();
        Entry idEntry = idToEntry.get(word);

        Iterable<Integer> hyponymsIterator = hypohypernyms.adj(word);
        for (Integer hyponymId : hyponymsIterator) {
            Entry entry = idToEntry.get(hyponymId);
            hyponymsSet.addAll(entry.getSynset());
            hyponymsSet.addAll(hyponyms(hyponymId));
        }
        return hyponymsSet;
    }

    public java.util.Set<java.lang.String> hyponyms(java.lang.String word) {
        Set<String> hyponymsSet = new HashSet<String>();
        Set<Integer> ids = nounToID.get(word);
        if (ids != null) {
            for (Integer id : ids) {
                Entry idEntry = idToEntry.get(id);
                hyponymsSet.addAll(idEntry.getSynset());
                hyponymsSet.addAll(hyponyms(id));
            }
        }
        return hyponymsSet;
    }

    private class Entry {

        private int id;
        private Set<String> synset;
        private String def;

        public Entry(int id2, Set<String> synset2, String def2) {
            this.id = id2;
            this.synset = synset2;
            this.def = def2;
        }

        @Override
        public String toString() {
            String string = "";
            string += Integer.toString(id);
            string += " | ";
            for (String syn : synset) {
                string += syn + " ";
            }
            string += " : " + def;
            return string;
        }

        public Set<String> getSynset() {
            return this.synset;
        }
    }
}
