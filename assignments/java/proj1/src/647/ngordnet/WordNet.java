package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;

/** WordNet
  *  @author Malachi Hamada
  *  An object that stores the WordNet graph in a manner useful for extracting
  *  all hyponyms of a word. 
  */
public class WordNet {
    private String synsFilename;
    private String hypoFilename;
    private Set<String> nounSet;
    private ArrayList<String> nounArray;
    private Digraph g;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME. */
    public WordNet(String synsetFilename, String hyponymFilename) {
        // Initialize class variables.
        synsFilename = synsetFilename;
        hypoFilename = hyponymFilename; 
        nounSet = Collections.synchronizedSet(new HashSet<String>());
        nounArray = new ArrayList<String>();

        // Create an In object and iterate over lines of SYNSETFILENAME.
        In i = new In(synsetFilename);
        int totalLines = 0;
        while (i.hasNextLine()) {
            // Get each line from the file.
            String line = i.readLine();
            totalLines += 1;

            // Get a string of noun(s) and add it/them to the set.
            String noun = line.split(",")[1];
            nounArray.add(noun);
            if (noun.contains(" ")) {
                String[] nouns = noun.split(" ");
                for (String n : nouns) {
                    if (!nounSet.contains(n)) {
                        nounSet.add(n);
                    }
                }
            } else {
                if (!nounSet.contains(noun)) {
                    nounSet.add(noun);               
                }
            }
        }

        // Create a Digraph to keep track of descendents.
        g = new Digraph(totalLines);
        In j = new In(hyponymFilename);
        while (j.hasNextLine()) {
            String line = j.readLine();
            int index = Integer.parseInt(line.split(",")[0]);
            for (int k = 1; k < line.split(",").length; k += 1) {
                g.addEdge(index, Integer.parseInt(line.split(",")[k]));
            }
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounSet != null && nounSet.contains(noun);
    }

    /** Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        // Create a set of hyponyms to be returned at the end.
        Set<String> hyponymSet = Collections.synchronizedSet(new HashSet<String>());

        // Get synonyms and put them in the set.
        // Create a list to store indices.
        Set<Integer> indices = new HashSet<Integer>();
        for (int i = 0; i < nounArray.size(); i += 1) {
            ArrayList<String> synonyms = new ArrayList<String>(
                                        Arrays.asList(nounArray.get(i).split(" ")));
            if (synonyms.contains(word)) {
                for (String s : synonyms) {
                    if (!hyponymSet.contains(s)) {
                        hyponymSet.add(s);
                    }
                }
                if (!indices.contains(i)) {
                    indices.add(i);                  
                }
            }
        }

        // Add descendents to indices list.
        for (int i : GraphHelper.descendants(g, indices)) {
            indices.add(i);
        }

        // Find hyponyms at specified indices and add them to the set.
        for (int index : indices) {
            String hyponym = nounArray.get(index);
            if (hyponym.contains(" ")) {
                String[] hyponyms = hyponym.split(" ");
                for (String h : hyponyms) {
                    if (!hyponymSet.contains(h)) {
                        hyponymSet.add(h);
                    }
                }
            } else {
                if (!hyponymSet.contains(hyponym)) {
                    hyponymSet.add(hyponym);
                }
            }
        }

        // Return the set.
        return hyponymSet;
    }
}
