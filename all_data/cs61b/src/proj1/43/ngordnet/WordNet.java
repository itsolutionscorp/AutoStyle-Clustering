package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;


/** Tree implementation of WordNet that stores items of type String
*   @author Kelly Cho
*/

public class WordNet {
    private TreeMap<Integer, Synset> tree;

    /** Creates a WordNet using files form SYNSETFILENAME 
        and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        String[] synsetFile = new In(synsetFilename).readAllLines();
        String[] hyponymFile = new In(hyponymFilename).readAllLines();

        TreeMap<Integer, Synset> synsets = new TreeMap<Integer, Synset>(); 
        for (String line : synsetFile) {
            String[] parsedLine = line.split(",");
            int id = Integer.parseInt(parsedLine[0]);
            synsets.put(id, new Synset(parsedLine[1], id));
        }

        for (String arrow : hyponymFile) {
            String[] parsedLine = arrow.split(",");
            int root = Integer.parseInt(parsedLine[0]);
            for (int i = 1; i < parsedLine.length; i++) {
                synsets.get(root).hyponyms.add(
                    Integer.parseInt(parsedLine[i]));
            }
        }
        tree = synsets;
    }

    /** Returns if noun is contained in this WordNet instance */
    public boolean isNoun(String noun) {
        for (Synset wordGroup : tree.values()) {
            if (wordGroup.words.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /** Returns the set of all nouns in this WordNet instance */
    public Set<String> nouns() {
        HashSet<String> nouns = new HashSet<String>();
        for (Synset wordGroup : tree.values()) {
            for (String noun : wordGroup.words) {
                nouns.add(noun);
            }
        }
        return nouns;
    }


    /** Returns the set of all hyponyms of WORD as well as all 
        synonyms of WORD. If WORD belongs to multiple synsets, 
        returns all hyponyms of all of these synsets. 
        Does not include hyponyms of synonyms. */
    public Set<String> hyponyms(String word) {
        HashSet<Integer> wordID = new HashSet<Integer>();
        HashSet<String> hyponyms = new HashSet<String>();
        for (int i = 0; i < tree.size(); i++) {
            if (tree.get(i).words.contains(word)) {
                wordID.add(i);
            }
        }
        for (int i : wordID) {
            for (String noun : tree.get(i).getHyponyms()) {
                hyponyms.add(noun);
            }
        }
        return hyponyms;
    }

    /** Class that represents a synset. Fields contain id#'s of hypernyms
        and hyponyms. Words field contains ArrayList of words. */
    private class Synset {
        private HashSet<String> words = new HashSet<String>();
        private HashSet<Integer> hypernyms = new HashSet<Integer>();
        private HashSet<Integer> hyponyms = new HashSet<Integer>();

        private Synset(String nouns, int id) {
            for (String noun : nouns.split(" ")) {
                this.words.add(noun);
            }
        }

        private HashSet<String> getHyponyms() {
            HashSet<String> result = new HashSet<String>();
            /** adds synonyms to result */
            for (String noun : words) {
                result.add(noun);
            }
            for (int id : hyponyms) {
                Synset wordGroup = tree.get(id);
                /** adds direct hyponyms to result */
                for (String noun : wordGroup.words) {
                    result.add(noun);
                }
                /** checks and adds deeper level hyponyms to result */
                if (wordGroup.hyponyms.size() > 0) {
                    for (int i : wordGroup.hyponyms) {
                        for (String noun : tree.get(i).getHyponyms()) {
                            result.add(noun);
                        }
                    }
                }
            }
            return result;
        }
    }
}
