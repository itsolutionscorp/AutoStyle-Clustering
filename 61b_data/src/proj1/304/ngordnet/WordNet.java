/**
*  WordNet contains word fields (different nouns, that have equivalent meaning)
*  and their hypo/hypernym relations. 
*  hypGraph is a directed graph encoding the hypo/hypernym relations.
*  toWordsMap and toIntMap map between (sets of) integers, that function as
*  indices of word fields, and (sets of) strings, because hypGraph can only hold integer vertices.
*  The data is obtained from two txt-files in comma separated value format for each line:
*  Integer index, nouns separated by spaces, glossary of wordfield
*  Integer index of noun, Integer indices of its hyponyms separated by commas
*  @author René Saitenmacher
*/

package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.introcs.In;


public class WordNet {

    private Digraph hypGraph;
    private Map<Integer, Set<String>> toWordsMap;
    private Map<String, Set<Integer>> toIntMap;


    /**
    *  Constructs a WordNet from two txt-files containing nouns and their hypo/hypernym relations
    *
    *  @param synsetFilename    name of txt-file that contains indices, nouns and glossaries
    *  @param hyponymFileName   name of txt-file that contains hypo/hypernym relations 
    *
    */

    public WordNet(String synsetFilename, String hyponymFileName) {
        toWordsMap = new HashMap<Integer, Set<String>>();
        toIntMap = new HashMap<String, Set<Integer>>();
        In syns = new In(synsetFilename);

        while (syns.hasNextLine()) {
            String[] indexWordfieldGlossary = syns.readLine().split(",", 3);
            int index = Integer.parseInt(indexWordfieldGlossary[0]);
            String[] synTemp = indexWordfieldGlossary[1].split("\\s");
            Set<String> wordField = new TreeSet<String>();
            int i = 0;
            while (i < synTemp.length) {
                String word = synTemp[i];
                wordField.add(word);
                if (!toIntMap.containsKey(word)) {
                    toIntMap.put(word, new TreeSet<Integer>());
                }
                toIntMap.get(word).add(index);
                i += 1;
            }
            toWordsMap.put(index, wordField);
        }

        hypGraph = new Digraph(toWordsMap.keySet().size());
        In hypos = new In(hyponymFileName);
        while (hypos.hasNextLine()) {
            String[] hypoTemp = hypos.readLine().split(",");
            int hyper = Integer.parseInt(hypoTemp[0]);
            int j = 1;
            while (j < hypoTemp.length) {
                hypGraph.addEdge(hyper, Integer.parseInt(hypoTemp[j]));
                j += 1;
            }
        }
    }


    /**
    *  Return whether a noun is in the wordnet.
    *
    *  @param noun    noun to be checked
    */

    public boolean isNoun(String noun) {
        return toIntMap.keySet().contains(noun);
    }
    
    /**
    *  Returns all nouns that are in the wordnet.
    */

    public Set<String> nouns() {
        return toIntMap.keySet();
    }

    /**
    *  Returns all hyponyms of a noun including itself.
    *
    *  @param noun    noun, whose hyponyms will be returned
    */

    public Set<String> hyponyms(String noun) {
        Set<String> result = new TreeSet<String>();
        for (int i : GraphHelper.descendants(hypGraph, toIntMap.get(noun))) {
            result.addAll(toWordsMap.get(i));
        }
        return result;
    }
}
