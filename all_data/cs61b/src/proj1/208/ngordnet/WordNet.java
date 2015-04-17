package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;



public class WordNet {
    private HashMap<Integer, String> hmap1; //maps id to synset
    private HashMap<String, HashSet<Integer>> hmap2; //maps single word to id
    private Digraph digraph;

    private In synsetIn; //synset file to be read
    private In hyponymIn; //hyponym file to be read

    private int numVertices;
    private Set<String> synsetNouns;
    

    // public static void main(String[] args) {

    // }

    public WordNet(String synsetFilename, String hyponymFilename) {
    /** Constructor creates a WordNet using files from synsetFilename
    and hyponymFilename */
    //need to use digraph methods including:
    //1. Digraph constructor : public Digraph(int V)
    //2. Digraph method : public void addEdge(int v, int w)
        //in.readLine(), string.split(String delimiter),
        //Integer.parseInt(String n) will be useful.
        if (synsetFilename == null || hyponymFilename == null) {
            throw new IllegalArgumentException();
        }
        synsetIn = new In(synsetFilename);
        hyponymIn = new In(hyponymFilename);
        hmap1 = new HashMap<Integer, String>();
        hmap2 = new HashMap<String, HashSet<Integer>>();
        
        //putting synset id and synset words into hmap1
        String synsetcurrLine;
        while ((synsetcurrLine = synsetIn.readLine()) != null) {
            numVertices += 1;
            //putting id/synset into hmap1
            String[] splitedLine = synsetcurrLine.split(",");
            int id = Integer.parseInt(splitedLine[0]);
            String synset = splitedLine[1];
            hmap1.put(id, synset);
            //putting synset word/id into hmap2
            String[] splitSynset = synset.split(" ");
            for (int i = 0; i < splitSynset.length; i += 1) {
                HashSet<Integer> value;
                if (hmap2.get(splitSynset[i]) == null) {
                    value = new HashSet<Integer>();
                    value.add(id);
                    hmap2.put(splitSynset[i], value); 
                } else {
                    hmap2.get(splitSynset[i]).add(id);
                }       
            }
        }
        //use hyponymIn to create the digraph
        digraph = new Digraph(numVertices);
        String hypocurrLine;
        //for loop to check for edges and need to parse int
        while ((hypocurrLine = hyponymIn.readLine()) != null) {
            String[] splitedLine = hypocurrLine.split(",");
            int vertice = Integer.parseInt(splitedLine[0]);
            for (int i = 1; i < splitedLine.length; i += 1) {
                int edge = Integer.parseInt(splitedLine[i]);
                digraph.addEdge(vertice, edge);
            }
        }
    }
    

    public boolean isNoun(String noun) {
    //Returns true if noun is a word in some synset
    //go through hmap and check if NOUN is ever a value in the hmap.
        if (noun == null) {
            return false;
        } 
        return hmap2.containsKey(noun);
    }

    public Set<String> nouns() {
    //returns the set of all nouns
    //go through hmap2 and put all the keys in the synsetNouns set
    //return the set
        synsetNouns = hmap2.keySet();
        return synsetNouns;
    }

    public Set<String> hyponyms(String word) {
    /**Returns the set of all hyponyms of WORD as well as all synonyms
    of WORD. if WORD belongs to multiple synsets, return all hyponyms 
    of all these synsets. See http://goo.gl/EGloys for an example.
    Do not include hyponyms of synonyms. */
        if (word == null) {
            return null;
        }
        Set<String> hyponyms = new HashSet<String>();
        //find the id's of the starting vertices int hmap2
        if (hmap2.get(word) == null) {
            return null;
        }
        Set<Integer> vertices = hmap2.get(word);
        
        //find the id's of the descendents.
        Set<Integer> descendents = GraphHelper.descendants(digraph, vertices);
        vertices.addAll(descendents);


        //Iterator<Integer> iter = vertices.iterator();
        for (Integer i : vertices) {
            // System.out.println("Attempting to add to hyponyms set");
            // System.out.println(hyponyms);
            //Integer currentID = iter.next();
            if (hmap1.containsKey(i)) {
                // System.out.println("currentID" + i);
                // System.out.println("hmap getting " + hmap1.get(5));
                String[] synsetWords = hmap1.get(i).split(" ");
                
                for (int s = 0; s < synsetWords.length; s += 1) {
                    //System.out.println("synsetWords " + synsetWords[s]);
                    hyponyms.add(synsetWords[s]);
                }       
            }
        }

        if (hyponyms == null) {
            return null;
        }
        return hyponyms;
    }
}
