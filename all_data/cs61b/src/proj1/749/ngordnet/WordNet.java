package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/* Digraph g stores relationships between ids
 * Map m stores actual synsets related to
 * each id
 */
public class WordNet {
    private Digraph g;
    private HashMap<Integer, String> m; //maps id to content
    private HashMap<String, HashSet<Integer>> n; //maps content to id

    /* Constructor
     * takes in synsets and hyponyms filenames and
     * constructs a Digraph g from them.
     */
    public WordNet(String sFile, String hFile) {
        String[] lineReader;
        String content;
        String[] contentArr;
        Integer id;
        String line;
        String word;
        int hypoId;
        HashSet<Integer> potId;

        In sInput = new In(sFile);
        In hInput = new In(hFile);

        //read in sFile as a String array, initialize g
        String[] synsets = sInput.readAllLines();
        String[] hyponyms = hInput.readAllLines();
        this.g = new Digraph(synsets.length);
        this.m = new HashMap<Integer, String>(synsets.length);
        this.n = new HashMap<String, HashSet<Integer>>();

        //split each line into an array,
        //put id and content inside m
        for (int i = 0; i < synsets.length; i++) {
            line = synsets[i];
            lineReader = line.split(",");
            id = Integer.parseInt(lineReader[0]);
            content = lineReader[1];
            this.m.put(id, content);
            contentArr = content.split(" ");

            //put content and id(s) in n
            for (int j = 0; j < contentArr.length; j++) {
                word = contentArr[j];
                if (this.n.containsKey(word)) {
                    this.n.get(word).add(id);
                } else {
                    potId = new HashSet<Integer>();
                    potId.add(id);
                    this.n.put(word, potId);
                }
            }
        }

        //split each line into array
        //add edges to g
        for (int i = 0; i < hyponyms.length; i++) {
            line = hyponyms[i];
            lineReader = line.split(",");
            id = Integer.parseInt(lineReader[0]);
            for (int j = 1; j < lineReader.length; j++) {
                hypoId = Integer.parseInt(lineReader[j]);
                this.g.addEdge(id, hypoId);
            }
        }
    }

    //return true if noun is in a synset
    public boolean isNoun(String noun) {
        return (this.n.containsKey(noun));
    }

    //returns set of all hyponyms of word
    public Set<String> hyponyms(String word) {
        //get ids of all synsets word is in
        HashSet<Integer> synIds = this.n.get(word);
        HashSet<Integer> hypoIds = new HashSet<Integer>();
        HashSet<String> hypos = new HashSet<String>();

        String[] hypoArray;

        //use g to find ids of all hyponym synsets
        //store these ids in hypoIds
        for (Integer id : synIds) {
            hypoIds.add(id);
            hypoIds.addAll(GraphHelper.descendants(this.g, synIds));
        }

        //use Ids in hypoIds to find synsets from m
        //split into component words and add to hypos
        for (Integer hid : hypoIds) {
            hypoArray = this.m.get(hid).split(" ");
            for (int i = 0; i < hypoArray.length; i++) {
                hypos.add(hypoArray[i]);
            }
        }
        return hypos;
    }

    //returns set of all nouns
    public Set<String> nouns() {
        return this.n.keySet();
    }
}

