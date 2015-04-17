
package ngordnet;
import edu.princeton.cs.introcs.In;
//import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import edu.princeton.cs.algs4.Digraph;
/** class that puts words in proper placement
 *  @author Kristine lin
 */

public class WordNet {
    private Set<String> nouns; //all nouns
    private Map sets; //map with index as key and set of words as value
    private int size;
    private Digraph hyponyms;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In readsynset = new In(synsetFilename);
        nouns = new TreeSet<String>();
        String synset;
        size = 0;
        sets = new TreeMap();
        while (readsynset.hasNextLine()) {
            synset = readsynset.readLine();
            String[] list = synset.split(",");
            int num = (int) Integer.parseInt(list[0]);
            String[] words = list[1].split(" ");
            Set<String> setOfWords = new TreeSet<String>(); 
            for (int i = 0; i < words.length; i++) {
                nouns.add(words[i]);
                setOfWords.add(words[i]);
            }
            sets.put(num, setOfWords);
            size++;
        }
        In readHyponym = new In(hyponymFilename);
        hyponyms = new Digraph(size);
        String hyponym;
        while (readHyponym.hasNextLine()) {
            hyponym = readHyponym.readLine();
            String[] pointers = hyponym.split(",");
            int origin = Integer.parseInt(pointers[0]);
            for (int i = 1; i < pointers.length; i++) {
                int destination = Integer.parseInt(pointers[i]);
                hyponyms.addEdge(origin, destination);
            }
        }

    }
    // private void putInArray(int num, Words clump){
    //     if (num>setSize){//setSize is too small
    //         Words[] temp = new Words[setSize*10];
    //         for (int i=0; i<max; i++){
    //             temp[i]=sets[i];
    //         }
    //         sets = temp;
    //     }
    //     sets[num]=clump;
    // }
    // Returns true if NOUN is a word in some synset.
    public boolean isNoun(String noun) {
        return nouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> origin = new TreeSet<Integer>();
        for (int i = 0; i < size; i++) {
            Set<String> setInKey = (Set) sets.get(i);
            if (setInKey.contains(word)) {
                origin.add(i);
            }
        }
        Set<Integer> descendInts = GraphHelper.descendants(hyponyms, origin);
        Set<String> descendStrings = new TreeSet<String>();
        Iterator iter = descendInts.iterator();
        while (iter.hasNext()) {
            Set<String> miniSet = (Set) sets.get(iter.next());
            Iterator miniIter = miniSet.iterator();
            while (miniIter.hasNext()) {
                descendStrings.add((String) miniIter.next());
            }
            //descendStrings.add(sets.get(iter.next()));//(String)
        }
        // for(int i=0; i<descendInts.length; i++){
        //     descendStrings.addAll(sets.get(descendInts[i]));
        // }
        return descendStrings;
        //return null;
    }
    
}
