package ngordnet;

import java.util.Set;
import java.util.TreeMap;
import java.util.Map;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {

    private TreeMap<Integer, TreeSet<String>> synsetFiles;
    private TreeMap<Integer, TreeSet<String>> hyponymFiles;
    private Set<String> nouns;
    private int verticesCount;
    private String synsetFile;
    private String hyponymFile;
    private Digraph g;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetFile = synsetFilename;
        hyponymFile = hyponymFilename;
        verticesCount = 0;
        nouns = new TreeSet<String>();
        synsetFiles = new TreeMap<Integer, TreeSet<String>>();
        hyponymFiles = new TreeMap<Integer, TreeSet<String>>();
        synsetFiles = readFile(synsetFilename, synsetFiles);
        hyponymFiles = readFile(hyponymFilename, hyponymFiles);

        // Creates Digraph with edges
        g = new Digraph(verticesCount * 2);
        for (Map.Entry<Integer, TreeSet<String>> entry : hyponymFiles
                .entrySet()) {
            Integer hyponymKey = entry.getKey();
            TreeSet<String> digraphSet = entry.getValue();
            for (String s : digraphSet) {
                Integer index = Integer.parseInt(s);
                g.addEdge(hyponymKey, index);
            }
        }
    }

    // creates TreeSet with input files
    private TreeMap<Integer, TreeSet<String>> readFile(String fileName,
            TreeMap<Integer, TreeSet<String>> returnMap) {
        In in = new In(fileName);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] elements = line.split(",");
            Integer key = Integer.parseInt(elements[0]);
            TreeSet<String> valueSet = new TreeSet<String>();
            // Splitting procedure for hyponyms
            if (fileName.equals(hyponymFile)) {
                for (int i = 1; i < elements.length; i++) {
                    if (returnMap.containsKey(key)) {
                        valueSet = returnMap.get(key);
                    }
                    valueSet.add(elements[i]);
                    verticesCount += 1;
                }
            } else {
                String[] split = elements[1].split(" ");
                for (int i = 0; i < split.length; i++) {
                    valueSet.add(split[i]);
                    nouns.add(split[i]);
                }
            }
            returnMap.put(key, valueSet);
        }
        return returnMap;
    }

    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> returnHyponyms = new TreeSet<String>();
        Set<Integer> returnHyponymKey = new TreeSet<Integer>();
        Set<String> synsetVals = new TreeSet<String>();
        Set<Integer> gSet = new TreeSet<Integer>();
        for (Map.Entry<Integer, TreeSet<String>> entry : synsetFiles.entrySet()) {
            Integer hyponymKey = entry.getKey();
            for (String s : entry.getValue()) {
                if (s.equals(word)) {
                    gSet.add(hyponymKey);
                    returnHyponymKey = GraphHelper.descendants(g, gSet);
                }
            }
        }

        for (Integer i : returnHyponymKey) {
            synsetVals = synsetFiles.get(i);
            for (String s : synsetVals) {
                returnHyponyms.add(s);
            }
        }
        return returnHyponyms;
    }
}
