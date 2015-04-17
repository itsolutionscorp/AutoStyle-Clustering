package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;

/* Wordnet Class to store words and their relationships 
 * in a directed graph. @author Etan Khanal */

public class WordNet {
    private Set<String> nounSet;
    private TreeMap<String, Set<Integer>> intMappings;
    private Digraph basicNet;
    private TreeMap<Integer, Set<String>> wordMappings;
    private In synsetFile;
    private In hyponymsFile;

    public WordNet(String synsetFilename, String hyponymFilename) {
        //Constructor for wordnet
        this.synsetFile = new In(synsetFilename);
        this.hyponymsFile = new In(hyponymFilename);
        this.nounSet = new HashSet<String>();
        this.wordMappings = new TreeMap<Integer, Set<String>>();
        this.intMappings = new TreeMap<String, Set<Integer>>();
        int wordIndex;
        while (this.synsetFile.hasNextLine()) { //loops through the file for each line
            String[] allSynonyms = synsetFileReader(synsetFile.readLine()); 
            wordIndex = Integer.parseInt(allSynonyms[0]);
            for (int i = 1; i < allSynonyms.length; i++) { //loops through each set of synsets
                String w =  allSynonyms[i];
                this.nounSet.add(w); //adds each word to the nounset
                if (this.intMappings.containsKey(w)) { //if map contains w , add index
                    this.intMappings.get(w).add(wordIndex);
                } else if (!this.intMappings.containsKey(w)) {
                    Set<Integer> tempIndexSet = new HashSet<Integer>(); //otherwise make a new Set
                    tempIndexSet.add(wordIndex); 
                    this.intMappings.put(w, tempIndexSet); //and add it to the map
                } 
                if (wordMappings.containsKey(wordIndex)) { 
                    this.wordMappings.get(wordIndex).add(w); //then add w 
                } else  if (!this.wordMappings.containsKey(wordIndex)) {
                    Set<String> tempWordSet = new HashSet<String>(); // 
                    tempWordSet.add(w);
                    this.wordMappings.put(wordIndex, tempWordSet);
                }
            }
        }
        //Creates the graph
        this.basicNet = new Digraph(wordMappings.size());
        String[] hypoSubset;
        int vertice;
        while (this.hyponymsFile.hasNextLine()) {
            hypoSubset = hyponymFileReader(this.hyponymsFile.readLine());
            vertice =  Integer.parseInt(hypoSubset[0]);
            int j = 1;
            while (j < hypoSubset.length) {
                this.basicNet.addEdge(vertice, Integer.parseInt(hypoSubset[j]));
                j += 1;
            }
        }
    }

    public Set<String> hyponyms(String wd) {
        //Returns all hyponyms of a given word
        Set<String> descendentSet = new HashSet<String>(); 
        Set<Integer> synsetIdentifactionOfWd = intMappings.get(wd); 
        Set<Integer> descendentMappings; //set of all ID's of descendants
        Set<Integer> tempDescendents =
            ngordnet.GraphHelper.descendants(basicNet, synsetIdentifactionOfWd); 
        for (int i : tempDescendents) { //and then for each Descendant, 
            for (String s : wordMappings.get(i)) {
                descendentSet.add(s);
            } //get the word it represents + add to set
        }
        return descendentSet; //return the set
    }
    
    public boolean isNoun(String wd) {
        return nounSet.contains(wd);
    }

    public Set<String> nouns() {
        return nounSet;
    }


    private  static String[] hyponymFileReader(String x) {
        String[] returner = x.split(",");
        return returner;
    }

    private  static String[] synsetFileReader(String x) {
        String[] temp;
        String y = x;
        y = y.substring(y.indexOf(",") + 1, y.length());
        y = y.substring(0, y.indexOf(","));
        temp = y.split(" ");
        String[] returner = new String[temp.length + 1];
        returner[0] = x.substring(0, x.indexOf(","));
        System.arraycopy(temp, 0, returner, 1, returner.length - 1);
        return returner;
    }

}
