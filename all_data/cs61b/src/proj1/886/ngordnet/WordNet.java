package ngordnet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.StringTokenizer;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;


/** WordNet Class inside ngordnet package.
 *  @author John Sebastian Ospina */

public class WordNet {
    private In synset;
    private In hyponym;
    private Map<Integer, Set<String>> synsetMap1;
    private Map<String, Set<Integer>> synsetMap2;
    private Digraph hyponymDigraph;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new In(synsetFilename);
        hyponym = new In(hyponymFilename);
        synsetMap1 = new HashMap<Integer, Set<String>>(); //ID to synonyms.
        synsetMap2 = new HashMap<String, Set<Integer>>(); //Word to IDs.
        
        //String tokenizer from stack overflow.
        int i = 0;
        while (synset.hasNextLine() && synset != null) {
            String synsetLine = synset.readLine(); 
            //Line is "###,synonyms,description".//
            StringTokenizer synsetTok = new StringTokenizer(synsetLine, ","); 
            //Splitting the string line at the ",".//
            int id = Integer.parseInt(synsetTok.nextToken()); 
            //Only the "###". 
            StringTokenizer synonymsTok = new StringTokenizer(synsetTok.nextToken(), " "); 
            //Splitting the synonyms at the " ". Only the "synonyms".// 
            Set<String> synonymsSet = new HashSet<String>();
            String hypostring = "Random string name";
            int max = synonymsTok.countTokens();
            for (int x = 0; x < max; x++) {
                Set<Integer> inttoStringSet = new HashSet<Integer>();
                hypostring = synonymsTok.nextToken();
                synonymsSet.add(hypostring); 
                //Placing the synonym strings into the the synonymSet.//
                if (!synsetMap2.containsKey(hypostring)) {
                    synsetMap2.put(hypostring, inttoStringSet);
                }
                synsetMap2.get(hypostring).add(id);
            }
            synsetMap1.put(id, synonymsSet);
            i++;
        }
        
        //String tokenizer from stack overflow.
        hyponymDigraph = new Digraph(i);
        while (hyponym.hasNextLine()) {
            String hyponymLine = hyponym.readLine(); 
            //Line is "hypernym, hyponym # 1, hyponyn # 2, hyponym # n+1".//
            StringTokenizer hyponymTokenized = new StringTokenizer(hyponymLine, ",");
            int hypernym = Integer.parseInt(hyponymTokenized.nextToken()); 
            //Should store the hypernym as the vertice.//
            int numberofhyponyms = hyponymTokenized.countTokens();
            for (int y = 0; y < numberofhyponyms; y++) {
                int hypoid = Integer.parseInt(hyponymTokenized.nextToken());
                hyponymDigraph.addEdge(hypernym, hypoid);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) { 
        return synsetMap2.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return synsetMap2.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
    * WORD. If WORD belongs to multiple synsets, return all hyponyms of
    * all of these synsets. See http://goo.gl/EGLoys for an example.
    * Do not include hyponyms of synonyms.
    */

    public Set<String> hyponyms(String word) {
        Set<Integer> wordintidSet = synsetMap2.get(word); 
        //Set of numbers that are ID's of word in synset.
        Set<Integer> setofdescendants = GraphHelper.descendants(hyponymDigraph, wordintidSet); 
        //Set of ID's of the descendants.
        Iterator<Integer> bossiterator = setofdescendants.iterator();
        //Creates an iterator that goes through the set descendant id's
        Set<String> bossSet = new HashSet<String>(); 
        //New set of strings that we want to add to and then return.
        while (bossiterator.hasNext()) {
            Set<String> scrubSet = synsetMap1.get(bossiterator.next());
            Iterator<String> scrubIterator = scrubSet.iterator();
            while (scrubIterator.hasNext()) {
                bossSet.add(scrubIterator.next());
            }
        }
        return bossSet;
    }
}
