package ngordnet;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap; //* replaced with hashmap stuffs
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private String synsetFile;
    private String hyponymFile;
    private HashMap<Integer, Synset> mapOfIndexToSynset;
    private HashMap<String, Set<Integer>> mapOfNounToIndex;
    private Digraph mapOfIndices;
    public WordNet(String synsetFilename, String hyponymFilename) {
        this.synsetFile = synsetFilename;
        this.hyponymFile = hyponymFilename;
        readSynsetIntoMaps(synsetFile, hyponymFile);
    }

    private void readSynsetIntoMaps(String synFile, String hypFile) {
        In myStream = new In(synFile);
        this.mapOfIndexToSynset = new HashMap<Integer, Synset>();
        this.mapOfNounToIndex = new HashMap<String, Set<Integer>>();
        while (myStream.hasNextLine()) {
            String nextLine = myStream.readLine();
            String[] indexWordDef = nextLine.split(",", 4);
            int actualIndex = Integer.parseInt(indexWordDef[0]);
            String theNouns = indexWordDef[1];
            String[] actualNouns = theNouns.split(" ", 0);
            String theDef = indexWordDef[2];
            Synset mySynset = new Synset(actualNouns, theDef);
            this.mapOfIndexToSynset.put(actualIndex, mySynset);
            for (String temp : actualNouns) {
                Set<Integer> l = mapOfNounToIndex.get(temp);
                if (l == null) {
                    mapOfNounToIndex.put(temp, l = new HashSet<Integer>());
                }
                l.add(actualIndex);
                this.mapOfNounToIndex.put(temp, l);
            }
        }
        In hypString = new In(hypFile);
        this.mapOfIndices = new Digraph(mapOfIndexToSynset.size());
        String myLine = hypString.readLine();
        while (myLine != null) {
            String[] hypList = myLine.split(",", 0);
            for (int i = 1; i < hypList.length; i += 1) {
                int base = Integer.parseInt(hypList[0]);
                int addOn = Integer.parseInt(hypList[i]);
                this.mapOfIndices.addEdge(base, addOn);
            }
            myLine = hypString.readLine();
        }
    }

    public boolean isNoun(String noun) {
        return this.mapOfNounToIndex.containsKey(noun);
    }

    public Set<String> nouns() {
        HashSet<String> mySet = new HashSet<String>();
        for (String temp : this.mapOfNounToIndex.keySet()) {
            mySet.add(temp);
        }
        return mySet;
    }

    public Set<String> hyponyms(String word) {
        HashSet<String> mySet = new HashSet<String>();
        Set<Integer> wordSet = mapOfNounToIndex.get(word);
        Set<Integer> indexSet = GraphHelper.descendants(mapOfIndices, wordSet);
        for (Integer myIndex : indexSet) {
            Synset mySynset = mapOfIndexToSynset.get(myIndex);
            for (String aNoun : mySynset.myNouns) {
                mySet.add(aNoun);
            }
        }
        return mySet;
    }

    private class Synset {
        private String[] myNouns;
        private String myDef;
        private Synset(String[] parString, String parDefinition) {
            this.myNouns = parString;
            this.myDef = parDefinition;
        }
    }
}
