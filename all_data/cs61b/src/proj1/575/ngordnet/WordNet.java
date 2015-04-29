package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import edu.princeton.cs.introcs.In;
import java.util.Arrays;

public class WordNet {
    private HashMap<Integer, TreeSet<String>> vertexToList;
    private Set<String> allNouns;
    private Digraph relationships; 
    private ArrayList<ArrayList<Integer>> numbers;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In in1 = new In(synsetFilename);
        In in2 = new In(hyponymFilename);
        vertexToList = new HashMap<Integer, TreeSet<String>>();
        allNouns = new TreeSet<String>();
        numbers = new ArrayList<ArrayList<Integer>>();
        String temp;
        String[] tempArray;
        Integer vertex;
        TreeSet<String> theTree;

        while (in1.hasNextLine()) {
            temp = in1.readLine();
            tempArray = temp.split(",");
            vertex = Integer.parseInt(tempArray[0]);
            temp = tempArray[1];
            tempArray = temp.split(" ");
            theTree = new TreeSet<String>(Arrays.asList(tempArray));
            allNouns.addAll(theTree);
            vertexToList.put(vertex, theTree);
        }

        Set<Integer> allNumbers = new TreeSet<Integer>();
        ArrayList<Integer> theTree2;
        Integer[] tempArray2;

        while (in2.hasNextLine()) {
            temp = in2.readLine();
            tempArray = temp.split(",");
            tempArray2 = new Integer[tempArray.length];

            for (int i = 0; i < tempArray.length; i += 1) {
                tempArray2[i] = Integer.parseInt(tempArray[i]);
            }

            theTree2 = new ArrayList<Integer>(Arrays.asList(tempArray2));
            allNumbers.addAll(theTree2);
            numbers.add(theTree2);
        }

        int numOfVertex = allNumbers.size();
        relationships = new Digraph(numOfVertex);

        for (ArrayList<Integer> tree: numbers) {
            for (int i = 1; i < tree.size(); i += 1) {
                relationships.addEdge(tree.get(0), tree.get(i));
            }
        }
    }

    /** Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (TreeSet<String> value: vertexToList.values()) {
            if (value.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<String> answer = new TreeSet<String>();
        Set<Integer> vertices = new TreeSet<Integer>();
        Set<Integer> des;

        //Find the word
        for (Map.Entry<Integer, TreeSet<String>> entry: vertexToList.entrySet()) {
            Integer vertex = entry.getKey();
            TreeSet<String> nouns = entry.getValue();
            if (nouns.contains(word)) {
                vertices.add(vertex);
            }
        }

        des = GraphHelper.descendants(relationships, vertices);

        for (Integer vertex: des) {
            answer.addAll(vertexToList.get(vertex));
        }
        return answer;
    }
}










