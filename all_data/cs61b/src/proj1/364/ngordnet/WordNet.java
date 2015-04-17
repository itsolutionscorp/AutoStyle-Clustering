package ngordnet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    private HashMap<Integer, String> numberToSynsets = new HashMap<Integer, String>();
    private HashMap<String, Integer> synsetsToNumber = new HashMap<String, Integer>();
    private Digraph nounsToNumberDiagraph;
    private String[] synsetsStringsArrayList;
    private String[] hyponymStringsArrayList;
    private int synsetsLength;
    private Set<String> hyponymSet;
    private Set<String> setNoun;
    private static int firstEdge;
    private static int secondEdge;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsets = new In(synsetFilename);
        In hyponyms = new In(hyponymFilename);
        synsetsStringsArrayList = synsets.readAllLines();
        hyponymStringsArrayList = hyponyms.readAllLines();
        if (synsetsStringsArrayList != null) {
            synsetsLength = synsetsStringsArrayList.length;
        }
        nounsToNumberDiagraph = new Digraph(synsetsLength);
        synsetsHashmap();
        synsetsHashmapreverse();
        hypoynmDiagraph();
    }

    private void synsetsHashmap() {
        for (String line : synsetsStringsArrayList) {
            String[] thingsInLines = line.split(",");
            int key = Integer.parseInt(thingsInLines[0]);
            String value = thingsInLines[1];
            numberToSynsets.put(key, value);
        }
    }

    private void synsetsHashmapreverse() {
        for (String line: synsetsStringsArrayList) {
            String[] thingsInLines = line.split(",");
            int key = Integer.parseInt(thingsInLines[0]);
            String value = thingsInLines[1];
            synsetsToNumber.put(value, key);
        }
    }

    private void hypoynmDiagraph() {
        for (String line: hyponymStringsArrayList) {
            String[] thingsInLines = line.split(",");
            firstEdge = Integer.parseInt(thingsInLines[0]);
            for (int i = 1; i < thingsInLines.length; i++) {
                secondEdge = Integer.parseInt(thingsInLines[i]);
                nounsToNumberDiagraph.addEdge(firstEdge, secondEdge);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (String val : numberToSynsets.values()) {
            String[] splitval = val.split(" ");
            for (String value : splitval) {
                if (value.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Set<String> nouns() {
        setNoun = new HashSet<String>();
        for (String val : numberToSynsets.values()) {
            String[] splitval = val.split(" ");
            for (String value:splitval) {
                if (isNoun(value)) {
                    setNoun.add(value);
                }
            }
        }
        return setNoun;

    }

    // * Returns the set of all hyponyms of WORD as well as all synonyms of
    //   * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    //   * all of these synsets. See http://goo.gl/EGLoys for an example.
    //   * Do not include hyponyms of synonyms.
    public Set<String> hyponyms(String word) {
        Set<Integer> finalhyponyms = new HashSet<Integer>();
        Set<Integer> adddescendants;
        for (String key : synsetsToNumber.keySet()) {
            String[] splitval = key.split(" ");
            for (String value: splitval) {
                if (value.equals(word)) {
                    int number = synsetsToNumber.get(key);
                    adddescendants = new HashSet<Integer>();
                    adddescendants.add(number);
                    Set<Integer> steptofinalhyponyms =
                        GraphHelper.descendants(nounsToNumberDiagraph, adddescendants);
                    finalhyponyms.addAll(steptofinalhyponyms);
                }
            }
        }
        hyponymSet = new HashSet<String>();
        for (int number : finalhyponyms) {
            String line = numberToSynsets.get(number);
            String[] thingsInLines = line.split(" ");
            for (String thing: thingsInLines) {
                hyponymSet.add(thing);
            }
        }
        return hyponymSet;
    }
}
