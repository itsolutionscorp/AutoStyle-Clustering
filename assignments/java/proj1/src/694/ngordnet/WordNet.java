package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Set;

public class WordNet {
  /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<String, Integer[]> reverseSynset = new HashMap<String, Integer[]>();
    private HashMap<Integer, String[]> synsetMap;
    private HashMap<Integer, Integer[]> hyponymMap;
    private Digraph digraph;
    private int totalNouns = 0; 

    public WordNet(String synsetFilename, String hyponymFilename) {
        String synsetFile = synsetFilename;
        String hyponymFile = hyponymFilename;
        In synsetIn = new In(synsetFile);
        In hyponymIn = new In(hyponymFile);
        synsetMap = synsetParser(synsetIn);
        hyponymMap = hyponymParser(hyponymIn);
        digraphConstructor();
    }

  /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns().contains(noun);
    }

  /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> result = new TreeSet<String>();
        for (Integer key : synsetMap.keySet()) {
            for (int i = 0; i < synsetMap.get(key).length; i++) {
                result.add(synsetMap.get(key)[i]);
            }
        }
        return result;
    }
  /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */
    public Set<String> hyponyms(String word) {
        TreeSet<String> hypresult = new TreeSet<String>(); 
        Integer[] synids = reverseSynset.get(word);
        if (synids == null) {
            return hypresult;
        }
        Set<Integer> vertices = new TreeSet<Integer>(Arrays.asList(synids));
        Set<Integer> descendants = GraphHelper.descendants(digraph, vertices);
        Integer[] descendantsIterable = descendants.toArray(new Integer[descendants.size()]);
        for (Integer i : descendantsIterable) {
            for (int k = 0; k < synsetMap.get(i).length; k++) {
                hypresult.add(synsetMap.get(i)[k]);
            }
        }
        return hypresult;
    }

    private void digraphConstructor() {
        digraph = new Digraph(totalNouns); 
        for (Integer key : hyponymMap.keySet()) {
            for (int i = 0; i < hyponymMap.get(key).length; i++) {
                digraph.addEdge(key, hyponymMap.get(key)[i]);
            }
        }
    }


    private HashMap<Integer, String[]> synsetParser(In instance) {
        HashMap<Integer, String[]> result = new HashMap<Integer, String[]>();
        while (instance.hasNextLine()) {
            String line = instance.readLine();
            String[] lineArray = line.split(",");
            Integer intKey = Integer.parseInt(lineArray[0]);
            String[] stringValues = lineArray[1].split(" ");
            totalNouns += stringValues.length;
            for (int i = 0; i < stringValues.length; i++) { //ReverseSynset input Begins
                String stringKey = stringValues[i];
                if (reverseSynset.containsKey(stringKey)) {
                    Integer[] values = reverseSynset.get(stringKey);
                    Integer[] newIntKeys = new Integer[values.length + 1];
                    System.arraycopy(values, 0, newIntKeys, 0, values.length);
                    newIntKeys[values.length] = intKey;
                    reverseSynset.put(stringKey, newIntKeys);
                } else {
                    reverseSynset.put(stringKey, new Integer[]{intKey}); //ReverseSynset input Ends
                }
            }
            result.put(intKey, stringValues);
        } 
        return result;
    }

    private  HashMap<Integer, Integer[]> hyponymParser(In instance) {
        HashMap<Integer, Integer[]> result = new HashMap<Integer, Integer[]>();
        while (instance.hasNextLine()) {
            String line = instance.readLine();
            String[] lineArray = line.split(",");
            int intKey = Integer.parseInt(lineArray[0]);
            Integer[] intList = new Integer[lineArray.length - 1];
            for (int i = 1; i < lineArray.length; i++) {
                intList[i - 1] = Integer.parseInt(lineArray[i]);
            }
            if (result.containsKey(intKey)) {
                Integer[] values = result.get(intKey);
                Integer[] newValues = new Integer[values.length + intList.length];
                System.arraycopy(values, 0, newValues, 0, values.length);
                for (int k = 0; k < intList.length; k++) {  
                    newValues[values.length + k] = intList[k];
                }
                intList = newValues;
            }
            result.put(intKey, intList);
        }
        return result; 
    }
}
