package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Arrays;

public class WordNet {

    private Map<String, ArrayList<Integer>> nouns;
    private Map<Integer, List<String>> nounsNumfront;
    private int countVertices;
    private Digraph digraph;
    
    
    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME */
    public WordNet(String synsetFilename, String hypernymFilename) {
        In synsetRead = new In(synsetFilename);
        In hypoRead1 = new In(hypernymFilename);
        In hypoRead2 = new In(hypernymFilename);

        nouns = new TreeMap<String, ArrayList<Integer>>();
        nounsNumfront = new TreeMap<Integer, List<String>>();
        countVertices = 0;


        while (synsetRead.hasNextLine()) {
            String[] currLine = synsetRead.readLine().split(",");
            List<String> words = Arrays.asList(currLine[1].split(" "));
            for (String x: words) {
                if (nouns.containsKey(x)) {
                    nouns.get(x).ensureCapacity(nouns.get(x).size() + 1);
                    nouns.get(x).add(Integer.parseInt(currLine[0])); 
                } else {
                    ArrayList<Integer> numbers = new ArrayList<Integer>(); 
                    numbers.add(Integer.parseInt(currLine[0]));
                    nouns.put(x, numbers);
                }                 
            }
            nounsNumfront.put(Integer.parseInt(currLine[0]), words); // key: number; value: noun
        }

        while (hypoRead1.hasNextLine()) {
            String[] currLine = hypoRead1.readLine().split(",");
            for (String s: currLine) {
                countVertices += 1;
            }

        }
        digraph = new Digraph(countVertices);
        
        while (hypoRead2.hasNextLine()) {
            String[] eachLine = hypoRead2.readLine().split(",");
            for (int i = 1; i < eachLine.length; i++) {
                digraph.addEdge(Integer.parseInt(eachLine[0]), Integer.parseInt(eachLine[i]));
            }
        }    
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns.keySet();
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        List<Integer> num = nouns.get(word); // convert String to number
        Set<String> result = new TreeSet<String>();
        Set<Integer> hypernym = new TreeSet<Integer>();

        for (Integer h: num) {
            hypernym.add(h);
        }

        Object[] descendants = GraphHelper.descendants(digraph, hypernym).toArray(); // numbers 
        
        
        for (Object x: descendants) {
            List<String> temp = nounsNumfront.get(x);
            for (String y: temp) {
                result.add(y); 
            }             
        }
        

        result.add(word);
        return result;
    }    
}
