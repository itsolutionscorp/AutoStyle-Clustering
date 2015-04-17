package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class WordNet {
    private String synsetFilename;
    private String hyponymFilename;

    private HashMap<Integer, String> synset;
    private int numOfSynsets = 0;

    private Digraph graph;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
     *
     * @param synsetFilename: the synset filename path
     * @param hypnoymFilename: the hypnoym filename path
     */
    public WordNet(String synsetFilename1, String hyponymFilename1) {
        this.synsetFilename = synsetFilename1;
        this.hyponymFilename = hyponymFilename1;
        synset = new HashMap<Integer, String>();
        setSynsetADT();
        setDigraphADT();
    }

    /** Returns true if NOUN is a word in some synset.
      *
      * @param noun: the String noun you are querying for
      */
    public boolean isNoun(String noun) {
        Iterator<Integer> keySetIterator = synset.keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            String listofNounsinLine = synset.get(key);
            String[] individualNouns = listofNounsinLine.split(" ");
            for (String currentNoun: individualNouns) {
                if (noun.equals(currentNoun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns the set of all nouns.
      *
      * @return Set<String>: Returns the Set of all nouns in the data.
      */
    public Set<String> nouns() {
        Set<String> allNounsBaby = new TreeSet<String>();
        Iterator<Integer> keySetIterator = synset.keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            String listofNounsinLine = synset.get(key);
            String[] individualNouns = listofNounsinLine.split(" ");
            for (String currentNoun: individualNouns) {
                allNounsBaby.add(currentNoun);
            }
        }
        return allNounsBaby;

    }

    /** Gets all the KEYS of the synsets in which word exists.
      *
      * @param word: The word that you want all the keys to
      *
      * @return TreeSet<Integer>: a TreeSet of KEY values
      */
    private TreeSet<Integer> getID(String word) {
        TreeSet<Integer> wordInHere = new TreeSet<Integer>();
        Iterator<Integer> keySetIterator = synset.keySet().iterator();
        while (keySetIterator.hasNext()) {
            Integer key = keySetIterator.next();
            String listofNounsinLine = synset.get(key);
            String[] individualNouns = listofNounsinLine.split(" ");
            for (String currentNoun: individualNouns) {
                if (word.equals(currentNoun)) {
                    wordInHere.add(key);
                }
            }
        }
        return wordInHere;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      *
      * @param word: The word that you want all the hyponyms for.
      *
      * @return Set<String>: the Set of all words that are hyponyms of the inputed argument
      */
    public Set<String> hyponyms(String word) {
        TreeSet<String> allHypo = new TreeSet<String>();
        TreeSet<Integer> verticesOfWord = getID(word);
        TreeSet<Integer> reachableVertex = (TreeSet<Integer>) GraphHelper.descendants(graph,
            verticesOfWord);


        for (Integer vertex: verticesOfWord) {
            String listofNounsinLine = synset.get(vertex);  // Vertex is basically the key
            String[] individualNouns = listofNounsinLine.split(" ");
            for (String currentNoun: individualNouns) {
                allHypo.add(currentNoun);
            }
        }

        for (Integer vertex: reachableVertex) {
            String listofNounsinLine1 = synset.get(vertex);  // Vertex is basically the key
            String[] individualNouns1 = listofNounsinLine1.split(" ");
            for (String currentNoun1: individualNouns1) {
                allHypo.add(currentNoun1);
            }
        }

        return allHypo;
    }

    /** Opens the data files and inputs all the data into the HashMapADTs
      */
    private void setSynsetADT() {
        // opens the synset file using the In library
        In synsetReader = new In(this.synsetFilename);
        // reades a line from the file
        while (!synsetReader.isEmpty()) {  // Make sure the input file has nextline
            String oneLine = synsetReader.readLine();
            String[] splitLine = oneLine.split(",");
            synset.put(Integer.parseInt(splitLine[0]), splitLine[1]);
            numOfSynsets += 1;
        }

    }

    /** Opens the data files and inputs all the data into the Digraph ADTs
      */
    private void setDigraphADT() {
        // Initialize the Digraph with the given date entry size
        graph = new Digraph(numOfSynsets);
        // opens the hyponym file using the In library
        In hypoReader = new In(this.hyponymFilename);
        // reads the lines in the hypnym file
        while (!hypoReader.isEmpty()) {  // Make sure the input file has nextLine
            String oneLine = hypoReader.readLine();
            String[] splitLine = oneLine.split(",");
            for (String actuallyAnInt: splitLine) {
                if (!actuallyAnInt.equals(splitLine[0])) {
                    graph.addEdge(Integer.parseInt(splitLine[0]), Integer.parseInt(actuallyAnInt));
                }
            }
        }
    }

}
