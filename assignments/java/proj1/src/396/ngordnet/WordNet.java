package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private In synsets;
    private In hyponyms;
    private HashMap<Integer, String[]> syns;
    private HashMap<Integer, ArrayList<Integer>> hyps;
    private Digraph d;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsets = new In(synsetFilename);
        hyponyms = new In(hyponymFilename);
        //hashmap of synsets, from synset id to the list of strings it corresponds to
        syns = new HashMap<Integer, String[]>();
        //the map from a synset to its hyponyms, retrieved from Hyponym file
        hyps = new HashMap<Integer, ArrayList<Integer>>();
        readSyns(synsets);
        readHyps(hyponyms);
        //the Digraph that creates all the links based on the numbers
        d = new Digraph(syns.size());
        createConnections(hyps);
        //create iterator that goes through the words 
    }

    private void readSyns(In sFile) {
        String line;
        int id;
        String[] syList;
        String rest;
        while (sFile.hasNextLine()) {
            line = sFile.readLine();
            id = Integer.valueOf(line.substring(0, line.indexOf(",")));
            rest = line.substring(line.indexOf(",") + 1);
            syList = rest.split(",")[0].split(" ");
            syns.put(id, syList);
            // for (String s: syList){
            //     System.out.println(id + " " + s);
            // }  
        } 
    }

    private void readHyps(In hFile) {
        String line;
        int cur;
        ArrayList<Integer> hypList;
        String[] temp;
        while (hFile.hasNextLine()) {
            line = hFile.readLine();
            cur = Integer.valueOf(line.substring(0, line.indexOf(",")));
            temp = line.substring(line.indexOf(",") + 1).split(",");
            hypList = new ArrayList<Integer>();
            for (int i = 0; i < temp.length; i++) {
                hypList.add(Integer.valueOf(temp[i]));
            }
            if (hyps.containsKey(cur)) {
                hyps.get(cur).addAll(hypList);
            } else {
                hyps.put(cur, hypList);
            }
        }
    }

    private void createConnections(HashMap<Integer, ArrayList<Integer>> m) {
        for (int w: m.keySet()) {
            for (int h: m.get(w)) {
                d.addEdge(w, h);
            }
        }
    }


    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int id: syns.keySet()) {
            for (String s: syns.get(id)) {
                if (s.equals(noun)) {
                    return true;
                }
            }
        }
        return false;

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> nounList = new HashSet<String>(); 
        for (int id: syns.keySet()) {
            for (String s: syns.get(id)) {
                nounList.add(s);
            }
        }
        return nounList;
    }


    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> idSet = new HashSet<Integer>();
        Set<String> res = new HashSet<String>();
        if (isNoun(word)) {
            for (int id: syns.keySet()) {
                if (Arrays.asList(syns.get(id)).contains(word)) {
                    idSet.add(id);
                    for (String s: syns.get(id)) {
                        //System.out.println(s);
                        res.add(s);
                    }
                }
            }
            for (int desc: GraphHelper.descendants(d, idSet)) {
                for (String hyp: syns.get(desc)) {
                    //System.out.println(hyp);
                    res.add(hyp);
                }
            }
            return res;
        }
        return null;
    }

}
