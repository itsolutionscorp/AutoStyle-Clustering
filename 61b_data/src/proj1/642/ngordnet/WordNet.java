package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;
import java.util.TreeSet;
import java.io.File;
import java.util.Set;

public class WordNet {
    private Digraph digraph;
    private int digraphSize;
    //private HashMap<int, int> numberLinks;
    private HashMap<Integer, TreeSet<String>> wordLinks;
    private TreeSet<String> allWords;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        File synsetFile = new File(synsetFilename);
        In reader = new In(synsetFile);
        String[] synsetInput = reader.readAllLines();
        digraphSize = synsetInput.length;
        digraph = new Digraph(digraphSize); //Set the initial digraph size
       
        //numberLinks = new HashMap<int, int>; //Initiate muh variables
        wordLinks = new HashMap<Integer, TreeSet<String>>(); 
        allWords = new TreeSet<String>();   
        Integer current = 0;
        //int digraphPosition = 0;
        int lineNumber = 0;
        String line;
        String[] splitLine;
        String[] splitLine2;
        TreeSet<String> synset = new TreeSet<String>();        
        while (lineNumber < synsetInput.length) { //Group synsets together, map them to a vertex
            line = synsetInput[lineNumber];
            splitLine = line.split(",");
            current = Integer.valueOf(splitLine[0]);
            splitLine2 = splitLine[1].split(" ");
            for (String s : splitLine2) { //For every String in the 2nd part
                synset.add(s);
                allWords.add(s);
            }
            wordLinks.put(current, synset); //Map the number to the synset
            lineNumber += 1;
            synset = new TreeSet<String>(); //Change the reference of synset
        }
            
        File hyponymFile = new File(hyponymFilename);
        In hypoReader = new In(hyponymFile);
        int diagraphVertex; 
        while (hypoReader.hasNextLine()) { //Assign edges
            line = hypoReader.readLine();
            splitLine = line.split(",");
            diagraphVertex = Integer.valueOf(splitLine[0]); 
            for (int j = 1; j < splitLine.length; j++) { //Subsequent numbers give the children
                digraph.addEdge(diagraphVertex, Integer.valueOf(splitLine[j])); 
            }
        }               
    }
            

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (allWords.contains(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allWords;
    }
                    

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        if (!isNoun(word)) {
            return null;
        }
        Set<String> hyposyno = new TreeSet<String>();
        TreeSet<String> currentSet;
        TreeSet<Integer> ancestors = new TreeSet<Integer>();
        for (int i = 0; i < digraphSize; i++) {
            currentSet = wordLinks.get(i);
            if (currentSet.contains(word)) {
                for (String s : currentSet) {
                    if (!s.equals(word)) {
                        hyposyno.add(s);
                    }
                }
                ancestors.add(i);
            }
        }
        Set<Integer> hyponyms = GraphHelper.descendants(digraph, ancestors);
        for (int k : hyponyms) {
            currentSet = wordLinks.get(k);
            for (String s : currentSet) {
                hyposyno.add(s);
            }
        }
        return hyposyno;
    }
}
