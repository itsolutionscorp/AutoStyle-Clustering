package ngordnet;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
public class WordNet {
    private ArrayList<Entry> entries;
    private Digraph hyponyms;
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetRead = new In(synsetFilename);
        In hyponymRead = new In(hyponymFilename);
        String[] split;
        entries = new ArrayList<Entry>();
        Entry filler = new Entry();
        while (synsetRead.hasNextLine()) {
            entries.add(filler);
            synsetRead.readLine();
        }
        synsetRead = new In(synsetFilename);
        while (synsetRead.hasNextLine()) {
            split = synsetRead.readLine().split(",");
            entries.set(Integer.parseInt(split[0]),  
                new Entry(Integer.parseInt(split[0]), split[1], split[2]));
        }
        hyponyms = new Digraph(entries.size());
        while (hyponymRead.hasNextLine()) {
            split = hyponymRead.readLine().split(",");
            int first = Integer.parseInt(split[0]);
            for (int i = 1; i < split.length; i++) {
                hyponyms.addEdge(first, Integer.parseInt(split[i]));
            }
        }
    }
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Entry entry: entries) {
            if (entry.getNouns().contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> returnValue = new TreeSet<String>();
        for (Entry entry : entries) {
            returnValue.addAll(entry.getNouns());
        }
        return returnValue;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        TreeSet<String> returnValue = new TreeSet<String>();
        for (Entry entry: entries) {
            if (entry.getNouns().contains(word)) {
                ids.add(entry.getId());
                returnValue.addAll(entry.getNouns());
            }
        }
        for (int i = 0; i < ids.size(); i++) {
            for (Integer adjacent : hyponyms.adj(ids.get(i))) {
                if (!ids.contains(adjacent)) {
                    ids.add(adjacent);
                }
                returnValue.addAll(entries.get(adjacent).getNouns());
            }
        }
        return returnValue;
    }

    private class Entry implements Comparable<Entry> {
        private Integer id;
        private ArrayList<String> nouns;
        private String description;
        public Entry() {
        }
        public Entry(Integer identification, String noun, String desc) {
            id = identification;
            nouns = new ArrayList<String>(Arrays.asList(noun.split(" ")));
            description = desc;
        }
        public int compareTo(Entry other) {
            return id - other.getId();
        }
        public Integer getId() {
            return id;
        }
        public ArrayList<String> getNouns() {
            return nouns;
        }
        public String getDescription() {
            return description;
        }
    }
}
