package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.ArrayList;

public class WordNet {
    private In sFile;
    private In hFile;
    private Set<String> nounSet;
    private Map<Integer, String> map1; 
    private Map<String, ArrayList<Integer>> map2; 

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        sFile = new In(synsetFilename);
        hFile = new In(hyponymFilename);
        // inttoword = new HashMap<Integer, ArrayList<String>>()
        map1 = new HashMap<Integer, String>();
        map2 = new HashMap<String, ArrayList<Integer>>();

        nounSet = new HashSet<String>();
        while (sFile.hasNextLine()) {  //while there's still another line
            String currLine = sFile.readLine();  //the curr line
            Scanner sL = new Scanner(currLine).useDelimiter(",\\s*");
            int id = sL.nextInt();
            String lineNouns = sL.next();  //the line's synset of nouns
            map1.put(id, lineNouns);
            Scanner sN = new Scanner(lineNouns);
        
            while (sN.hasNext()) {
                String word = sN.next(); 
                if (!map2.containsKey(word)) {  //new key
                    ArrayList<Integer> arr = new ArrayList<Integer>();
                    arr.add(id);
                    map2.put(word, arr);
                } 
                map2.get(word).add(id);  //old key: add the new ID as another arraylist value
                nounSet.add(word);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nounSet.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nounSet;    
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> set = new HashSet<String>();

        Digraph g = new Digraph(map1.size());
        Set<Integer> wordInt = new TreeSet<Integer>();  //ID(s) we want
        ArrayList<Integer> intArr = map2.get(word);

        if (intArr.size() > 1) {
            for (int x : intArr) { 
                wordInt.add(x);   //multiple IDs of word
            }
        } else if (intArr.size() == 1) {
            int wordID = intArr.get(0); //single ID of word parameter
            wordInt.add(wordID);
        }

        while (hFile.hasNextLine()) {  //while there's still another line
            String currLine = hFile.readLine();  //the curr line
            Scanner sL = new Scanner(currLine).useDelimiter(",\\s*");  
            int id = sL.nextInt();
            while (sL.hasNextInt()) {
                g.addEdge(id, sL.nextInt());
            }
        }

        for (int num : GraphHelper.descendants(g, wordInt)) {
            String key = map1.get(num);
            Scanner hyps = new Scanner(key);
            while (hyps.hasNext()) {
                set.add(hyps.next());
            }
        }
        return set;
    }
}
