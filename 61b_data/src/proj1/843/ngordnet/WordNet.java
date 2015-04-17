package ngordnet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

/**
 * Created by FelipeMendes on 3/2/15.
 */
public class WordNet {

    /*
     *The idea behind the data structure is simple. From the synsets file
     * we create a HashMap with all the IDs where each dif ferent word is
     * in a Synset. Then with the hyponyms file we create a digraph
     * relating the synsets and its hyponyms. Thus, when we need to access
     * the noun, we use the HashMap
     * and when we want to get the hyponyms we just have to go down in the
     * digraph from the indexes that we have
     * and when we want to get all the nouns we just have to return the set
     * of nouns in the HashMap.
     */
    private Digraph wordNetGraph; //Hierarchical relationship

    private HashMap<String, HashSet<Integer>> wordMapping; //Each word and its hyponyms.

    private HashMap<Integer, HashSet<String>> idsToSynsetsMap; //Relation between id and words.



    /** Creates a WordNet using files   for m SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);

        wordMapping = new HashMap<>();
        idsToSynsetsMap = new HashMap<>();

        HashSet<String> synsetSet = new HashSet<>();
        HashSet<Integer> auxiliary = new HashSet<>();


        String line;
        String[] splitLine;
        String[] synsetArray;

        int idValue;
        int numberOfSynsets = 0;  //Track number of synsets   for  the creation of the digraph.



        while (synsetFile.hasNextLine()) {
            numberOfSynsets++;
            line = synsetFile.readLine();
            splitLine = line.split(",");
            idValue = Integer.parseInt(splitLine[0]);
            synsetArray = splitLine[1].split("\\s");
            for (String noun: synsetArray) {
                synsetSet.add(noun);
                if (wordMapping.containsKey(noun)) {
                    auxiliary = wordMapping.get(noun);
                    auxiliary.add(idValue);
                    wordMapping.put(noun, new HashSet<>(auxiliary));
                    auxiliary.clear();
                } else {
                    auxiliary.add(idValue);
                    wordMapping.put(noun, new HashSet<>(auxiliary));
                    auxiliary.clear();
                }
            }
            idsToSynsetsMap.put(idValue, new HashSet<>(synsetSet));
            synsetSet.clear();
        }

        synsetFile.close();
        In hyponymFile = new In(hyponymFilename);
        wordNetGraph = new Digraph(numberOfSynsets);
        int hyperVertex;
        while (hyponymFile.hasNextLine()) {
            line = hyponymFile.readLine();
            splitLine = line.split(",");
            hyperVertex = Integer.parseInt(splitLine[0]);
            for (String vertex: splitLine) {
                if (Integer.parseInt(vertex) != hyperVertex) {
                    wordNetGraph.addEdge(hyperVertex, Integer.parseInt(vertex));
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordMapping.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordMapping.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. if WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys   for  an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String word) {
        if (isNoun(word)) {
            Set<Integer> setOfIDs = GraphHelper.descendants(wordNetGraph, wordMapping.get(word));
            HashSet<String> setOfOutput = new HashSet<>();
            for (Integer id: setOfIDs) {
                for (String noun: idsToSynsetsMap.get(id)) {
                    setOfOutput.add(noun);
                }
            }
            return setOfOutput;
        } else {
            throw new IllegalArgumentException("No records of " + word);
        }
    }

}
