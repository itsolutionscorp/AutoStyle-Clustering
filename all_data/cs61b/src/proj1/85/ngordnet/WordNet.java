/* Received assistance with Scanner from: http://stackoverflow.com/questions/
13185727/reading-a-txt-file-using-scanner and
http://stackoverflow.com/questions/20934388/
read-file-from-txt-and-split-string-using-comma */

package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class WordNet {
    private edu.princeton.cs.algs4.Digraph digraph;
    private Map<Integer, List<String>> synsets;
    private Map<String, Set<Integer>> map; 

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        map = new HashMap<String, Set<Integer>>();
        synsets = new HashMap<Integer, List<String>>();
        int count = 0;

        try {
            Scanner sc = new Scanner(new File(synsetFilename));
            while (sc.hasNextLine()) {
                count += 1;
                String line = sc.nextLine();
                String[] lst = line.split(",");
                Integer id = Integer.parseInt(lst[0]);
                List<String> words = Arrays.asList(lst[1].split(" "));
                synsets.put(id, words);
                for (String word: words) {
                    if (map.containsKey(word)) {
                        map.get(word).add(id);
                    } else {
                        Set<Integer> ids = new HashSet<Integer>();
                        ids.add(id);
                        map.put(word, ids);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        digraph = new Digraph(count);
        try {
            Scanner sc2 = new Scanner(new File(hyponymFilename));
            while (sc2.hasNextLine()) {
                String line2 = sc2.nextLine();
                String[] lst2 = line2.split(",");
                Integer id2 = Integer.parseInt(lst2[0]);
                for (int i = 1; i < lst2.length; i++) {
                    digraph.addEdge(id2, Integer.parseInt(lst2[i]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
     /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (map.containsKey(noun)) {
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return map.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> res = new HashSet<String>();
        Set<Integer> d = GraphHelper.descendants(digraph, map.get(word));
        for (int i: d) {
            res.addAll(synsets.get(i));
        }
        return res;
    }

    /* returns the synonym set as a list of strings corresponding to id */
    private Set<String> getSynset(List<Integer> id) {
        Set<String> res = new HashSet<String>();
        for (int i: id) {
            res.addAll(synsets.get(i));
        }
        return res;
    }
}
