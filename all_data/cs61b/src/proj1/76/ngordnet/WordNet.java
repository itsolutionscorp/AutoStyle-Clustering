package ngordnet;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.io.File;

public class WordNet {

    private HashMap<Integer, String> synmap;
    private HashMap<Integer, String> synmapsingle; //map of synonyms and codes 
    private HashMap<Integer, Set<Integer>> hypmap;
    private HashMap<Integer, Set<String>> synmap2;
    private HashMap<Set<String>, Integer> opposynmap2;
    private HashMap<String, Integer> opposynmap; //map of codes and synonyms
    private HashMap<String, Integer> opposynmapsingle; //map of codes and synonyms one by one
    private Digraph D;
    private List<List<List<Integer>>> hyplist;
    private Set<Integer> synsetints; //Set of codes
    private Set<String> synsetstrings; //set of nouns
    private List<Integer> hypints;



    public WordNet(String synsetFilename, String hyponymFilename) {
        File syns = new File(synsetFilename);
        File hyps = new File(hyponymFilename);
        Set<String> hyponym = new HashSet<String>();
        List<String> syn = Arrays.asList(synsetFilename);
        List<String> hyp = Arrays.asList(hyponymFilename);
        synmap = new HashMap<Integer, String>(syn.size());
        synmapsingle = new HashMap<Integer, String>();
        opposynmap = new HashMap<String, Integer>(syn.size());
        opposynmapsingle = new HashMap<String, Integer>();
        hypmap = new HashMap<Integer, Set<Integer>>(hyp.size());
        synmap2 = new HashMap<Integer, Set<String>>();
        opposynmap2 = new HashMap<Set<String>, Integer>();
        In s = new In(synsetFilename);
        In h = new In(hyponymFilename);
        synsetints = new HashSet<Integer>(syn.size());
        synsetstrings = new HashSet<String>();
        while (s.hasNextLine()) {
            String what = s.readLine();
            Scanner w = new Scanner(what).useDelimiter(",");
            int num = w.nextInt();
            Set<String> synstrings = new HashSet<String>();
            String synonyms = w.next();
            Scanner strings = new Scanner(synonyms);
            while (strings.hasNext()) {
                synstrings.add(strings.next());
            }
            synmap2.put(num, synstrings);
            opposynmap2.put(synstrings, num);
            synmap.put(num, synonyms);
            opposynmap.put(synonyms, num);
            synsetints.add(num);
            Scanner n = new Scanner(synonyms);
            String[] synse = synonyms.split(" ");
            for (String j: synse) {
                synsetstrings.add(j);
            }
        }
        hyplist = new ArrayList<List<List<Integer>>>();
        while (h.hasNextLine()) {
            String hypp = h.readLine();
            Scanner t = new Scanner(hypp).useDelimiter(",");
            int code = t.nextInt();
            hypints = new ArrayList<Integer>();
            List<Integer> hypint = new ArrayList<Integer>();
            hypint.add(code);
            List<List<Integer>> hypvals = new ArrayList<List<Integer>>();
            while (t.hasNextInt()) {
                hypints.add(t.nextInt());
            }
            hypvals.add(hypint);
            hypvals.add(hypints);
            hyplist.add(hypvals);
        }

    
        D = new Digraph(synsetints.size());
        for (List<List<Integer>> k: hyplist) {
            for (Integer y: k.get(1)) {
                D.addEdge(k.get(0).get(0), y);
        
            }
        }

        for (String p: opposynmap.keySet()) {
            String[] temp = p.split(" ");
            for (String y: temp) {
                opposynmapsingle.put(y, opposynmap.get(p)); 
            }
        }

        for (String w: opposynmapsingle.keySet()) {
            synmapsingle.put(opposynmapsingle.get(w), w);
        }

    
    }

    public Set<String> nouns() {
        return synsetstrings;
    }
    public boolean isNoun(String noun) {
        return synsetstrings.contains(noun);
    }


    public Set<String> hyponyms(String word) {
        Set<Integer> syncodes = new HashSet<Integer>();
        Set<String> hyponym = new HashSet<String>();
        Set<Integer> integers = new HashSet<Integer>();
        for (Set<String> k: opposynmap2.keySet()) {
            if (k.contains(word)) {                 
            //make opposynmap2 key a list, otherwise, losing numberss...i think
                syncodes.add(opposynmap2.get(k));
            }
        }
        integers = GraphHelper.descendants(D, syncodes);
        for (int i: integers) {
            for (String j: synmap2.get(i)) {
                hyponym.add(j);
            }
        }
        return hyponym;
    }   
}
