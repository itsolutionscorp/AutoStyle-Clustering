package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME 
    Object that stores the WordNet graph in a manner useful for extracting all hyponyms of a word.*/
    private Digraph digraph; //holds numbers and explains the relationships between these numbers
    private String[][] words; //contains words and indexes refer to the numbers in the digraph
    private String[] synfile;
    private String[] hypfile;

    /* Creates a WordNet using files from SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In syn = new In(synsetFilename);
        In hyp = new In(hyponymFilename);
        synfile = syn.readAllLines(); //length of this is the same as how many synsets there are
        hypfile = hyp.readAllLines(); //length of this is how many relationships/edges there are
      

        words = new String[synfile.length][]; //empty 2D array with boxes that contain every synset
        for (int i = 0; i < words.length; i++) {
            String[] k = synfile[i].split(","); //array: 0th element is number, 1st element is word
            words[i] = k[1].split(" "); //the word put in its corresponding place in the list
        }

        digraph = new Digraph(synfile.length); //a diagraph with nodes for every synset id
        for (int i = 0; i < hypfile.length; i++) {
            String[] k = hypfile[i].split(","); //make each entry into its own list
            if (k.length == 2) { //if the lenght of that list is 2
                int c = Integer.parseInt(k[0]);
                int d = Integer.parseInt(k[1]);
                digraph.addEdge(c, d); //define the relationship between the two
            } else { //if the length is longer than 2
                for (int j = 1; j < k.length; j++) {
                    int a = Integer.parseInt(k[0]);
                    int b = Integer.parseInt(k[j]);
                    digraph.addEdge(a, b); //relationship between the first number and the others
                }
            }
        }
    } 

      /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) { 
                if (noun.equals(words[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

      /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> x = new HashSet<String>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                x.add(words[i][j]); 
            }
        } return x;
    }

        /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> x = new HashSet<String>();
        x.add(word);
        Set<Integer> id = new HashSet<Integer>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                if (word.equals(words[i][j])) {
                    System.out.print(i);
                    id.add(i); //figure out what number word is associated with, add it to set
                }
            }
        }
        Set<Integer> descendants = GraphHelper.descendants(digraph, id); 
        for (int number : descendants) {
            for (int j = 0; j < words[number].length; j++) {
                System.out.print(number);
                x.add(words[number][j]);
            }
        }
        return x;
    }
}
