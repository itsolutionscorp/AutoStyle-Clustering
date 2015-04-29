package ngordnet;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    
    private List<Synset> synSetList = new ArrayList<Synset>();
    private Digraph hypList;
    
    public WordNet(String synsetFilename, String hyponymFilename) {
        /*used to use scanner but came with some autograder issues*/
        String tempSentence;
        int tempID;
        String tempHypPlaceHolder;
        String[] tempHyp;
        String tempDefinition;
        Synset tempSynset;
        
        String tempHypString;
        int tempHypID;
        List<Integer> tempHypDirHyp = new ArrayList<Integer>();
        
        In in = new In(new File(synsetFilename));
        while (in.hasNextLine()) {
            tempSentence = in.readLine();
            String[] sortedSentence = tempSentence.split(",");
            tempID = Integer.parseInt(sortedSentence[0]);
            tempHypPlaceHolder = sortedSentence[1];
            if (tempHypPlaceHolder.contains(" ")) {
                tempHyp = tempHypPlaceHolder.split(" ");
            } else {
                tempHyp = new String[]{tempHypPlaceHolder};
            }
            tempDefinition = sortedSentence[2];
            tempSynset = new Synset(tempID, tempHyp, tempDefinition);
            synSetList.add(tempSynset);
        }
        in.close();
        
        hypList = new Digraph(synSetList.size());
        In in1 = new In(new File(hyponymFilename));
        while (in1.hasNextLine()) {
            tempHypString = in1.readLine();
            String[] sortedHypString = tempHypString.split(",");
            tempHypID = Integer.parseInt(sortedHypString[0]);
            tempHypDirHyp.clear();
            for (int i = 1; i < sortedHypString.length; i = i + 1) {
                tempHypDirHyp.add(Integer.parseInt(sortedHypString[i]));
            }
            for (int x : tempHypDirHyp) {
                hypList.addEdge(tempHypID, x);
            }
        }
        in1.close();
    }
    public Set<String> hyponyms(String word) {
        Set<Integer> synsetIDlist = new HashSet<Integer>();
        Set<String> result = new HashSet<String>();
        
        for (Synset x : synSetList) {
            for (String y : x.getSynList()) {
                if (y.equals(word)) {
                    if (!synsetIDlist.contains(x.getID())) {
                        synsetIDlist.add(x.getID());
                    }
                }
            }
        }
        synsetIDlist = GraphHelper.descendants(hypList, synsetIDlist);
        for (int y : synsetIDlist) {
            for (Synset x : synSetList) {
                if (x.getID() == y) {
                    for (String str : x.getSynList()) {
                        result.add(str);
                    }
                }
            }
        }
        return result;
    }
    
    public boolean isNoun(String noun) {
        for (Synset x : synSetList) {
            for (String y : x.getSynList()) {
                if (y.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
    public Set<String> nouns() {
        Set<String> result = new HashSet<String>();
        for (Synset x : synSetList) {
            for (String y : x.getSynList()) {
                result.add(y);
            }
        }
        return result;
    }
    
}
