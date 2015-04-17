package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import edu.princeton.cs.introcs.In;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
    private Digraph d;
    public WordNet(String synsetFilename, String hyponymFilename) {
        In reader = new In(synsetFilename);
        //Integer last = 0;
        //Integer k = 0;
        //int count = 0;
        while (!reader.isEmpty()) {
            String line = reader.readLine(); 
            ArrayList<String> values = new ArrayList<String>();
            String [] info = line.split(",");
            if (Character.isDigit(line.charAt(0))) {
                int i = Integer.parseInt(info[0]);
                String[] words = info[1].split(" ");
                for (String s: words) {
                    if (map.containsKey(i)) {
                        map.get(i).add(s);
                    } else {
                        values.add(s);
                        map.put(i, values);
                    }
                }
            }
        }
        In hyponyms = new In(hyponymFilename);
        d = new Digraph(map.size());
        while (!hyponyms.isEmpty()) {
            String h = hyponyms.readLine();
            String [] storage = h.split(",");
            Integer root = Integer.parseInt(storage[0]);
            for (String s: storage) {
                Integer vertex = Integer.parseInt(s);
                d.addEdge(root, vertex);
            }
        }
    }     
    
    /*int i = 0;
    String store = "";
    while (i < line.length() && Character.isDigit(line.charAt(i))) {
        store = store.concat(Character.toString(line.charAt(i)));
        i += 1;
    }
    if (i == 0) {
        k = last;
        count = 0;
    } else {
        k = Integer.parseInt(store);
        last = k;
        count = i + 1;
    }
    String val = "";
    boolean split = false;
    int commaCount = 0;
    while (count < line.length() && !split && commaCount < 2) {
        char c = line.charAt(count);
        if (c == ',') {
            split = true;
            commaCount += 1;
        } else if (c != ',') {
            String s = Character.toString(c);
            val = val.concat(s);
        }
        count += 1;
    }
    if (map.containsKey(k)) {
        map.get(k).add(val);
    } else {
        values.add(val);
        map.put(k, values);
    }
}   
    
    Integer root = 0;
    boolean setRoot = false;
    int x = 0;
    while (x < h.length()) {
        char c = h.charAt(x);
        if (c == ',' && !setRoot) {
            setRoot = true;
            root = Integer.parseInt(index);
            index = "";
        } 
        else if (c == ',' && setRoot) {
            Integer vertex = Integer.parseInt(index);
            d.addEdge(root,vertex);
            index = "";
        } 
        else if ((x == h.length()-1) && setRoot) {
            String s = Character.toString(c);
            index = index.concat(s);
            Integer vertex = Integer.parseInt(index);
            d.addEdge(root,vertex);
            index = "";
        }
        else if (c != ',') {
            String s = Character.toString(c);
            index = index.concat(s);
        }
        x += 1;
    }*/

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer k: map.keySet()) {
            for (String s: map.get(k)) {
                if (s.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> n = new HashSet<String>();
        for (Integer k: map.keySet()) {
            for (String s: map.get(k)) {
                if (!n.contains(s)) {
                    n.add(s);
                }
            }
        }
        return n;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        Set<Integer> set = new HashSet<Integer>();
        for (Integer k: map.keySet()) {
            for (String s: map.get(k)) {
                if (word.equals(s)) {
                    set.add(k);
                }
            }
        }
        Set<Integer> vertices = GraphHelper.descendants(d, set);
        Set<String> hyponyms = new HashSet<String>();
        for (Integer v: vertices) {
            for (String s: map.get(v)) {
                hyponyms.add(s); 
            }
        }
        return hyponyms;
    }
    
    /*public static void main (String[] args) {
        WordNet wn = new WordNet("./p1data/wordnet/synsets11.txt",
                "./p1data/wordnet/hyponyms11.txt");
        System.out.println(wn.hyponyms("increase"));
    }*/
}
