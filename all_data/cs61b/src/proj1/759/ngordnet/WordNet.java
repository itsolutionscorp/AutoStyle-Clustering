package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import edu.princeton.cs.introcs.In;
import java.util.HashMap;
import java.util.TreeSet;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, String[]> ver = new HashMap<Integer, String[]>();
    private Digraph wordNet;
    private HashMap<String, ArrayList<Integer>> menu = new HashMap<String, ArrayList<Integer>>();
    

    public WordNet(String synsetFilename, String hyponymFilename) {
        In sF = new In (synsetFilename);
        In hF = new In (hyponymFilename);
        
        while (sF.hasNextLine()){
          String[] sNoun=sF.readLine().split(",");
          Integer a=Integer.parseInt(sNoun[0]);
          String[] w=sNoun[1].split(" ");
          ver.put(a,w);
          for (int i=0;i<w.length;i++){
            if (menu.get(w[i])==(null)){
              menu.put(w[i], new ArrayList<Integer> ());
            }
            menu.get(w[i]).add(a);
          }
        }
        wordNet = new Digraph(ver.size());
        while (hF.hasNextLine()){
          String [] sNyms=hF.readLine().split(",");
          Integer b=Integer.parseInt(sNyms[0]);
          for (int i=1; i<sNyms.length;i=i+1){
            wordNet.addEdge(b, Integer.parseInt(sNyms[i]));
          
          }   
        }
      }
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun){
      Set<String> n = nouns ();
    for (String word:n){
      if(word.equals(noun)){
        return true;
      }
    }
    return false;
    }
    

    /* Returns the set of all nouns. */
    public Set<String> nouns(){
      Set<String> nouns= new TreeSet<String>();
      for (int w:ver.keySet()){
        for (String k:ver.get(w)){
          nouns.add(k); 
        }
        
      }
      return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word){
      Set<Integer> list = new TreeSet<Integer>(menu.get(word));
      Set<Integer> ds = GraphHelper.descendants(wordNet, list);
      Set<String> hyponyms = new TreeSet<String>();
      for(int i:ds){
        String[] set = ver.get(i);
        for(String string:set){
          hyponyms.add(string);
        }
      }
      return hyponyms;

    
  }
}
