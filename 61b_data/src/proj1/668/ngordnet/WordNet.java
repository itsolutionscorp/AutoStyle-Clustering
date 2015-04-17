package ngordnet;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private Map<Integer, Set<String>> synsetmap;
    private Map<String, Set<Integer>> synsetmapreverse;
    private Digraph hyponymGraph;
    private In synset;
    private In hyponym;
    public WordNet(String synsetFilename, String hyponymFilename) {
        synset = new In(synsetFilename);
        hyponym = new In(hyponymFilename);
        synsetmap = new HashMap<Integer, Set<String>>();
        synsetmapreverse = new HashMap<String, Set<Integer>>();
        //I discussed ideas with Fadi, Kevin, Sebastian
        // We did not copy code I state there names here to show
        // That our code may be similar since we discussed ideas.
        int hypcount = 0;
        while (synset.hasNextLine()) {
            String synsetLine = synset.readLine();
            StringTokenizer tokenizedstring = new StringTokenizer(synsetLine, ",");
            int synsId = Integer.parseInt(tokenizedstring.nextToken()); //hold's Id of synset
            StringTokenizer synonymsTokenizer;
            synonymsTokenizer = new StringTokenizer(tokenizedstring.nextToken(), " ");
            Set<String> synonyms = new HashSet<String>(); // set of synonyms for synsetmap
            int max = synonymsTokenizer.countTokens();
            String multisyn = "unimportant";
            for (int iter = 0; iter < max; iter++) {
                Set<Integer> intsinHyp = new HashSet<Integer>(); // set of int's for reverse
                multisyn = synonymsTokenizer.nextToken(); // the synonym
                synonyms.add(multisyn); // for synsetmap
                if (!synsetmapreverse.containsKey(multisyn)) { //if multisyn not in reverse
                    synsetmapreverse.put(multisyn, intsinHyp);
                }
                synsetmapreverse.get(multisyn).add(synsId); // at multisyn add the id
            }
            synsetmap.put(synsId, synonyms);
            hypcount++;
            
        }
        hyponymGraph = new Digraph(hypcount);
        while (hyponym.hasNextLine()) {
            String hyponymLine = hyponym.readLine();
            StringTokenizer hyponymsTokenized = new StringTokenizer(hyponymLine, ",");
            int hyperId = Integer.parseInt(hyponymsTokenized.nextToken());
            int max = hyponymsTokenized.countTokens();
            for (int iter = 0; iter < max; iter++) {
                int hypoId = Integer.parseInt(hyponymsTokenized.nextToken());
                hyponymGraph.addEdge(hyperId, hypoId);  
            }

        }
    }

    public boolean isNoun(String noun) {
        return synsetmapreverse.containsKey(noun);
    }

    public Set<String> hyponyms(String word) { // returns set of all hypnonyms of word
        if (!synsetmapreverse.containsKey(word)) {
            throw new IllegalArgumentException("This word is not in data.");
        }
        Set<Integer> synsetSet = synsetmapreverse.get(word);
        Set<Integer> descendantsSet = GraphHelper.descendants(hyponymGraph, synsetSet);
        Iterator<Integer> iter = descendantsSet.iterator();
        Set<String> megaWordSet = new HashSet<String>();
        while (iter.hasNext()) {
            Set<String> wordSet = synsetmap.get(iter.next());
            Iterator<String> anotherIter = wordSet.iterator();
            while (anotherIter.hasNext()) {
                megaWordSet.add(anotherIter.next());
            }
        }
        return megaWordSet;
    }
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */

    public Set<String> nouns() { //returns set of nouns
        return synsetmapreverse.keySet();
    }

    private static void main(String[] args) {

    }
    
}
