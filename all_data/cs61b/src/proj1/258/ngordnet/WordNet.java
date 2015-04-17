
package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

public class WordNet {
    private In synsetsFile;
    private In hyponymsFile;
    private int synsetsLength;
    private int hyponymsLength; //also known as the number of vertices for digraph
    private Map<Integer, ArrayList<String>> synsets = new HashMap<Integer, ArrayList<String>>();
    private Digraph hyponyms;

    public WordNet(String synsetFilename, String hyponymFilename) {

        String[] synsetLines = (new In(synsetFilename)).readAllLines();
        synsetsLength = synsetLines.length; 
        String[] hyponymLines = (new In(hyponymFilename)).readAllLines();
        hyponymsLength = hyponymLines.length;
        synsetsFile = new In(synsetFilename);
        hyponymsFile = new In(hyponymFilename);
        createSynsets(synsetsFile);
        createDigraph(hyponymsFile);

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < synsets.size(); i++) {
            ArrayList<String> nouns = new ArrayList<String>();
            nouns = synsets.get(i);
            for (int x = 0; x < nouns.size(); x++) {
                if (noun.equals(nouns.get(x))) {
                    return true;
                }
            } 
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nouns = new HashSet<String>();
        for (int i = 0; i < synsets.size(); i++) {
            List<String> values = new ArrayList<String>();
            values = synsets.get(i);
            for (int x = 0; x < values.size(); x++) {
                nouns.add(values.get(x));
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
        Set<String> hyponymsSet = new HashSet<String>();
        ArrayList<String> allDemHyposAndSyns = new ArrayList<String>();
        allDemHyposAndSyns = getHyponymsAndSynonyms(word);
        for (String words : allDemHyposAndSyns) {
            hyponymsSet.add(words);
        }
        return hyponymsSet;
    }

    private ArrayList<String> getHyponymsAndSynonyms(String word) {
        ArrayList<String> allDemHyposAndSyns = new ArrayList<String>();
        for (int i = 0; i < synsetsLength; i++) {
            ArrayList<String> key = new ArrayList<String>();
            key = synsets.get(i);
            String keyString = "";
            for (String s : key) {
                keyString += s + " ";
            }
            String[] valueArray = keyString.split(" ");
            for (int v = 0; v < valueArray.length; v++) {
                if (valueArray[v].equals(word)) {
                    allDemHyposAndSyns.addAll(key);
                    Set<Integer> number = new TreeSet<Integer>();
                    number.add(i);
                    Set<Integer> hyponymDescendants = new TreeSet<Integer>();
                    hyponymDescendants = GraphHelper.descendants(hyponyms, number);
                    for (Integer intValue : hyponymDescendants) { 
                        ArrayList<String> values = new ArrayList<String>();
                        values = synsets.get(intValue);
                        String keyString2 = "";
                        for (String value : values) {
                            keyString2 += value + " ";
                        }
                        allDemHyposAndSyns.addAll(values);
                        allDemHyposAndSyns.addAll(getHyponymsAndSynonyms(keyString2));
                    }
                }
            }
        }
        return allDemHyposAndSyns;
    }

    private void createSynsets(In synset) {
        for (int i = 0; i < synsetsLength; i++) {
            ArrayList<String> values = new ArrayList<String>(); 
            String line = synset.readLine();
            Integer key = 0;
            String[] rawLines = line.split(",");
            key = Integer.parseInt(rawLines[0]);
            String value = rawLines[1];
            String[] valueArray = value.split(" ");
            for (int x = 0; x < valueArray.length; x++) {
                values.add(valueArray[x]);
            }
            synsets.put(key, values);
        }
    }

    private void createDigraph(In hyponym) {
        hyponyms = new Digraph(synsetsLength);
        for (int i = 0; i < hyponymsLength; i++) {
            String line = hyponym.readLine();
            String[] numbersString = line.split(",");
            Integer[] numbers = new Integer[numbersString.length];
            for (int number = 0; number < numbersString.length; number++) {
                numbers[number] = Integer.parseInt(numbersString[number]);
            }
            Integer key = numbers[0];
            for (int value = 1; value < numbers.length; value++) {
                hyponyms.addEdge(key, numbers[value]);
            }
        }
    }
} 
