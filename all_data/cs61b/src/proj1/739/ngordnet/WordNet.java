package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Set;

public class WordNet {

    private File synsetsFile; 
    private File hyponymsFile;
    private TreeMap<Integer, TreeSet<String>> nounsMap;
    private Digraph hyponymsGraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        // Creates file objects for both synsets and hyponym files
        synsetsFile = new File(synsetFilename);
        hyponymsFile = new File(hyponymFilename);

        // Creates and fills the hyponyms digraph
        hyponymsGraph = new Digraph(synsetsFile.length());
        int hyponymsFileLength = hyponymsFile.length();
        for (int i = 0; i < hyponymsFileLength; i++) {
            String hyponymsLine = hyponymsFile.nextLine();

            // Gets parent Synset (First value in list)
            int firstSynset = Integer.parseInt(hyponymsLine.split(",")[0]);

            // Gets child Synsets (Rest of values in list)
            String[] restSynset = hyponymsLine.split(",", 2)[1].split(",");

            for (String s : restSynset) {
                hyponymsGraph.addEdge(firstSynset, Integer.parseInt(s));
            }
        }

        // Creates and fills the nounsMap
        nounsMap = new TreeMap<Integer, TreeSet<String>>();
        int synsetsFileLength = synsetsFile.length();
        for (int i = 0; i < synsetsFileLength; i++) {
            String nextLine = synsetsFile.nextLine();
            Integer nextLineNounsIndex = Integer.parseInt(nextLine.split(",")[0]);
            String[] nextLineNouns = nextLine.split(",")[1].split(" ");
            TreeSet<String> nextLineNounsArrayList;
            nextLineNounsArrayList = new TreeSet<String>(Arrays.asList(nextLineNouns));
            nounsMap.put(nextLineNounsIndex, nextLineNounsArrayList);
        }
    }

    // Returns true if String noun is found in the nouns msp
    public boolean isNoun(String noun) {
        if (nounsMap.keySet() == null) {
            return false;
        }
        return getIdsFromSynset(noun).size() > 0;
    }

    // Retuurns a set of all the nouns in the nouns map
    public Set<String> nouns() {
        ArrayList<String> returnList = new ArrayList<String>();
        for (Integer i : nounsMap.keySet()) {
            for (String s : nounsMap.get(i)) {
                returnList.add(s);
            }
        }
        Set<String> returnSet = new TreeSet<String>(returnList);
        return returnSet;
    }

    // A function that returns an array of all the hyponyms correspinding to a given synset
    public Set<String> hyponyms(String word) {
        Set<Integer> synsetIds = new TreeSet<Integer>(getIdsFromSynset(word));
        ArrayList<String> returnNouns = new ArrayList<String>();

        // Loops through the descendants and adds their corresponding synsets to the returnNouns
        Set<Integer> synsetDescendants = GraphHelper.descendants(hyponymsGraph, synsetIds);
        for (Integer i : synsetDescendants) {
            for (String s : nounsMap.get(i)) {
                if (!returnNouns.contains(s)) {
                    returnNouns.add(s);
                }
            }
        }

        // Converts returnNouns ArrayList to a Set of strings and returns it
        Set<String> returnSet = new TreeSet<String>(returnNouns);
        return returnSet;
    }

    // A helper function that returns a list of all the indexes that correspond to a given synset
    private ArrayList<Integer> getIdsFromSynset(String synset) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
        for (Integer i : nounsMap.keySet()) {
            for (String s : nounsMap.get(i)) {
                if (s.equals(synset)) {
                    returnList.add(i);
                }
            }
        }
        return returnList;
    }

    // A helper class that returns information on a file (given its path).
    private class File {

        private String filePath;
        private In constFileReader;

        // Constructor sets the filePath and initializes the constFileReader usin In
        public File(String filePathStr) {
            filePath = filePathStr;
            constFileReader = new In(filePath);
        }

        // Returns the path of the current path
        public String path() {
            return filePath;
        }

        // Iterates through the lines of the file
        public String nextLine() {
            return constFileReader.readLine();
        }

        // Resets the iterator process
        public void reset() {
            constFileReader = new In(filePath);
        }

        // Returns the number of lines int the file
        public int length() {
            In fileReader = new In(filePath);
            int count = 0;
            while (fileReader.hasNextLine()) {
                count++;
                fileReader.readLine();
            }   
            return count;
        }
    }
}
