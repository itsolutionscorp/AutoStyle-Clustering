package ngordnet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {

    private String[] elementsOfLine;
    private int elementID;
    private List<String> wordAndSynonyms;
    private Collection<List<String>> individualCollectionOfNouns;
    private Iterator<List<String>> collectionIterator;
    private List<String> nextList;
    private Set<String> setOfNouns;

    private Digraph hyponymsDigraph;
    private Map<Integer, List<String>> synsetsMap;

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // constructor for WordNet --> read files in, create mapping between
        // keys and values

        synsetsMap = makeSynsetsMap(synsetFilename);
        hyponymsDigraph = makeHyponymsDigraph(hyponymFilename, synsetFilename);

        setOfNouns = nouns(); // saves the set of all nouns

        // hyponymsDigraph: mapping between vertices (hypernyms) and edges
        // (hyponyms)
        // synsetsMap: MAP between KEYS: id of word || VALUES: LIST [word,
        // synonyms of the word, gloss of the word]
    }

    private boolean canScan(In fileIn) {
        return fileIn.hasNextLine();
    }

    private String[] getNextLineElements(String nextLine) {
        String[] allItems = nextLine.split(","); // array containing the split
                                                 // elements of nextLine
        return allItems;

        /*
         * String outputLine = fileIn.nextLine(); String[] allItems =
         * outputLine.split(","); //array containing the split elements of
         * outputLine return allItems;
         */
    }

    /*
     * Returns the number of vertices to produce for hyponymsDigraph, based on
     * the number of integers per line there are Three integers on a line means
     * we need two vertices (one between the first number and the seccond,
     * another between the first number and the third
     */
    private int numVertices(String fileName) {
        int vertices = 0;

        In fileIn = new In(fileName);
        while (fileIn.hasNextLine()) {
            fileIn.readLine();
            vertices += 1;
        }
        return vertices;
    }

    /* Makes a digraph out of the hyponyms file */
    private Digraph makeHyponymsDigraph(String filename, String lineCountFile) {

        int verticeCount = numVertices(lineCountFile);
        // System.out.println(x);
        hyponymsDigraph = new Digraph(verticeCount); // make a new Digraph with
                                                     // verticeCount vertices

        In fileIn = new In(filename);

        while (canScan(fileIn)) {
            elementsOfLine = getNextLineElements(fileIn.readLine());
            elementID = Integer.parseInt(elementsOfLine[0]);

            for (int i = 1; i < elementsOfLine.length; i++) {
                // System.out.println("Adding edge: " + elementID +
                // Integer.parseInt(elementsOfLine[i]));
                hyponymsDigraph.addEdge(elementID, Integer.parseInt(elementsOfLine[i]));
            }

        }

        return hyponymsDigraph;
    }

    /* Makes a map of all of the synsets */
    private Map<Integer, List<String>> makeSynsetsMap(String filename) {
        Map<Integer, List<String>> returnMap = new HashMap<Integer, List<String>>();

        In fileIn = new In(filename);

        while (canScan(fileIn)) {
            elementsOfLine = getNextLineElements(fileIn.readLine()); // read the
                                                                     // next
                                                                     // line for
                                                                     // all
                                                                     // elements
            elementID = Integer.parseInt(elementsOfLine[0]); // change first
                                                             // element of the
                                                             // string list back
                                                             // to an int
            wordAndSynonyms = Arrays.asList(elementsOfLine[1].split(" ")); // list
                                                                           // containing
                                                                           // the
                                                                           // word
                                                                           // itself
                                                                           // and
                                                                           // the
                                                                           // word's
                                                                           // synonyms
            // System.arraycopy(elementsOfLine, 1, wordAndSynonyms, 0,
            // elementsOfLine.length - 2); //don't want the 0th element (synset
            // id) or last element (gloss)
            returnMap.put(elementID, wordAndSynonyms);
        }

        return returnMap;
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return (setOfNouns.contains(noun));
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {

        Set<String> returnSet = new HashSet<String>();

        // returns a collection of all of the values contained in the map
        // (values: Lists of Strings)
        individualCollectionOfNouns = synsetsMap.values();
        collectionIterator = individualCollectionOfNouns.iterator(); // iterator
                                                                     // over the
                                                                     // elements
                                                                     // in the
                                                                     // collection
                                                                     // of
                                                                     // values
                                                                     // -->
                                                                     // yields
                                                                     // one List
                                                                     // of
                                                                     // Strings
                                                                     // per
                                                                     // iteration

        while (collectionIterator.hasNext()) { // still more Lists to grab nouns
                                               // from!
            nextList = collectionIterator.next();
            returnSet.addAll(nextList);

            // collection is a super-interface to list --> can just
            // addAll(Collection<? extends E> c) to our set
            // because all of the information is either the word itself or a
            // synonym of the word!
        }
        return returnSet;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {

        Set<String> setOfHyponyms = new HashSet<String>(); // set of all
                                                           // hyponyms of a word

        Set<Integer> integersLookup = new HashSet<Integer>(); // set of all
                                                              // integers to
                                                              // lookup in
                                                              // hyponymsDigraph
        Set<Integer> integersToTranslate = new HashSet<Integer>(); // set of
                                                                   // integers
                                                                   // that have
                                                                   // been found
                                                                   // in
                                                                   // hyponymsDigraph;
                                                                   // convert to
                                                                   // words!

        Set<Integer> setOfSynsetKeys = synsetsMap.keySet(); // set of all keys
                                                            // within the
                                                            // synsetMap

        for (Integer key : setOfSynsetKeys) {

            List<String> values = synsetsMap.get(key); // see if the List
                                                       // associated with that
                                                       // key contains word
            if (values.contains(word)) {
                setOfHyponyms.addAll(values); // add the word itself and the
                                              // synonyms of that word
                integersLookup.add(key); // need to look at the hyponyms of this
                                         // id!
            }

        }

        // integers representing hyponyms of the word; descendants() finds ALL
        // of the children nodes!
        integersToTranslate = GraphHelper.descendants(hyponymsDigraph, integersLookup);

        for (Integer key : integersToTranslate) {
            setOfHyponyms.addAll(synsetsMap.get(key));
            // might return just one word, or a set of words (if the looked-up
            // word has a synonym)
        }
        return setOfHyponyms;
    }

}
