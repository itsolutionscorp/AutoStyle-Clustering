package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Iterator;

public class WordNet {
    private int lineCount = 0;
    private Set<String> nouns = new HashSet<String>();
    private List<String[]> indexedWords = new ArrayList<String[]>();
    private Digraph vertices;
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetFile = new In(synsetFilename);
        while (synsetFile.hasNextLine()) {
            String synsetLine = synsetFile.readLine();
            String[] synsetLinePieces = synsetLine.split(",");
            int index = Integer.parseInt(synsetLinePieces[0]);
            if (synsetLinePieces[1].contains(" ")) {
                String[] multipleSynsetLine = synsetLinePieces[1].split(" ");
                indexedWords.add(index, multipleSynsetLine);
                for (int i = 0; i < multipleSynsetLine.length; i++) {
                    if (!nouns.contains(multipleSynsetLine[i])) {
                        nouns.add(multipleSynsetLine[i]);
                    }
                }
            } else {
                if (!nouns.contains(synsetLinePieces[1])) {
                    nouns.add(synsetLinePieces[1]);
                }
                String[] indexWordsAdder = {synsetLinePieces[1]};
                indexedWords.add(index, indexWordsAdder);
            } 
            lineCount += 1;
        }

        vertices = new Digraph(lineCount);
        In hyponymFile = new In(hyponymFilename);
        while (hyponymFile.hasNextLine()) {
            String hyponymLine = hyponymFile.readLine();
            String[] hyponymLinePieces = hyponymLine.split(",");
            int[] intHyponymLinePieces = new int[hyponymLinePieces.length];
            for (int j = 0; j < hyponymLinePieces.length; j++) {
                intHyponymLinePieces[j] = Integer.parseInt(hyponymLinePieces[j]);
            }
            for (int k = 1; k < intHyponymLinePieces.length; k++) {
                vertices.addEdge(intHyponymLinePieces[0], intHyponymLinePieces[k]);
            }
        }
    }

    
    public boolean isNoun(String noun) {
        if (nouns.contains(noun)) {
            return true;
        }
        return false;
    }

    public Set<String> nouns() {
        return nouns;
    }
    
    public Set<String> hyponyms(String word) {
        Set<String> hyponyms = new HashSet<String>();
        Set<Integer> numHyponyms = new TreeSet<Integer>();
        List<Integer> hyponymIndexes = new ArrayList<Integer>();
        for (int l = 0; l < indexedWords.size(); l++) {
            String[] element = indexedWords.get(l);
            for (int m = 0; m < element.length; m++) {
                if (word.equals(element[m])) {
                    hyponymIndexes.add(l);
                }
            }
        }

        Iterator<Integer> hyponymIndexesIterator = hyponymIndexes.iterator();
        while (hyponymIndexesIterator.hasNext()) {
            numHyponyms.add(hyponymIndexesIterator.next());
        }
        numHyponyms = GraphHelper.descendants(vertices, numHyponyms);
        Iterator<Integer> descendantsIndexesIterator = numHyponyms.iterator();
        while (descendantsIndexesIterator.hasNext()) {
            String[] finalHyponyms = indexedWords.get(descendantsIndexesIterator.next());
            for (int n = 0; n < finalHyponyms.length; n++) {
                hyponyms.add(finalHyponyms[n]);
            }
        }

        return hyponyms;
    }
}
