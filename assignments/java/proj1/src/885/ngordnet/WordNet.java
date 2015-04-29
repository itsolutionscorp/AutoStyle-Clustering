package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Set;


public class WordNet {
    private HashMap<Integer, ArrayList<String>>  map = new  HashMap<Integer, ArrayList<String>>();
    private HashMap<String , ArrayList<Integer>> inverseMap = 
        new  HashMap<String , ArrayList<Integer>>();
    private int numVertices = 0;
    private HashSet<String> nounSet = new HashSet<String>(); 
    private HashSet<String> hypSet  = new HashSet<String>();

    private int testValue;
    private Digraph web;
    private Digraph webTest;

    private Set<Integer> hypNums = new TreeSet<Integer>();
  
    // Fields that are created for testing purposes
  
    private ArrayList<Integer> webNums       = new ArrayList<Integer>();
    private ArrayList<Integer> counterNums   = new ArrayList<Integer>();
    private ArrayList<Character> characterAt =  new ArrayList<Character>();   


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synReader = new In(synsetFilename);
        In hypReader = new In(hyponymFilename);


        while (!synReader.isEmpty()) {
            String line = synReader.readLine();
            mapBuilder(line);
            numVertices += 1;
        }

        inverseMapBuilder();


        web = new Digraph(numVertices);


        while (!hypReader.isEmpty()) {
            String line = hypReader.readLine();
            webBuilder(line);
        }

    }


        // Creates a map with number as key and word as value
    
    private void mapBuilder(String line) {
        String[] splitter = line.split(",");
        Integer key = Integer.valueOf(splitter[0]);
        String[] values = splitter[1].split(" ");
        ArrayList<String> valList = new ArrayList<String>();
        for (String val: values) {
            valList.add(val);
        }
        map.put(key, valList);
    }


    private ArrayList<String> valueParser(String vals) {
        int beginIndex = 0;
        ArrayList<String> dictionaryValues = new ArrayList<String>();
        
        for (int i = 0; i < vals.length(); i++) {
            if (Character.toString(vals.charAt(i)).equals(" ")) {
                dictionaryValues.add(vals.substring(beginIndex, i));
                beginIndex = i + 1;
            }
        }
        dictionaryValues.add((String) vals.substring(beginIndex));
        return dictionaryValues;
    }
        

    private void webBuilder(String line) {
        String[] splitter = line.split(",");
        Integer first = (Integer) Integer.parseInt(splitter[0]);
        for (String split: splitter) {
            if (split != splitter[0]) {
                web.addEdge(first, Integer.parseInt(split));
            }
        }
    }


 

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        int counter = 0;
        while (counter < numVertices) {
            ArrayList<String> checker = map.get(counter);
            if (checker.contains(noun)) {
                return true;
            }
            counter += 1;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Integer counter = 0;
        while (counter < numVertices) {
            ArrayList curr = map.get(counter);
            int max = curr.size();
            int index = 0;
            while (index < max) {
                nounSet.add((String) curr.get(index));
                index += 1;
            }
            counter += 1;
        }
        return nounSet;
    }
        // Very useful to get the synonyms for hyponyms 
        // Very similiar to some of the above methods

    private void inverseMapBuilder() {
        int counter = 0;
        while (counter < numVertices) {
            ArrayList curr = map.get(counter);
            int val = counter;
            int index = 0;
            int size = curr.size();
            String[] keys = new String[size];
            while (index < size) {
                keys[index] = (String) curr.get(index); //typecast?
                index += 1;
            }
            inverseMapHelper(keys, val);
            counter += 1;
        }
    }

    private void inverseMapHelper(String[] keys, int val) {
        for (String key: keys) {
            if (inverseMap.containsKey(key)) { 
                inverseMap.get(key).add(val);
            } else {
                ArrayList<Integer> valueArrayList = new ArrayList<Integer>();
                valueArrayList.add(val);
                inverseMap.put(key, valueArrayList);
            }
        }
    }



    // * Returns the set of all hyponyms of WORD as well as all synonyms of
    //   * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    //   * all of these synsets. See http://goo.gl/EGLoys for an example.
    //   * Do not include hyponyms of synonyms.
      
    public Set<String> hyponyms(String word) {
        HashSet<String> hypoHashSet = new HashSet<String>();
        
        // First Get all the synonyms
        TreeSet<Integer> forDescendants = new TreeSet<Integer>();
        ArrayList<Integer> wordNumList  = inverseMap.get(word);
        for (Integer wordNum : wordNumList) {
            for (String synword : map.get(wordNum)) {
                hypoHashSet.add(synword);
            }
            forDescendants.add(wordNum);
        }
        Set<Integer> hypNumbers = GraphHelper.descendants(web, forDescendants);
        // now get all of the hyponyms using graphHelper
        for (Integer hypNum: hypNumbers) {
            for (String hypWord: map.get(hypNum)) {
                hypoHashSet.add(hypWord);
            }
        }
        return hypoHashSet;    
    }
}
