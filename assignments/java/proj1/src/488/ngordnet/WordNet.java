package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Set;
// import java.util.List;
// import java.util.ArrayList;
import java.util.HashSet;
//ID to list of words
//ID to list of IDS
//Set of words for existance
//Digraph
public class WordNet {
    //Thanks to OH and the conceptual thinkings of friends
    private Map<Integer, Set<String>> synsetIDToWords;
    private Map<String, Set<Integer>> synsetWordToIDs;
    private Map<Integer, Set<Integer>> synsetIDToIDs;
    private Digraph dig;

    public WordNet(String synsetFilename, String hyponymFilename) {
        synsetIDToWords = new HashMap<Integer, Set<String>>();
        synsetIDToIDs = new HashMap<Integer, Set<Integer>>();
        synsetWordToIDs = new HashMap<String, Set<Integer>>();

        try {
            FileReader fReadSynset = new FileReader(synsetFilename);
            BufferedReader synIn = new BufferedReader(fReadSynset);

            while (synIn.ready()) {
                String synText = synIn.readLine();
                StringTokenizer synToken = new StringTokenizer(synText, ",");

                Integer num = Integer.parseInt(synToken.nextToken());
                String nouns = synToken.nextToken();
                
                synToken = new StringTokenizer(nouns, " ");
                
                Set<String> lst = new HashSet<String>();
                while (synToken.hasMoreTokens()) {
                    String elem = synToken.nextToken();
                    lst.add(elem);
                    
                    if (synsetWordToIDs.containsKey(elem)) {
                        synsetWordToIDs.get(elem).add(num);
                    } else {
                        Set<Integer> setOfIDs = new HashSet<Integer>();
                        setOfIDs.add(num);
                        synsetWordToIDs.put(elem, setOfIDs);
                    }
                }
                synsetIDToWords.put(num, lst);
            }

            dig = new Digraph(synsetIDToWords.size());
            FileReader fReadHyp = new FileReader(hyponymFilename);
            BufferedReader hypIn = new BufferedReader(fReadHyp);

            String hypText;
            StringTokenizer hypToken;

            while (hypIn.ready()) {
                hypText = hypIn.readLine();
                hypToken = new StringTokenizer(hypText, ",");

                Integer map = Integer.parseInt(hypToken.nextToken());
                dig.addEdge(map, map);
                Set<Integer> lst = new HashSet<Integer>();

                while (hypToken.hasMoreTokens()) {
                    Integer currTok = Integer.parseInt(hypToken.nextToken());
                    dig.addEdge(map, currTok);
                    lst.add(currTok);
                }
                synsetIDToIDs.put(map, lst);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean isNoun(String noun) {
        return synsetWordToIDs.containsKey(noun);
    }

    public Set<String> nouns() {
        return synsetWordToIDs.keySet();
    }

    //Friend and OH definitely helped talk me through this conceptually to help
    //guide me into rethinking my data structures
    public Set<String> hyponyms(String word) {
        Set<String> retVal = new HashSet<String>();
        
        Set<Integer> id = synsetWordToIDs.get(word);
        Set<Integer> numID = new HashSet<Integer>();
        
        for (Integer item : id) {
            numID.add(item);
        }
        retVal.add(word);

        Set<Integer> idFromGraph = GraphHelper.descendants(dig, numID); 
        for (Integer numbers : idFromGraph) {
            Set<String> collect = synsetIDToWords.get(numbers);

            for (String itemsInCollect : collect) {
                retVal.add(itemsInCollect);
            }
        }
        return retVal;
    }
    //BRIAN ROCKS MY SOCKS!
}
