package ngordnet;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private ArrayList<String> hyponymList = new ArrayList<String>();
    private ArrayList<String[]> synsetListSplit = new ArrayList<String[]>();
    private Digraph g;
    private TreeSet<String> nouns = new TreeSet<String>();

    public WordNet(String synsetFile, String hyponymFile) {
        In synset = new In(synsetFile);
        ArrayList<String> synsetList = new ArrayList<String>();
        In hyponym = new In(hyponymFile);

        /*
         * The method assumes that all the items in synset are indexed
         * correctly. The information is then placed into an ArrayList where we
         * can use the index of the ArrayList to obtain the word.
         */
        while (synset.hasNextLine()) {
            synsetList.add(synset.readLine().split(",")[1]);
        }
        for (String item : synsetList) {
            // String.split() for whitespace using "\\s+" from:
            // http://stackoverflow.com/questions/7899525/how-to-split-a-string-by-space
            synsetListSplit.add(item.split("\\s+"));
        }
        g = new Digraph(synsetList.size());
        while (hyponym.hasNextLine()) {
            hyponymList.add(hyponym.readLine());
        }
        for (String path : hyponymList) {
            // Learned String split() method from:
            // http://www.tutorialspoint.com/java/java_string_split.htm
            String[] pathSplit = path.split(",");
            for (int i = 1; i < pathSplit.length; i++) {
                // Integer.parseInt() method from:
                // http://stackoverflow.com/questions/5585779/converting-string-to-int-in-java
                g.addEdge(Integer.parseInt(pathSplit[0]), Integer
                        .parseInt(pathSplit[i]));
            }
        }
        for (String[] item : synsetListSplit) {
            for (String noun : item) {
                nouns.add(noun);
            }
        }
    }

    public Set<String> hyponyms(String word) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < synsetListSplit.size(); i++) {
            for (int j = 0; j < synsetListSplit.get(i).length; j++) {
                if (word.equals(synsetListSplit.get(i)[j])) {
                    indexes.add(i);
                    indexes.add(j);
                }
            }
        }
        // Code mirrored from GraphDemo.java
        Set<String> hyponymsSet = new TreeSet<String>();
        for (int k = 1; k < indexes.size(); k += 2) {
            Set<Integer> wordIndexSet = new TreeSet<Integer>();
            wordIndexSet.add(indexes.get(k - 1));
            Set<Integer> intDescendents = GraphHelper.descendants(g,
                    wordIndexSet);
            String nounInitial = synsetListSplit.get(indexes.get(k - 1))[indexes
                    .get(k)];
            if (!isNounInSet(hyponymsSet, nounInitial)) {
                hyponymsSet.add(nounInitial);
            }
            for (String item : synsetListSplit.get(indexes.get(k - 1))) {
                if (!isNounInSet(hyponymsSet, item)) {
                    hyponymsSet.add(item);
                }
            }
            for (Integer index : intDescendents) {
                for (String part : synsetListSplit.get(index)) {
                    if (!isNounInSet(hyponymsSet, part)) {
                        hyponymsSet.add(part);
                    }
                }
            }
        }
        return hyponymsSet;
    }

    private boolean isNounInSet(Set<String> s, String noun) {
        for (String item : s) {
            if (noun.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNoun(String noun) {
        for (String item : nouns) {
            if (noun.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public Set<String> nouns() {
        return nouns;
    }
}
