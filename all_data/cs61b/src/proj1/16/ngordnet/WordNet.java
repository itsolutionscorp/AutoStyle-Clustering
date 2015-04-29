package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.TreeSet;
// import java.util.*;


public class WordNet  {
    private Map<Integer, Synset> synsets = new HashMap<Integer, Synset>();
    private Map<String, List<Integer>> stringToInt = new HashMap<String, List<Integer>>();
    private Set<String> mykeys;
    private Digraph hypos;

    public WordNet(String synfile, String hypofile) {
        try  {
            FileReader synReader = new FileReader(new File(synfile));
            BufferedReader synread = new BufferedReader(synReader);
            String line;
            String[] currline;
            int id;
            String[] synonyms;
            List<Integer> mynewlist;
            while ((line = synread.readLine()) != null)  {
                // System.out.println(line);
                currline = line.split(",");
                id = Integer.parseInt(currline[0]);
                synonyms = currline[1].split(" ");
                synsets.put(id, new Synset(id, synonyms));
                for (String elem: synonyms)  {
                    // System.out.println(elem);
                    if (stringToInt.containsKey(elem)) {
                        stringToInt.get(elem).add(id);
                    } else  {
                        mynewlist = new ArrayList<Integer>();
                        mynewlist.add(id);
                        stringToInt.put(elem, mynewlist);
                    }
                    
                }
            }
            mykeys = stringToInt.keySet();
            synReader.close();

            System.out.println(mykeys.size());
            hypos = new Digraph(mykeys.size());
            BufferedReader hyporeader = new BufferedReader(new FileReader(new File(hypofile)));
            while ((line = hyporeader.readLine()) != null)  {
                currline = line.split(",");
                id = Integer.parseInt(currline[0]);
                for (String elem : currline) {
                    if (!elem.equals(currline[0])) {
                        hypos.addEdge(id, Integer.parseInt(elem));
                    }
                }
            }



        } catch (IOException e)  {
            e.printStackTrace();
        }
    }


    public boolean isNoun(String noun) {
        return mykeys.contains(noun);
    }


    public Set<String> nouns() {
        return mykeys;
    }

    public Set<String> hyponyms(String word) {
        Set<Integer> myword = new TreeSet<Integer>();
        for (Integer elem : stringToInt.get(word)) {
            myword.add(elem);
        }
        Set<Integer> hypowords = GraphHelper.descendants(hypos, myword);
        Set<String> ret = new TreeSet<String>();
        for (Integer currint : hypowords) {
            Synset currsynset = synsets.get(currint);
            for (String elem : currsynset.getNouns()) {
                ret.add(elem);
            }
        }
        
        return ret;
        
    }
//Digraph with total number of nodes = # of vertices
    // connect them with addEdge()

    //given x, y, z:
    //addEdge(x, y), addEdge(x,z) (order may be reveresed)


// HashMap: id--> Synset object (contains id, synonyms, hyponym bullshits)
// Map: name --> id


    private class Synset  {
        private int id;
        private String[] nouns;

        public Synset(int myid, String[] mynouns)  {
            id = myid;
            nouns = mynouns;
        }

        public String[] getNouns() {
            return nouns;
        }
    }
}
