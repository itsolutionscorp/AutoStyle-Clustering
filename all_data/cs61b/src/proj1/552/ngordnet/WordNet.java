package ngordnet;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Digraph connections;
    private ArrayList<String> synsets;
    private TreeSet<String> allWords = new TreeSet<String>();

    // Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new ArrayList<String>();
        String delimComma = ",";

        // Deal with synsets
        // Text file format: One line. Spaces separate words, commas separate
        // word content
        In synsetInput = new In(synsetFilename);

        while (!synsetInput.isEmpty()) {
            String[] line = synsetInput.readLine().split("[,]+");
            synsets.add(line[1]);
            String[] contentLength = line[1].split("\\s+");
            if (contentLength.length > 1) {
                for (String x : contentLength) {
                    allWords.add(x);
                }
            } else {
                allWords.add(line[1]);
            }
        }

        connections = new Digraph(synsets.size()); // one vertex per word

        // Deal with hyponyms
        In hyponymInput = new In(hyponymFilename);
        while (hyponymInput.hasNextLine()) {
            String line = hyponymInput.readLine();
            String[] numberList = line.split(delimComma);
            int first = Integer.parseInt(numberList[0]);
            for (int i = 1; i < numberList.length; i += 1) {
                connections.addEdge(first, Integer.parseInt(numberList[i]));
            }
        }

    }

    /*
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        // Accounts for words with multiple meanings. Example: jump leap and
        // jump increase
        Set<Integer> indexSet = new TreeSet<Integer>();
        for (int i = 0; i < synsets.size(); i += 1) {
            if (synsets.get(i).contains(word)) {
                // This long procedure is here instead of simply indexSet.add(i)
                // to account for, say preteenager containing teenager, but not
                // being a hyponym of it
                String[] differentWords = synsets.get(i).split("\\s+");
                boolean wordPresent = false;
                for (String everyWord : differentWords) {
                    if (everyWord.equals(word)) {
                        wordPresent = true;
                    }
                }
                if (wordPresent) {
                    indexSet.add(i);
                }
            }
        }

        Set<Integer> hyponymIndicies = GraphHelper.descendants(connections, indexSet);
        Set<String> hyponymWords = new TreeSet<String>();
        for (Integer i : hyponymIndicies) {
            String synsetWord = synsets.get(i);
            String[] lengthChecker = synsetWord.split("\\s+");
            if (lengthChecker.length > 1) {
                for (String x : lengthChecker) {
                    hyponymWords.add(x);
                }
            } else {
                hyponymWords.add(synsetWord);
            }
        }
        return hyponymWords;

    }

    public boolean isNoun(String noun) {
        return allWords.contains((Object) noun);
    }

    public Set<String> nouns() {
        return allWords;
    }
}
