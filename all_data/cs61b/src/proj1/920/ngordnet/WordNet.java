package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

/* @author: Austin Leung */

public class WordNet {

    private ArrayList<String> synsetsArrayList; //array of all synsets
    private Set<String> nounsSet; //break each word into a String
    private Digraph wordNetDigraph; //digraph
    private Map<String, Set<Integer>> nounMap; //holds <noun,set of numbers that ID it>

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        /* citation: Java Methods A & AB by Maria Litvin and Gary Litvin*/
        File synsetFile = new File(synsetFilename);
        Scanner synsetInput = null;
        try {
            synsetInput = new Scanner(synsetFile);
        } catch (FileNotFoundException ex1) {
            System.out.println(synsetFilename + " file cannot be opened");
        }
        File hyponymFile = new File(hyponymFilename);
        Scanner hyponymInput = null;
        try {
            hyponymInput = new Scanner(hyponymFile);
        } catch (FileNotFoundException ex2) {
            System.out.println(hyponymFilename + " file cannot be opened");
        } 
        
        //initialize variables
        nounsSet = new HashSet<String>();
        synsetsArrayList = new ArrayList<String>();
        nounMap = new HashMap<String, Set<Integer>>();
        Set<Integer> synsetNumberSet = new HashSet<Integer>();

        /*add synsets to an ArrayList, nouns to a Set, and nouns to a map*/
        while (synsetInput.hasNext()) {
            String synsetLine = synsetInput.nextLine();
            String[] synsetArray = synsetLine.split(",");
            int synsetNumber = Integer.parseInt(synsetArray[0]);
            synsetsArrayList.add(synsetNumber, synsetArray[1]); // add synsets to instance Array
            String[] nounArray = synsetArray[1].split(" ");
            for (int i = 0; i < nounArray.length; i += 1) {
                nounsSet.add(nounArray[i]); // add nouns to instance Set
                if (nounMap.containsKey(nounArray[i])) {
                    Set<Integer> oldSet = nounMap.get(nounArray[i]);
                    oldSet.add(synsetNumber);
                    nounMap.put(nounArray[i], oldSet); // add new int to existing set and noun
                } else {
                    Set<Integer> newSet = new HashSet<Integer>();
                    newSet.add(synsetNumber);
                    nounMap.put(nounArray[i], newSet); // add new noun and int to Map
                }
            }
        } 


        // initialize Digraph
        wordNetDigraph = new Digraph(synsetsArrayList.size());

        /* fill out Digraph */
        while (hyponymInput.hasNext()) {
            String hyponymLine = hyponymInput.nextLine();
            String[] hyponymArray = hyponymLine.split(",");
            Integer[] hyponymArrayInt = new Integer[hyponymArray.length];
            for (int i = 0; i < hyponymArray.length; i += 1) {
                // convert array from strings to integers
                hyponymArrayInt[i] = Integer.parseInt(hyponymArray[i]);
            }
            int first = hyponymArrayInt[0];
            Integer[] hyponymArrayNext = new Integer[hyponymArrayInt.length - 1];
            System.arraycopy(hyponymArrayInt, 1, hyponymArrayNext, 0, hyponymArrayNext.length);
            for (int next = 0; next < hyponymArrayInt.length; next += 1) {
                // give each edge to the vertice
                wordNetDigraph.addEdge(first, hyponymArrayInt[next]);
            }
        }
        

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return this.nouns().contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounsSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        // holds set of ints that correlates to hyponyms
        Set<Integer> hyponymIntSet = GraphHelper.descendants(wordNetDigraph, nounMap.get(word));
        Iterator<Integer> hyponymIterator = hyponymIntSet.iterator();
        Set<String> hyponymStringSet = new HashSet<String>();
        while (hyponymIterator.hasNext()) {
            int temp = hyponymIterator.next();
            String synsets = synsetsArrayList.get(temp);
            String[] synsetsSeparated = synsets.split(" ");
            for (int i = 0; i < synsetsSeparated.length; i += 1) {
                hyponymStringSet.add(synsetsSeparated[i]);
            }
        }
        return hyponymStringSet;
    }

}
