package ngordnet; 
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Scanner;
import java.util.Set;
import com.google.common.collect.Multimap;
import com.google.common.collect.ArrayListMultimap;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class WordNet {
    private String synsetFilename;
    private String hyponymFilename;
    private In synsets;
    private In hyponyms;
    private int nSLines;
    private int nHLines;
    private int[] ids;
    private String[] nouns;
    private Set<String> allNounSet;
    private Digraph graph;
    private Multimap<String, Integer> inverseMultiMap;
    private Multimap<Integer, String> multiMap;
    public WordNet(String s1, String s2) {
        synsetFilename = s1;
        hyponymFilename = s2;
        nSLines = lineCount(synsetFilename); // count the number of lines in the text file
        nHLines = lineCount(hyponymFilename); // count the number of lines in text file
        synsetScanner(); // scan every line for id and nouns
        makeInverseMultiMap(); // make multimap with string as keys and integer as values
        makeMultiMap(); // make multimap with integer as keys and strings as values
        makeDigraph(); // make a DiGraph with the synset and hyponym files
    }
    private int lineCount(String filename) {
        int nLines = 0;
        In file = new In(filename);
        while (file.readLine() != null) {
            nLines += 1;
        }
        return nLines;
    }
    private void synsetScanner() {
        synsets = new In(synsetFilename);
        ids = new int[nSLines];
        nouns = new String[nSLines];
        for (int i = 0; i < nSLines; i += 1) { // while loop to loop through the file
            String wholeLine = synsets.readLine(); // reads 1 line at a time
            Scanner scan = new Scanner(wholeLine).useDelimiter(",");
            ids[i] = scan.nextInt(); // scanning every id and word into array
            nouns[i] = scan.next(); // scan next string
        }
    }   

    private List<Integer> getIDs(Multimap<String, Integer> map, String noun) {
        return new ArrayList<Integer>(map.get(noun));
    }

    private List<String> getHyponyms(Multimap<Integer, String> map, int id) {
        return new ArrayList<String>(map.get(id));
    }

    private void makeMultiMap() {
        multiMap = ArrayListMultimap.create();
        for (int i = 0; i < nSLines; i += 1) {
            String[] splitNouns = nouns[i].split("\\s+");
            for (int j = 0; j < splitNouns.length; j += 1) {
                multiMap.put(ids[i], splitNouns[j]);
            }
        }
    }

    private void makeInverseMultiMap() {
        inverseMultiMap = ArrayListMultimap.create();
        for (int i = 0; i < nSLines; i += 1) {
            String[] splitNouns = nouns[i].split("\\s+");
            for (int j = 0; j < splitNouns.length; j += 1) { // splitting strings up
                inverseMultiMap.put(splitNouns[j], ids[i]);
            }
        }
    }

    private void makeDigraph() { // by scanning hyponym file
        graph = new Digraph(nSLines);
        hyponyms = new In(hyponymFilename);
        for (int i = 0; i < nHLines; i += 1) {
            String wholeLine = hyponyms.readLine();
            Scanner scan = new Scanner(wholeLine).useDelimiter(",");
            int synsetID = scan.nextInt(); // scanning every id and word into array
            while (scan.hasNextInt()) {
                int hyponymID = scan.nextInt();
                graph.addEdge(synsetID, hyponymID);
            }
        }
    }

    public boolean isNoun(String noun) {
        return inverseMultiMap.containsKey(noun);
    }

    public Set<String> nouns() {
        return inverseMultiMap.keySet();
    }
    
    // discussed the idea of calling hyponyms with Andy Chu
    public Set<String> hyponyms(String word) {
        List<Integer> sIDs = getIDs(inverseMultiMap, word); // get all ids and put into list
        Set<Integer> idsOfWord = new TreeSet<Integer>(); // make empty treeSet
        for (int i : sIDs) { // loop through each id
            idsOfWord.add(i); // put id in set
        }
        Set<Integer> hyponymsIDs = GraphHelper.descendants(graph, idsOfWord); // get all descendants
        List<String> allHs = new ArrayList<String>();
        for (int i : hyponymsIDs) { // loop through hyponym ids
            List<String> hs = getHyponyms(multiMap, i);
            for (String j : hs) {
                allHs.add(j);
            }
        }
        Set<String> allHyponyms = new TreeSet<String>(); // can also use HashSet
        for (String i : allHs) {
            allHyponyms.add(i);
        }
        return allHyponyms;
    }
}
