package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.io.IOException; //must have for scanner
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class WordNet {
    private Set<String> allWords;
    private Integer numSynsets;
    private Digraph theDigraph;
    private Map<Integer, String[]> mapOfSynsets;

    /** Creates a WordNet using files form SYNSETFILENAME and ADJFILENAME
     *  journaldev.com/872/java-scanner-class-example-read-file-parse-text-read-from-inputstream
     *  Num Synsets wrong 12 not 11
     */
    public WordNet(String synsetFilename, String hypernymFilename) {
        try {
            Path synsetPath = Paths.get(synsetFilename);
            Path hypernymPath = Paths.get(hypernymFilename);
            Scanner lastLineScanner = new Scanner(synsetPath);
            lastLineScanner.useDelimiter(System.getProperty("line.separator"));
            String lastLine = "";
            while (lastLineScanner.hasNext()) {
                lastLine = lastLineScanner.next();
            }
            String[] lastLineArray = lastLine.split("\\s*,\\s*");
            numSynsets = Integer.parseInt(lastLineArray[0]) + 1;
            allWords = new HashSet<String>();
            mapOfSynsets = new HashMap<Integer, String[]>();
            theDigraph = new Digraph(numSynsets);
            Scanner scanner = new Scanner(synsetPath);
            scanner.useDelimiter(System.getProperty("line.separator"));
            while (scanner.hasNext()) {
                String line = scanner.next();
                Scanner tempScanner = new Scanner(line);
                tempScanner.useDelimiter("\\s*,\\s*");
                int synsetNum = tempScanner.nextInt();
                String synset = tempScanner.next();
                String[] synsetArray = synset.split("\\s* \\s*");
                for (String s : synsetArray) {
                    mapOfSynsets.put(synsetNum, synsetArray);
                    allWords.add(s);
                }
                String synsetDef = tempScanner.next();
            }
            scanner.close();
            Scanner hypernymScanner = new Scanner(hypernymPath);
            hypernymScanner.useDelimiter(System.getProperty("line.separator"));
            while (hypernymScanner.hasNext()) {
                String line = hypernymScanner.next();
                Scanner tempScanner = new Scanner(line);
                tempScanner.useDelimiter("\\s*,\\s*");
                int hypNum = tempScanner.nextInt();
                int numConnections = 0;
                while (tempScanner.hasNext()) {
                    theDigraph.addEdge(hypNum, tempScanner.nextInt());
                }
            }
        } catch (IOException ex) {
            System.out.println("IOException from java.util.Scanner");
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allWords.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allWords;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<Integer> wordsSetOfSynsets = new HashSet<Integer>();
        for (Integer key : mapOfSynsets.keySet()) {
            for (String s : mapOfSynsets.get(key)) {
                if (s.equals(word)) {
                    wordsSetOfSynsets.add(key);
                }
            }
        }
        Set<Integer> myDescendants = GraphHelper.descendants(theDigraph, wordsSetOfSynsets);
        Set<String> result = new HashSet<String>(myDescendants.size());
        for (Integer key : myDescendants) {
            for (String s : mapOfSynsets.get(key)) {
                result.add(s);
            }
        }
        return result;
    }
}
