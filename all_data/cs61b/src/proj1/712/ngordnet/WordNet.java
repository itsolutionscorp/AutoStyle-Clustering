package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import edu.princeton.cs.algs4.Digraph;




public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, TreeSet<String>> x;
    private HashMap<TreeSet<String>, Integer> y;
    private TreeSet<String> inner;
    private int synsetCount = 0;
    private Digraph g;
    private Set<String> nounList;
    public WordNet(String synsetFilename, String hyponymFilename) {
    	In inSynset = new In(synsetFilename);
    	In inHyponym = new In(hyponymFilename);
        nounList = new HashSet<String>();
    	x = new HashMap<Integer, TreeSet<String>>();
        y = new HashMap<TreeSet<String>, Integer>(); 

    	while(inSynset.hasNextLine()){
            inner = new TreeSet<String>();
    		String line = inSynset.readLine(); //reads the line out of the synset
    		String[] splits = line.split(","); //splits the line 
            int id = Integer.parseInt(splits[0]); //grabs ID
            String [] sep = splits[1].split(" ");
            for(String z : sep){
                inner.add(z);
                synsetCount+=1;
                nounList.add(z);
            }
            x.put(id, inner);
            y.put(inner, id);
    	}
        g = new Digraph(synsetCount); //builds digraph
        while(inHyponym.hasNextLine()){
            String hypline = inHyponym.readLine();
            String [] hyps = hypline.split(",");
            for(int i = 0; i< hyps.length-1;i++){
                int first = Integer.parseInt(hyps[0]);
                int second = Integer.parseInt(hyps[i+1]);
                g.addEdge(first, second);
            }
        }
    }
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun){
        if(nounList.contains(noun)){
            return true;
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns(){
        return nounList;
    }
    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word){
        Set<Integer> idSet = new HashSet<Integer>();
        Set<String> hypoSet = new TreeSet<String>();
        for(Map.Entry<Integer, TreeSet<String>> e : x.entrySet()) {
            Integer key = e.getKey();
            TreeSet<String> value = e.getValue();
            if(value.contains(word)){
                idSet.add(key);
            }
        } 
        Set<Integer> grab;
        grab = GraphHelper.descendants(g, idSet);
        Iterator<Integer> j = grab.iterator();
        while(j.hasNext()){
            hypoSet.addAll(x.get(j.next()));
        }
        return hypoSet;
    }
}
