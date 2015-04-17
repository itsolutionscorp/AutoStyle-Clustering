package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

/** Creates a WordNet object to hold the hyponyms and synset data and 
  * to correctly associate them with their respective paths. 
  * @author Richard Qian
  */
public class WordNet {
    private HashMap<Integer, SynEntry> hashSyn;
    private Digraph dgraph;

    /** Creates a WordNet usng files from SYNSETFILENAME and HYPONYMFILENAME
      * 
      * @param synsetFilename - The name of the synsets file to pull data from
      * @param hyponymFilename - The name of the hyponyms file to pull data from
      */
    public WordNet(String synsetFilename, String hyponymFilename) {
        hashSyn = new HashMap<Integer, SynEntry>();
        try {
            Scanner sn = new Scanner(new File(synsetFilename));
            while (sn.hasNextLine()) {
                String[] inLine = sn.nextLine().split(",");
                int number = Integer.parseInt(inLine[0]);
                String words = inLine[1];
                String definition = inLine[2];
                hashSyn.put(number, new SynEntry(number, words, definition));
            }
            dgraph = new Digraph(hashSyn.size());
        } catch (FileNotFoundException fnfe) {
            System.out.println("Your file doesn't exist");
        }
        try {
            Scanner hn = new Scanner(new File(hyponymFilename));
            while (hn.hasNextLine()) {
                String[] inLine = hn.nextLine().split(",");
                int[] array = new int[inLine.length];
                int count = 0;
                for (String s: inLine) {
                    array[count] = Integer.parseInt(s);
                    count += 1;
                }
                for (int i = 1; i < array.length; i++) {
                    dgraph.addEdge(array[0], array[i]);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Your file doesn't exist");
        }
    }

    /**Returns the set of all hyponyms of WORD as well as all synonyms of WORD. 
      * If WORD belongs to 
      * multiple synsets, return all hyponyms of all these synsets. 
      * Do not include hyponyms of synonyms.
      *
      * @param word - The word to find the hyponyms of.
      *
      * @return - A Set of Strings containing the hyponyms of WORD
      */
    public Set<String> hyponyms(String word) {
        TreeSet<Integer> hypotags = new TreeSet<Integer>();
        HashSet<String> stringtags = new HashSet<String>();
        for (Map.Entry<Integer, SynEntry> entry: hashSyn.entrySet()) {
            for (String str: entry.getValue().words) {
                if (word.equals(str)) {
                    hypotags.add(entry.getKey());
                }
            }
        }
        Set<Integer> locations = GraphHelper.descendants(dgraph, hypotags);
        for (int i: locations) {
            for (String k : hashSyn.get(i).words) {
                stringtags.add(k);
            }
        }
        return stringtags;
    }

    /** Determines whether NOUN is included in the list of nouns
      * 
      * @param noun - The word to search for in the WordNet
      *
      * @return - true if the word is within WordNet, false if it is not
      */
    public boolean isNoun(String noun) {
        for (Map.Entry<Integer, SynEntry> entry : hashSyn.entrySet()) {
            for (String str: entry.getValue().words) {
                if (noun.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Creates a Set containing all the nouns within this WordNet
      * 
      * @return - A Set<String> containing all the nouns within this WordNet
      */
    public Set<String> nouns() {
        HashSet<String> stringtags = new HashSet<String>();
        for (Map.Entry<Integer, SynEntry> entry : hashSyn.entrySet()) {
            for (String str: entry.getValue().words) {
                stringtags.add(str);
            }
        }
        return stringtags;
    }

    /** A SynEntry helper class that helps sort out data within WordNet by
      * storing the synset data of each word in a single entry. */
    private class SynEntry {
        private int num;
        private String[] words;
        private String def;

        /** A SynEntry object that stores the number associated with a word(s), 
          * the word(s) that have that number designation, and the definition 
          * of those word(s).
          * 
          * @param n - The number designation of the word(s)
          * @param w - The word(s) to assign to the number; if there are multiple
          *      words, it splits them up 
          * @param d - The definition of the word(s) in question. 
          */
        public SynEntry(int n, String w, String d) {
            num = n;
            words = w.split(" ");
            def = d;
        }
    }

    /** A HypEntry helper class that helps sort out data within WordNet by 
      * storing the hyponym data of each line in a single entry. */
    private class HypEntry {
        private int head;
        private int[] hyps;

        /** A HypEntry object that stores a line of numbers obtained from the
          * hyponyms file, storing the first as a head to be referenced.
          * 
          * @param a - The first number in a line, where the numbers that follow
          *     designate its hyponyms
          * @param h - The numbers of the hyponyms of the first number
          */
        public HypEntry(int a, int[] h) {
            head = a;
            hyps = h;
        }
    }
}
