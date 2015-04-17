package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;



public class WordNet {
    //idMap maps id:[synsets, definition]
    private HashMap<String, ArrayList<String>> idMap = new HashMap<String, ArrayList<String>>();
    //nameMap maps name:[id1, id2...]
    private HashMap<String, ArrayList<String>> nameMap = new HashMap<String, ArrayList<String>>();
    //Set of ids for use in nouns()
    private Set<Integer> idSet = new HashSet<Integer>();
    private Digraph wordDigraph;
    private GraphHelper graphHelper = new GraphHelper();
    private int diGraphVertices;

    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);

        //Use synset file to map each id to names/def and each name to ids
        while (synsetIn.hasNextLine()) {
            diGraphVertices += 1;
            String currentLine = synsetIn.readLine();
            String[] currentLineSplit = currentLine.split(",");
            String id = currentLineSplit[0];
            idSet.add(new Integer(id));

            //Map ids to ArrayList of synsets and definition
            //Could use size 2 array if want to optimize a bit
            ArrayList<String> detailArray = new ArrayList<String>();
            for (int i = 1; i < currentLineSplit.length; i++) {
                detailArray.add(currentLineSplit[i]);
            }
            idMap.put(id, detailArray);


            //Map names to ArrayList of ids
            String unseparatedSynsets = currentLineSplit[1];
            String[] synsets = unseparatedSynsets.split(" ");
            for (String synset: synsets) {
                ArrayList<String> idArray = new ArrayList<String>();
                if (nameMap.containsKey(synset)) {
                    idArray = nameMap.get(synset);
                    idArray.add(id);
                } else {
                    idArray.add(id);
                    nameMap.put(synset, idArray);
                }
            }
        }

        //Initiliaze digraph
        wordDigraph = new Digraph(diGraphVertices);

        //Use hyponym file to set up digraph
        while (hyponymIn.hasNextLine()) {
            String currentLine = hyponymIn.readLine();
            String[] currentLineSplit = currentLine.split(",");
            String hypernym = currentLineSplit[0];

            for (int i = 1; i < currentLineSplit.length; i++) {
                Integer hypernymInteger = Integer.parseInt(hypernym);
                Integer hyponymInteger = Integer.parseInt(currentLineSplit[i]);
                wordDigraph.addEdge(hypernymInteger, hyponymInteger);
            }
        }
    }

    private ArrayList<String> getIdArray(String word) {
        return nameMap.get(word);
    }

    public Set<String> hyponyms(String word) {
        ArrayList<String> wordIdList = nameMap.get(word);
        Set<String> hyponymSet = new HashSet<String>();
        Set<Integer> synonymIdSet = new HashSet<Integer>();

        //get synonyms
        for (String wordId: wordIdList) {
            String currentIdString = idMap.get(wordId).get(0);
            String[] currentSynonymList = currentIdString.split(" ");
            synonymIdSet.add(new Integer(wordId));

            for (String synonym: currentSynonymList) {
                hyponymSet.add(synonym);
            }
        }

        //get hyponyms of all synonyms
        Set<Integer> hyponymIdSet = graphHelper.descendants(wordDigraph, synonymIdSet);
        for (Integer id: hyponymIdSet) {
            String idString = Integer.toString(id);
            String currentHyponymString = idMap.get(idString).get(0);
            String[] currentHyponymList = currentHyponymString.split(" ");

            for (String hyponym: currentHyponymList) {
                hyponymSet.add(hyponym);
            }
        }

        return hyponymSet;
    }

    public Set<String> nouns() {
        Set<String> nounSet = nameMap.keySet();
        return nounSet;
    }

    public boolean isNoun(String noun) {
        return nameMap.containsKey(noun);
    }
}
