package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class WordNet {
    private In synsetReader;
    private In hyponymReader;

    private HashSet<String> allNouns; //Set of all Nouns;

    private HashMap<Integer, Set<String>> idToSetOfSynonyms; 

    private HashMap<String, Set<Integer>> wordToSetofAllSynsetsID; 

    private Digraph hyperIDToHypoID; //Map from String to a set of all its synonyms' ID.
    
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        this.synsetReader = new In(synsetFilename);
        this.hyponymReader = new In(hyponymFilename);
        this.allNouns = new HashSet<String>();
        this.idToSetOfSynonyms = new HashMap<Integer, Set<String>>();
        this.wordToSetofAllSynsetsID = new HashMap<String, Set<Integer>>();
        allStructureMutator(); //All data structure except the Digraph mutated;
        this.hyperIDToHypoID = new Digraph(this.allNouns.size());
        digraphDrawer();
    } 

    // this.allNounsGenerator(); //idToSetOfSynonyms and allnouns mutated are ready;
 //    this.wordToSetofAllSynsetsIDMutator();//The huge word to id map is ready;
    
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return allNouns.contains(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return allNouns;    
    }


    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Set<String> hypoAndSynonym;
        hypoAndSynonym = this.setOfHyponyms(this.wordToSetofAllSynsetsID.get(word));
        // for (int i : this.wordToSetofAllSynsetsID.get(word)){
        //     hypoAndSynonym.addAll(this.idToSetOfSynonyms.get(i));
        return hypoAndSynonym;

    }    

    private Set<String> setOfHyponyms(Set<Integer> hyperIDs) {
        Set<String> hyponymsSet = new HashSet<String>();
        for (int hypoID : GraphHelper.descendants(this.hyperIDToHypoID, hyperIDs)) {
            hyponymsSet.addAll(this.idToSetOfSynonyms.get(hypoID));
        }
        return hyponymsSet;
    }


    private void allStructureMutator() {
        String nextLine;
        String[] withoutComma;
        String[] nouns;
        while (this.synsetReader.hasNextLine()) {
            nextLine = this.synsetReader.readLine();
            withoutComma = nextLine.split(",");
            nouns = withoutComma[1].split(" ");
            for (String noun : nouns) {
                this.allNouns.add(noun);
            } //allNouns filled
            Set<String> nounsSet = new HashSet<String>();
            for (String word : nouns) {
                nounsSet.add(word);
            }
            int keyID = Integer.parseInt(withoutComma[0]);
            this.idToSetOfSynonyms.put(keyID, nounsSet); //idToSetOfSynonyms filled;
            
            for (String noun : nouns) {
                if (this.wordToSetofAllSynsetsID.containsKey(noun)) {
                    this.wordToSetofAllSynsetsID.get(noun).add(keyID);
                } else {
                    HashSet<Integer> ids = new HashSet<Integer>();
                    ids.add(keyID);
                    this.wordToSetofAllSynsetsID.put(noun, ids);
                }
            }
        }
        // for (String word : this.allNouns) {
        //     Set<Integer> correspondingIDSet = new HashSet<Integer>();
        //     for (Integer key : this.idToSetOfSynonyms.keySet()) {
        //         if (this.idToSetOfSynonyms.get(key).contains(word)) {
        //             correspondingIDSet.add(key);
        //         }
        //     }
        //     this.wordToSetofAllSynsetsID.put(word, correspondingIDSet);
        // } //The map wordToSetofAllSynsetsID filled;     
    }

    private void digraphDrawer() {
        String nextLine;
        String[] withoutComma;
        while (this.hyponymReader.hasNextLine()) {
            nextLine = this.hyponymReader.readLine();
            withoutComma = nextLine.split(",");
            for (int i = 1; i < withoutComma.length; i++) {
                int upVertex = Integer.parseInt(withoutComma[0]);
                int lowerVertex = Integer.parseInt(withoutComma[i]);
                this.hyperIDToHypoID.addEdge(upVertex, lowerVertex);
            }
        }
    } //Fill in the Digraph;
}
