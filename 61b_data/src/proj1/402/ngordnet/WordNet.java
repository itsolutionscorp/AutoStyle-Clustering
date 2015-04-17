package ngordnet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.ListIterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private HashMap<Integer, String[]> synmap;
    private Digraph d;
    
    public WordNet(String synsetFilename, String hyponymFilename) { 
        //Reading and splitting the synset. Creates a synset hashmap.
        try {
            BufferedReader brsyn = new BufferedReader(new FileReader(synsetFilename)); 
            /*buffered reader stuff from 
             *http://stackoverflow.com/questions/5868369/
             *how-to-read-a-large-text-file-line-by-line-using-java*/
            String line;
            synmap = new HashMap<Integer, String[]>();
            while ((line = brsyn.readLine()) != null) { 
                String[] synarr = line.split(","); // from 
                //http://www.informit.com/articles/article.aspx?p=680829&seqNum=8
                int skey = Integer.parseInt(synarr[0]);
                String[] svalues = synarr[1].split(" ");
                synmap.put(skey, svalues);
            }
            brsyn.close();
            
            //Reading and splitting the hyponym. Creates a hyponym digraph/hashmap.     
            BufferedReader brhyp = new BufferedReader(new FileReader(hyponymFilename)); 
            /*buffered reader stuff from 
             *http://stackoverflow.com/questions/5868369/
             *how-to-read-a-large-text-file-line-by-line-using-java
             */
            int digraphsize = synmap.size();
            d = new Digraph(digraphsize);
            while ((line = brhyp.readLine()) != null) {
                String[] shyparr = line.split(","); // from 
                //http://www.informit.com/articles/article.aspx?p=680829&seqNum=8
                int[] hyparr = new int[shyparr.length];
                hyparr[0] = Integer.parseInt(shyparr[0]);
                for (int i = 1; i < shyparr.length; i = i + 1) {
                    hyparr[i] = Integer.parseInt(shyparr[i]);
                    d.addEdge(hyparr[0], hyparr[i]);
                }
            }
            brhyp.close();
        } catch (IOException e) {
            System.out.println("badCommand");
        }
    }
        
    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Integer t: synmap.keySet()) {
            String[] ts = synmap.get(t);
            for (int i = 0; i < ts.length; i = i + 1) {
                if (ts[i].equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> allNouns = new HashSet<String>();
        for (Integer k : synmap.keySet()) {
            String[] toBeAdded = synmap.get(k);
            for (int i = 0; i < toBeAdded.length; i = i + 1) {
                allNouns.add(toBeAdded[i]);
            }
        }
        return allNouns;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        // Creates the hyponym set to return
        HashSet<String> allHyponyms = new HashSet<String>();
        // Inverts synmap to get key from a value
        HashMap<String, LinkedList<Integer>> inverse = new HashMap<String, LinkedList<Integer>>();
        for (Integer i: synmap.keySet()) {
            String[] tobeKey = synmap.get(i);
            LinkedList<Integer> tobeVal = new LinkedList<Integer>();
            tobeVal.add(i);
            for (int j = 0; j < tobeKey.length; j = j + 1) {    
                if (inverse.containsKey(tobeKey[j])) {
                    LinkedList<Integer> oriVal = inverse.get(tobeKey[j]);
                    ListIterator<Integer> itint = oriVal.listIterator();
                    while (itint.hasNext()) {
                        tobeVal.add(itint.next());
                    }
                }
                inverse.put(tobeKey[j], tobeVal);
            }
        }
        if (isNoun(word)) {
            LinkedList<Integer> got = inverse.get(word);
            while (got.size() != 0) {
                ListIterator<Integer> willIt = got.listIterator();
                if (willIt.hasNext()) {
                    int current = willIt.next();
                    // Adds itself
                    String[] addSelf = synmap.get(current);
                    for (int y = 0; y < addSelf.length; y = y + 1) {
                        if (!allHyponyms.contains(addSelf[y])) {                       
                            allHyponyms.add(addSelf[y]);
                        }
                    }
                    // Adds adjacent vertices to got so that it can be iterated through later
                    if (d.outdegree(current) != 0) {
                        Iterable<Integer> toIterate = d.adj(current);
                        for (Integer l: toIterate) {
                            String[] toAdd = synmap.get(l);
                            for (int z = 0; z < toAdd.length; z = z + 1) {  
                                if (!allHyponyms.contains(toAdd[z])) {                       
                                    allHyponyms.add(toAdd[z]);
                                }
                            }
                            got.add(l); //adds l to list of vertices that I need to iterate through
                        }
                    }   
                }
                got.remove(0); //removes the oldest entry
                //(bc I just looped through it and got its adjacent vertices)
            }
        }
        return allHyponyms;
    }
}
