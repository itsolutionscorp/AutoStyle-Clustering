package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class WordNet {
    private String synsets;
    private String hypernyms;
    private Map<String, HashSet<Integer>> words = new HashMap<String, HashSet<Integer>>();
    private int v = 0;
    private Digraph digraph;
    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hypernymFilename) {
        this.synsets = synsetFilename;
        this.hypernyms = hypernymFilename;
        In txt1 = new In(synsetFilename);
        In txtCopy = new In(synsetFilename);
        In txt2 = new In(hypernymFilename);
        this.readSynsets(txt1, txtCopy);
        digraph = new Digraph(v);
        this.readHypernyms(txt2);
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
        for (String k : words.keySet()) {
            nouns.add(k);
        }
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> hyponymSet = new HashSet<String>();
        try {
            for (int i : GraphHelper.descendants(digraph, words.get(word))) {
                for (String tempWord : words.keySet()) {
                    if (words.get(tempWord).contains(i)) {
                        hyponymSet.add(tempWord);
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println(word + " is not in the database.");
        }
        return hyponymSet;   
    }

    /** Reads in Synsets. */
    private void readSynsets(In txt, In txtCopy) {
        while (txtCopy.hasNextLine()) { //how many lines does it have?
            v += 1;
            txtCopy.readLine();
        }
        while (txt.hasNextLine()) {
            String[] temp = txt.readLine().split(",");
            int tempValue = Integer.parseInt(temp[0]); //first temp value will have an int
            if (temp[1].contains(" ")) { //second temp value will have one or more words
                String[] temp2 = temp[1].split(" "); //if it has more than one word              
                for (String word : temp2) { //for each word it has
                    if (words.containsKey(word)) { //if this word has already been added
                        words.get(word).add(tempValue); //add the number to its list
                    } else { //if not
                        HashSet<Integer> keys = new HashSet<Integer>(); //make a new set
                        keys.add(tempValue);  //add the value to the set
                        words.put(word, keys); //add the word to the set
                    }
                }
            } else { //if it only has one word
                if (words.containsKey(temp[1])) { //if this word has already been added
                    words.get(temp[1]).add(tempValue); //add this number to its list
                } else if (!words.containsKey(temp[1])) { //if this word hasn't been added
                    HashSet<Integer> keys = new HashSet<Integer>(); //make a new set
                    keys.add(tempValue);  //add the value to the set
                    words.put(temp[1], keys); //add this to the words
                }
            }
        }
    }

    /** Reads in Hypernyms. */
    private void readHypernyms(In txt) {
        while (txt.hasNextLine()) { //checks if txt has another line
            String[] temp = txt.readLine().split(","); //splits txt by commas
            int tempInt = Integer.parseInt(temp[0]); //stores the first word
            for (int i = 1; i < temp.length; i++) {
                digraph.addEdge(tempInt, Integer.parseInt(temp[i]));
            }
        }
    }
}
