package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;

public class WordNet {

    private int numVert = 0;
    private ArrayList<Set<Integer>> decs; 
    private String syn;
    private String hyp;
    private Digraph d;
    
    

    public WordNet(String synsetFilename, String hyponymFilename) {
        syn = synsetFilename;
        hyp = hyponymFilename;
        In syns = new In(synsetFilename);
        In hyps = new In(hyponymFilename);
    

        while (syns.hasNextLine()) {
            syns.readLine();
            numVert += 1;
        }
        In syns1 = new In(synsetFilename);
        d = new Digraph(numVert);

        while (hyps.hasNextLine()) {
            String s = hyps.readLine();
            String[] x = s.split(",");
            int E = Integer.parseInt(x[0]);
            for (int i = 1; i < x.length; i++) {
        
                d.addEdge(E, Integer.parseInt(x[i]));
            }
        }
        decs = new ArrayList<Set<Integer>>(numVert);
        for (int i = 0; i < numVert; i++) {
            Set<Integer> x = new TreeSet<Integer>();
            x.add(i);
            decs.add(i, x);
          
        }
   
    }



  
     /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        if (noun.contains(" ")) {
            return false;
        }
        In syns = new In(syn);
        while (syns.hasNextLine()) {
            String s = syns.readLine();
            String[] x = s.split(",");
            if (x[1].contains(" ")) {
                String[] a = x[1].split(" ");
                for (int i = 0; i < a.length; i++) {
                    if (a[i].equals(noun)) {
                        return true;
                    }
                }
                         
            }
            if (x[1].equals(noun)) {
                return true;
            }
        }
        return false;

    }

    /** Returns the set of all nouns. **/
    public Set<String> nouns() {
        In syns = new In(syn);
        Set<String> nouns = new TreeSet<String>();
        while (syns.hasNextLine()) {
            String s = syns.readLine();
            String[] x = s.split(",");
            if (x[1].contains(" ")) {
                String[] a = x[1].split(" ");
                for (int i = 0; i < a.length; i++) {
                    nouns.add(a[i]);
                }
            } else {
                nouns.add(x[1]);
            }
        }
     
        return nouns;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */

    public Set<String> hyponyms(String word) { 
        Set<String> toReturn = new TreeSet<String>();
        In syns = new In(syn);
        In syns1 = new In(syn);
        In syns2 = new In(syn);
        int[] myNum;
        int counter = 0;

        while (syns2.hasNextLine()) {
            String s = syns2.readLine();
            String[] x = s.split(",");
            if (x[1].contains(word)) {
                counter += 1;
            }
        }
        myNum = new int[counter];
        int i = 0;
        
       
        while (syns.hasNextLine() && i < myNum.length) {
            String s = syns.readLine();
            String[] x = s.split(",");     
            if (x[1].contains(word)) {
                myNum[i] = Integer.parseInt(x[0]);
                i += 1;
            }
        }

        
        while (syns1.hasNextLine()) {
            String s = syns1.readLine();
            String[] x = s.split(",");     
            for (int j = 0; j < myNum.length; j++) {
                int fill = Integer.parseInt(x[0]);
                if (GraphHelper.descendants(d, decs.get(myNum[j])).contains(fill)) {
                    if (x[1].contains(" ")) {
                        String[] a = x[1].split(" ");
                        for (int k = 0; k < a.length; k++) {
                            toReturn.add(a[k]);
                        }
                    } else {
            
                        toReturn.add(x[1]); 
                    }
        
                }
            }
       
        }
               
        return toReturn;


    }
    
    
}
