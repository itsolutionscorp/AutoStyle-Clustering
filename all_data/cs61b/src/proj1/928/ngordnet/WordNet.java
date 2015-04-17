package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.ArrayList;

public class WordNet {

    private HashMap<String, ArrayList<Integer>> wordId = 
        new HashMap<String, ArrayList<Integer>>();
    private HashMap<Integer, ArrayList<String>> idWord = 
        new HashMap<Integer, ArrayList<String>>();
    private Digraph graph = new Digraph(wordId.size());

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        
        In synFile = new In(synsetFilename);
        
        while (synFile.hasNextLine()) {

            String line = synFile.readLine();
            String[] temp = line.split(",");
            Integer itemZero = Integer.parseInt(temp[0]);
            String[] line2 = temp[1].split(" ");
            if (!wordId.containsKey(itemZero)) {

                ArrayList<String> wordList = new ArrayList<String>();
                for (int x = 0; x < line2.length; x += 1) {
                    wordList.add(line2[x]);
                }
                idWord.put(itemZero, wordList);

                for (int x = 0; x < line2.length; x += 1) {
                    if (wordId.containsKey(line2[x])) {
                        wordId.get(line2[x]).add(itemZero);
                    } else {
                        ArrayList<Integer> numAdd = new ArrayList<Integer>();
                        numAdd.add(itemZero);
                        wordId.put(line2[x], numAdd);
                    }
                }
            } else {
                for (int x = 0; x < line2.length; x += 1) {
                    idWord.get(itemZero).add(line2[x]);
                }
                for (int x = 0; x < line2.length; x += 1) {
                    if (wordId.containsKey(line2[x])) {
                        wordId.get(line2[x]).add(itemZero);
                    } else {
                        ArrayList<Integer> numToAdd = new ArrayList<Integer>();
                        numToAdd.add(itemZero);
                        wordId.put(line2[x], numToAdd);
                    }
                }
            }
        }
        graph = new Digraph(wordId.size());
        In hypoFile = new In(hyponymFilename);
        while (hypoFile.hasNextLine()) {
            String line = hypoFile.readLine();
            String[] temp = line.split("[,]");
            Integer itemZero = Integer.parseInt(temp[0]);
            for (int x = 1; x < temp.length; x += 1) {
                int var = Integer.parseInt(temp[x]);
                graph.addEdge(itemZero, var);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return wordId.containsKey(noun); 
    }
    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return wordId.keySet();
    } 

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */

    //Structural Understanding - Cesar Villalobos
    public Set<String> hyponyms(String word) {
        Set<String> words = new TreeSet<String>();
        Set<Integer> ids = new TreeSet<Integer>();
        Set<Integer> results = new TreeSet<Integer>();
        
        ArrayList<Integer> nums = wordId.get(word);

        for (Integer x : nums) {
            ids.add(x);
        }

        results = GraphHelper.descendants(graph, ids);

        for (Integer x : results) {
            if (idWord.containsKey(x)) {
                for (String y : idWord.get(x)) {
                    String toAdd = y;
                    words.add(toAdd);
                }
            }
        }
        return words;
    }
}
