package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import edu.princeton.cs.algs4.Digraph;
/*uses two maps. One with ID as key and synsets as values, and another with noun keys and 
  a list of ID's that noun is associated with as values.*/
  //came up with this strategy with Clay Smythe and Finn Howell then implemented
  //the code separately;
public class WordNet { 
    private In allSynset, allHyponym, allSynset2;
    private Map<Integer, String[]> iDAndSynset;
    private Map<String, HashSet<Integer>> nounAndIDs;
    private String stringAllSynset, stringAllHyponym;
    private Digraph g;
    private int vertices = 0;
    private GraphHelper gh;

    public WordNet(String synsetFilename, String hyponymFilename) {
        allSynset = new In(synsetFilename);
        allHyponym = new In(hyponymFilename);
        iDAndSynset = new HashMap<Integer, String[]>();
        nounAndIDs = new HashMap<String, HashSet<Integer>>();

        while (!allSynset.isEmpty()) {
            String curString = allSynset.readLine();
            String[] nate = curString.split(",");
            String[] curSynset = nate[1].split(" ");
            iDAndSynset.put(Integer.parseInt(nate[0]), curSynset); 

            for (int i = 0; i < curSynset.length; i++) {
                if (!nounAndIDs.containsKey(curSynset[i])) {
                    nounAndIDs.put(curSynset[i], new HashSet<Integer>());
                    nounAndIDs.get(curSynset[i]).add(Integer.parseInt(nate[0]));
                } else {
                    nounAndIDs.get(curSynset[i]).add(Integer.parseInt(nate[0]));
                }
            
            }
            vertices += 1;
        }
        // found "split" method online for convenience
        g = new Digraph(vertices);
        while (allHyponym.hasNextLine()) {
            String thisString = allHyponym.readLine();
            String[] comma = thisString.split(",");
            for (int i = 1; i < comma.length; i++) {
                g.addEdge(Integer.parseInt(comma[0]), Integer.parseInt(comma[i]));
            }
        }

        gh = new GraphHelper();
    }

    public boolean isNoun(String noun) {
        return nounAndIDs.containsKey(noun);
    }

    public Set<String> nouns() {
        return nounAndIDs.keySet();
    }

    public Set<String> hyponyms(String word) {
        //adds all synonyms of word
        Set<String> toReturn = new HashSet<String>();
        HashSet<Integer> ids = nounAndIDs.get(word);
        Iterator idIter = ids.iterator();
        while (idIter.hasNext()) {
            String [] syns = iDAndSynset.get(idIter.next());
            for (int i = 0; i < syns.length; i++) {
                toReturn.add(syns[i]);
            }
        }

        //adds all hyponyms of word
        Set<Integer> hyps = gh.descendants(g, nounAndIDs.get(word));
        Iterator hypsIter = hyps.iterator();
        while (hypsIter.hasNext()) {
            String [] hyps1 = iDAndSynset.get(hypsIter.next());
            for (int i = 0; i < hyps1.length; i++) {
                toReturn.add(hyps1[i]);
            }

        }
        return toReturn;
    }   
}
