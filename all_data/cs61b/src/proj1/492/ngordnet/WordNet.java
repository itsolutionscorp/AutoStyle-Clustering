package ngordnet;
import edu.princeton.cs.algs4.Digraph;
//import java.util.Set;
//import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Set;

public class WordNet {
    private TreeSet<String> allNouns = new TreeSet<String>();
    //diagraph for storing is-a relations
    private Digraph relations;
    //map for storing vertex-synset relations
    private HashMap<Integer, String> mapOfEntries;
    //number of hyper-(hyponym set) relations 
    private int numRelations;
    //number of vertices
    private int numVertices;
    public WordNet(java.lang.String synsetFilename, java.lang.String hyponymnFilename) {
        //read in the synset file
        String[] synsetContent = readFile(synsetFilename);
        //in order to isolate the synsets from the string
        String[] splitSyns;
        //number of vertices
        numVertices = synsetContent.length;
        //create hashmap for storing synsets at given indices
        mapOfEntries = new HashMap<Integer, String>();
        //read in the hyponym file
        String[] hypContent = readFile(hyponymnFilename);
        //in order to isolate the hyponyms from the hypernyms
        String [] splitHyp;
        //number of hyper-(hyponym set) relations 
        numRelations = hypContent.length;
        //create the diagraph
        relations = new Digraph(numVertices);
        int i = 0;
        //fills in the HashMap with indices and corresponding hyponyms
        while (i < numVertices) {
            //creates an array of the strings in the line, around the commas
            splitSyns = synsetContent[i].split(",");
            //add (to the HashMap) the synset at the index 
            mapOfEntries.put(i, splitSyns[1]);
            //add the nouns to allNouns
            String [] nounArray = splitSyns[1].split(" ");
            for (int x = 0; x < nounArray.length; x++) {
                //tree set will only add one of each noun, so don't check if it's already there
                allNouns.add(nounArray[x]);
            }
            //increment counter
            i++;
        }
        int j = 0;
        //fills in the Diagraph with is-a relationships
        while (j < numRelations) {
            //hyponyms start at index 1... the 0th index is the hypernym
            int hypIndex = 1;
            splitHyp = hypContent[j].split(",");
            while (hypIndex < splitHyp.length) {
                //0th index is the hypernym and the rest are hyponyms
                int firstVert = Integer.parseInt(splitHyp[0]);
                relations.addEdge(firstVert, Integer.parseInt(splitHyp[hypIndex]));
                hypIndex++;   
            }
            j++;
        }
    }
    public Set<String> hyponyms(java.lang.String word) {
        int k = 0;
        //to store the hypernym vertices... i.e., the vertices where "word" lives
        TreeSet<Integer> verticesOfWord = new TreeSet<Integer>();
        //getting the vertices of all synsets in which "word" belongs
        while (k < numVertices) {
            if (isNoun(mapOfEntries.get(k)) && mapOfEntries.get(k).equals(word)) {
                verticesOfWord.add(k);
            } else {
                String[] nounsOfSynset = mapOfEntries.get(k).split(" ");
                for (int x = 0; x < nounsOfSynset.length; x++) {
                    if (nounsOfSynset[x].equals(word)) {
                        verticesOfWord.add(k);
                    }
                }
            }
            k++;
        }
        //to store the hyponym vertices
        Set<Integer> hyponymVertices = GraphHelper.descendants(relations, verticesOfWord);
        TreeSet<String> synAndHyp = new TreeSet<String>();
        for (Integer element: hyponymVertices) {
            String[] splitHyp = mapOfEntries.get(element).split(" ");
            for (int x = 0; x < splitHyp.length; x++) {
                synAndHyp.add(splitHyp[x]);
            }
            //synAndHyp.add(mapOfEntries.get(element));
        }
        int j;
        String[] synonyms;
        while (!verticesOfWord.isEmpty()) {
            j = verticesOfWord.first();
            synonyms = mapOfEntries.get(j).split(" ");
            for (int x = 0; x < synonyms.length; x++) {
                synAndHyp.add(synonyms[x]);
            }
            verticesOfWord.remove(j);
        }
        return synAndHyp;
    }
    //this works correctly
    public boolean isNoun(java.lang.String noun) {
        //true if the word is a noun
        return nouns().contains(noun);
    }
    //this works correctly
    public Set<String> nouns() {
        return allNouns;
        
    }
    //this works correctly
    private String[] readFile(java.lang.String filename) {
        //read in the file
        In asAStream = new In(filename);
        String[] asAnArray = asAStream.readAllLines();
        //return the "read" file
        return asAnArray;
    }
}
