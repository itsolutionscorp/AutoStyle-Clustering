package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.Set;
import java.util.HashSet;

public class WordNet { 
    private TreeMap<Integer, String[]> synsetMap = new TreeMap<Integer, String[]>();
    private Digraph hyponymDigraph;
    // private String synsetLine;
    // private Integer lineNumber;
    // private Integer hyponymNumberFirst;
    // private Integer hyponymNumberRest;
    // private String[] synsetLineSplit;
    // private String [] wordsSplit;
    // private String[] hyponymLineSplit;
    // private String hyponymLine;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        In hyponymFile = new In(hyponymFilename);
        while (synsetFile.hasNextLine()) {
            String synsetLine = synsetFile.readLine();
            String[] synsetLineSplit = synsetLine.split(",");
            Integer lineNumber = Integer.parseInt(synsetLineSplit[0]);
            String[] wordsSplit = synsetLineSplit[1].split(" ");
            synsetMap.put(lineNumber, wordsSplit);
        }
        In numberOfVert = new In(hyponymFilename);
        int numberoflines = 0;
        while (numberOfVert.hasNextLine()) {
            String readingLine = numberOfVert.readLine();
            String[] numberOfThings = readingLine.split(",");
            numberoflines += numberOfThings.length + 1;
        }
        hyponymDigraph = new Digraph(numberoflines);
        while (hyponymFile.hasNextLine()) {
            String hyponymLine = hyponymFile.readLine();
            String[] hyponymLineSplit = hyponymLine.split(",");
            Integer hyponymNumberFirst = Integer.parseInt(hyponymLineSplit[0]);
            for (int i = 1; i < hyponymLineSplit.length; i++) {
                Integer hyponymNumberRest = Integer.parseInt(hyponymLineSplit[i]);
                hyponymDigraph.addEdge(hyponymNumberFirst, hyponymNumberRest);
            }
        }
    }

    public boolean isNoun(String noun) {
        Set<String> tempstufff = this.nouns();
        if (tempstufff.contains(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        HashSet<String> listOfNouns = new HashSet<String>();
        for (int i = 0; i < synsetMap.size(); i++) {
            String[] tempshit = synsetMap.get(i);
            for (int j = 0; j < tempshit.length; j++) {
                listOfNouns.add(tempshit[j]);
            }
        }
        return listOfNouns;
    }

    public Set<String> hyponyms(String word) {
        Set<String> returnofhyponyms = new HashSet<String>();
        for (int i = 0; i <= synsetMap.size(); i++) {
            String[] temphyponyms = synsetMap.get(i);
            if (temphyponyms != null) {
                for (int j = 0; j < temphyponyms.length; j++) {
                    if (temphyponyms[j].equals(word)) {
                        Set<Integer> wordfound = new HashSet<Integer>();
                        wordfound.add(i);
                        Set<Integer> numbersofhyponyms;
                        numbersofhyponyms = GraphHelper.descendants(hyponymDigraph, wordfound);
                        for (Integer x: numbersofhyponyms) {
                            String[] stringArrayWords = synsetMap.get(x);
                            for (String y: stringArrayWords) {
                                returnofhyponyms.add(y);
                            }
                        }
                    }
                }
            }
        }
        return returnofhyponyms;
    }
}
