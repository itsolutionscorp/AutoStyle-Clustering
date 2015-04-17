package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Set;

/* 
 * @author Xiaofei Zhou
 */


public class WordNet {
    // synsetMap map the integer index to its corresponding synset
    private TreeMap<Integer, TreeSet<String>> synsetMap;
    // nounSet contains all the nouns in all the synsets
    // one synset may contain multiple nouns
    private TreeSet<String> nounSet;
    // hyponymMap map each synset to its hynonyms
    // both the synset and its hynonyms are represented by their integer index
    private TreeMap<Integer, TreeSet<Integer>> hyponymMap;
    // the number of synsets, it doesn't equals to the element number in nounSet
    private int size;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetMap = new TreeMap<Integer, TreeSet<String>>();
        nounSet = new TreeSet<String>();
        hyponymMap = new TreeMap<Integer, TreeSet<Integer>>();
        size = 0;
        In sFile = new In(synsetFilename);
        In hFile = new In(hyponymFilename);

        while (!sFile.isEmpty()) {
            // read each line of synsetFile
            // then store different synonym elements into nounSet
            // store integer index and its corresponding synonym into synsetMap
            String line = sFile.readLine();
            String[] parts = line.split(",");
            int index = Integer.parseInt(parts[0]);
            String[] s = parts[1].split(" ");
            // use a TreeSet nouns to store different nouns (if probable)
            // in this line's synset
            TreeSet<String> nouns = new TreeSet<String>();
            for (int i = 0; i < s.length; i++) {
                // each i denotes an element in this line's synset
                nouns.add(s[i]);
                nounSet.add(s[i]);
            }
            synsetMap.put(index, nouns);
            this.size += 1;
        }
        
        while (!hFile.isEmpty()) {
            // read each line of hyponymFile
            String line = hFile.readLine();
            String[] parts = line.split(",");
            TreeSet<Integer> hyponyms = new TreeSet<Integer>();
            for (int i = 1; i < parts.length; i++) {
                // add hyponyms, the hyponyms start from the second element
                hyponyms.add(Integer.parseInt(parts[i]));
            }
            // parts[0] is the first element, which means the hypernyms of these hyponyms
            int hypernyms = Integer.parseInt(parts[0]);
            if (hyponymMap.containsKey(hypernyms)) {
                // if the hypernyms already exist, then include its existing hyponyms
                hyponyms.addAll(hyponymMap.get(hypernyms));
            }
            hyponymMap.put(hypernyms, hyponyms);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return this.nounSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return this.nounSet;
    }

    /* given a word, find out the indices of the synonyms containing such word */
    private TreeSet<Integer> getIndex(String word) {
        TreeSet<Integer> indices = new TreeSet<Integer>();
        for (int i = 0; i < this.size; i++) {
            // we need to search each synonyms 
            if (synsetMap.get(i).contains(word)) {
                indices.add(i);
            }
        }
        return indices;
    }

    /* given an index, return all its children */
    private TreeSet<Integer> getChildren(Integer index) {
        TreeSet<Integer> children = new TreeSet<Integer>();
        if (!hyponymMap.containsKey(index)) {
            // no such index in the map, the set children is empty
            return children;
        }
        children.addAll(hyponymMap.get(index));
        for (int i : hyponymMap.get(index)) {
            // recursively got the children of the children
            children.addAll(this.getChildren(i));
        }
        return children;
    }

    /* given the indices, return all the noun elements of the synsets
     * corresponding to those indices */
    private TreeSet<String> indicesToNouns(TreeSet<Integer> indices) {
        TreeSet<String> set = new TreeSet<String>();
        for (int i : indices) {
            set.addAll(synsetMap.get(i));
        }
        return set;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        // indices contains the indices of synsets containing auch word
        TreeSet<Integer> indices = this.getIndex(word);
        // indHyopSyno sontains all the indices of
        // all hyponyms of the word as well as all its synonyms
        TreeSet<Integer> indHypoSyno = this.getIndex(word);
        for (int i : indices) {
            indHypoSyno.addAll(this.getChildren(i));
        }
        // nounHypoSyno contains the noun Strings of
        // all hyponyms of the word as well as all its synonyms
        TreeSet<String> nounHypoSyno = this.indicesToNouns(indHypoSyno);
        return nounHypoSyno;
    }
}
