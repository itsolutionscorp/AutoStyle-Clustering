package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class WordNet {
    
    private HashMap<String, String> synsetHm = new HashMap<String, String>();
    private String file1, val1;
    private String file2, val2;
    private String[] synLineParts, synsetVals, extraNouns;
    private String[] hypoLineParts, hypoVals;
    private Digraph hypoD;
    private ArrayList<Integer> nums = new ArrayList<Integer>();
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) { 
        file1 = synsetFilename;
        file2 = hyponymFilename;
        In synIn = new In(file1);
        In hypoIn = new In(file2);

        while (synIn.hasNextLine()) {
            //use hashmap to pair key with word
            val1 = synIn.readLine();
            synLineParts = val1.split(",");
            synsetHm.put(synLineParts[0], synLineParts[1]); 
        }

        hypoD = new Digraph(synsetHm.size());
        while (hypoIn.hasNextLine()) {
            val2 = hypoIn.readLine();
            hypoLineParts = val2.split(",");
            hypoD.addEdge(Integer.parseInt(hypoLineParts[0]), Integer.parseInt(hypoLineParts[1]));
            if (hypoLineParts.length > 2) {
                for (int i = 2; i < hypoLineParts.length; i++) { 
                    Integer firstPart = Integer.parseInt(hypoLineParts[0]);
                    Integer otherPart = Integer.parseInt(hypoLineParts[i]);
                    hypoD.addEdge(firstPart, otherPart);
                }
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String value : synsetHm.values()) {
            if (value.contains(" ")) {
                synsetVals = value.split(" ");
                for (String arrVal : synsetVals) {
                    if (arrVal.equals(noun)) {
                        return true;
                    }
                }
            } else {
                if (value.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounSet = new HashSet<String>();

        for (String n : synsetHm.values()) {
            if (n.contains(" ")) {
                extraNouns = n.split(" ");
                for (String s : extraNouns) {
                    nounSet.add(s);
                }
            } else {
                nounSet.add(n);
            }
        }
        return nounSet;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    // Received suggestions from Michael Zhang (bhr) //
    public Set<String> hyponyms(String word) { 
        Set<String> hypSyn = new HashSet<String>();
        Set<Integer> ints = new HashSet<Integer>();
        Set<Integer> des = new HashSet<Integer>();
        String[] splitWords, splitArr;

        for (int i = 0; i < synsetHm.size(); i++) { //iterate through map
            splitWords = synsetHm.get(String.valueOf(i)).split(" "); //get every word and split 
            for (int j = 0; j < splitWords.length; j++) {
                if (splitWords[j].equals(word)) { //get word and add its synonym from split array
                    for (int b = 0; b < splitWords.length; b++) {
                        hypSyn.add(splitWords[b]);
                    }
                    ints.add(i);
                    des = GraphHelper.descendants(hypoD, ints); //get descendants
                    for (int c : des) { 
                        splitArr = synsetHm.get(String.valueOf(c)).split(" ");
                        for (int d = 0; d < splitArr.length; d++) {
                            hypSyn.add(splitArr[d]);
                        }
                    }
                }
            }
        }
        return hypSyn;
    }
}
