package ngordnet;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import java.util.Set;
import java.util.HashSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Digraph hyponymGraph;
    private Synset[] synsets;

    public WordNet(String synsetFilename, String hyponymFilename) {
        String synsetsRaw = new In(synsetFilename).readAll();
        String hyponyms = new In(hyponymFilename).readAll();

        constructSynsets(synsetsRaw);
        constructHyponyms(hyponyms);
    }

    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();

        for (Synset synset : synsets) {
            for (String noun : synset.getNouns()) {
                nouns.add(noun);
            }
        }

        return nouns;
    }

    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

    public Set<String> hyponyms(String noun) {
        Set<Integer> nouns = synsetIdsForNoun(noun);

        Set<Integer> hyponymIds = GraphHelper.descendants(hyponymGraph, nouns);
        Set<String> hyponyms = new HashSet<String>();

        for (int synsetId : hyponymIds) {
            Synset synset = synsets[synsetId];
            for (String synsetNoun : synset.getNouns()) {
                hyponyms.add(synsetNoun);
            }
        }

        return hyponyms;
    }

    private Set<Integer> synsetIdsForNoun(String targetNoun) {
        Set<Integer> synsetIds = new HashSet<Integer>();

        for (Synset synset : synsets) {
            for (String noun : synset.getNouns()) {
                if (noun.equals(targetNoun)) {
                    synsetIds.add(synset.getId());
                }
            }
        }

        return synsetIds;
    }

    private void constructSynsets(String synsetsRaw) {
        String[] lines = synsetsRaw.split("\n");
        this.synsets = new Synset[lines.length];

        for (String line : lines) {
            String[] parts = line.split(",");

            int id = Integer.parseInt(parts[0]);
            String synset = parts[1];

            this.synsets[id] = new Synset(id, synset);
        }
    }

    private void constructHyponyms(String hyponyms) {
        hyponymGraph = new Digraph(synsets.length);

        for (String line : hyponyms.split("\n")) {
            String[] parts = line.split(",");

            int hypernym = Integer.parseInt(parts[0]);

            for (int i = 1; i < parts.length; i++) {
                int hyponym = Integer.parseInt(parts[i]);
                hyponymGraph.addEdge(hypernym, hyponym);
            }
        }
    }

    private String readFileToString(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            return null;
        }
    }

    private class Synset {
        private int id;
        private String[] nouns;

        public Synset(int id, String nounsStr) {
            this.id = id;
            this.nouns = nounsStr.split(" ");
        }

        public int getId() {
            return id;
        }

        public String[] getNouns() {
            return nouns;
        }
    }
}
