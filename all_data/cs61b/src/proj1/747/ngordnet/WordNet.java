package ngordnet;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import edu.princeton.cs.algs4.Digraph;
import java.util.NoSuchElementException;
import java.util.Formatter;

/*ngordnet/WordNet.java:24: error: incompatible types
      vertices = new HashMap<Integer, HashSet<String>>();
                 ^
  required: Map<Integer,Set<String>>
  found:    HashMap<Integer,HashSet<String>>*/

public class WordNet {

    private HashMap<Integer, HashSet<String>> vertices;
    private Set<Integer> keySet;
    private int size;
    private Digraph digraph;
    private Scanner scanner;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    /*@http://stackoverflow.com/questions/15183761/how-to-check-the-end-of-line-using-scanner*/
    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            vertices = new HashMap<Integer, HashSet<String>>();
            scanner = new Scanner(new File(synsetFilename));
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                String[] splitNextLine = nextLine.split(",");
                int id = Integer.parseInt(splitNextLine[0]);
                String synset = splitNextLine[1];
                Scanner nestedScanner = new Scanner(synset);
                HashSet<String> synonyms = new HashSet<String>();
                while (nestedScanner.hasNext()) {
                    synonyms.add(nestedScanner.next());
                }
                nestedScanner.close();
                vertices.put(id, synonyms);
            }
            scanner.close();
            scanner = new Scanner(new File(hyponymFilename));
            keySet = vertices.keySet();
            size = keySet.size();
            digraph = new Digraph(size);
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                String[] ids = nextLine.split(",");
                int synonym = Integer.parseInt(ids[0]);
                for (int i = 1; i < ids.length; i += 1) {
                    digraph.addEdge(synonym, Integer.parseInt(ids[i]));
                }
            }
            scanner.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("FileNotFound");
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (nouns().contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (int key : keySet) {
            for (String noun : vertices.get(key)) {
                nouns.add(noun);
            }
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        if (!isNoun(word)) {
            throw new NoSuchElementException();
        }
        Set<Integer> ids = new HashSet<Integer>();
        for (int key : keySet) {
            if (vertices.get(key).contains(word)) {
                ids.add(key);
            }
        }
        Set<Integer> descendants = GraphHelper.descendants(digraph, ids);
        for (int id : ids) {
            Set<String> synset = vertices.get(id);
            for (String synonym : synset) {
                hyponyms.add(synonym);
            }
        }
        for (int id : descendants) {
            Set<String> synset = vertices.get(id);
            for (String hyponym : synset) {
                hyponyms.add(hyponym);
            }
        }
        return hyponyms;
    }

    /*@hw3.SortedComparableList.java*/
    private String toString(Set<String> hyponyms) {
        Formatter out = new Formatter();
        String sep;
        sep = "[";
        for (String hyponym: hyponyms) {
            out.format("%s%d", sep, hyponym);
            sep = ", ";
        }
        out.format("]");
        return out.toString();
    } 
}
