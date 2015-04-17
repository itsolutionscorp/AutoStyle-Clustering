package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;
// import java.org.clapper.util.misc.MultiValueMap;
// import java.lang.String;
// import java.lang.Integer;

public class WordNet {
    private Digraph d;
    private MultiValueMap<Integer, String> m;
    private MultiValueMap<String, Integer> mRev;
    private In sFile;
    private In sFile2;
    private In hFile;
    private In hFile2;
    private int nodeNum;
    private int mapSize;
    private int someInitSize;

    // Creates an instance of a WordNet.
    // Reads in one synset and one hyponym csv data files.  
    //
    // Functionalities I'd Like:  
    //  - Contains method.
    //  - Return entire contents.
    //  - Given a node ID, can return all children nodes.
    //    One IDmay correspond to multiple nodes whose children
    //    must be fetched.
    public WordNet(String synsetFilename, String hyponymFilename) {
        sFile = new In(synsetFilename);
        sFile2 = new In(synsetFilename);
        hFile = new In(hyponymFilename);
        hFile2 = new In(hyponymFilename);
        // HARD-CODED
        // nodeNum = 100000;
        // mapSize = 11;
        
        // NOT HARD-CODED
        nodeNum = digraphSize(hFile2);
        mapSize = fileLength(sFile2);
        someInitSize = mapSize * mapSize;

        // Create a Digraph describing hyponym relations.
        d = new Digraph(nodeNum);
        makeDigraph(hFile);

        // Create a MultiValueMap.  Maps synset id# to synsets.
        m = new MultiValueMap<Integer, String>(mapSize);
        makeMultiValueMap(sFile);

        // Create a MultiValueMap with keys and values reversed.
        mRev = new MultiValueMap<String, Integer>(mapSize);
        makeMultiValueMapReverse();

    }

    private void makeDigraph(In hIn) {
        String lineRead;
        String[] lineTokens;
        int hNum; // Number of hyponyms
        Integer[] hIDs;
        Integer hIndex;

        // Read hFile, line by line.
        while (hIn.hasNextLine()) {
            // Read a line.
            lineRead = hIn.readLine();
            // Split into tokens.
            lineTokens = lineRead.split("[,]");
            // Number of hIDs.
            hNum = lineTokens.length - 1;
            // Initialize empty hIDs array of correct size.
            hIDs = new Integer[hNum];
            // Initialize hIndex.
            hIndex = Integer.parseInt(lineTokens[0]);

            // Initialize hIDs array.
            for (int i = 0; i < hNum; i++) {
                hIDs[i] = Integer.parseInt(lineTokens[i + 1]);
            }

            // Draw digraph edges from hIndex to each element
            //   in hIDs.
            for (Integer hItem : hIDs) {
                d.addEdge(hIndex, hItem);
            }
        }
    }

    private void makeMultiValueMap(In sIn) {
        String lineRead;
        String[] lineTokens;
        String[] sIDs;
        Integer sIndex;
        String synsetsToSeparate;

        // Read sFile, line by line.
        while (sIn.hasNextLine()) {
            // Read a line.
            lineRead = sIn.readLine();
            // Split into tokens.
            lineTokens = lineRead.split("[,]");
            // Initialize sIndex.
            sIndex = Integer.parseInt(lineTokens[0]);
            // Separate synsets.
            synsetsToSeparate = lineTokens[1];
            // Initialize sIDs with separated synsets.
            sIDs = synsetsToSeparate.split("[ ]");

            // Map sIndex to sIDs.
            for (String sItem : sIDs) {
                m.put(sIndex, sItem);
            }
        }
    }

    private void makeMultiValueMapReverse() {
        // Instantiate map to reverse.
        MultiValueMap<Integer, String> map1 = m;
        // For each key in original map,
        for (Integer k : map1.keySet()) {
            // For each value of said key,
            for (String v : map1.getCollection(k)) {
                // Put (value, key).
                mRev.put(v, k);
            }
        }
    }

    private int fileLength(In sIn) {
        int sizeCount = 0;
        String lineRead;
        while (sIn.hasNextLine()) {
            lineRead = sIn.readLine();
            sizeCount = sizeCount + 1;
        }
        return sizeCount;
    }

    private int digraphSize(In hIn) {
        String lineRead;
        String[] lineTokens;
        int hNum; // Number of hyponyms
        Integer[] hIDs;
        Integer hIndex;
        Set<Integer> filler = new HashSet<Integer>(someInitSize);
        int sizeCount = 0;
        
        // Read hFile, line by line.
        while (hIn.hasNextLine()) {
            // Read a line.
            lineRead = hIn.readLine();
            // Split into tokens.
            lineTokens = lineRead.split("[,]");
            // Number of hIDs.
            hNum = lineTokens.length - 1;
            // Initialize empty hIDs array of correct size.
            hIDs = new Integer[hNum];
            // Initialize hIndex.
            hIndex = Integer.parseInt(lineTokens[0]);

            // Initialize hIDs array.
            for (int i = 0; i < hNum; i++) {
                hIDs[i] = Integer.parseInt(lineTokens[i + 1]);
            }

            // For each hIndex, hItem pair
            for (Integer hItem : hIDs) {
                // If filler contains hIndex,
                if (filler.contains(hIndex)) {
                    // Increment sizeCount by 1.
                    sizeCount = sizeCount + 1;
                } else {
                    // Else, increment sizecount by 2.
                    sizeCount = sizeCount + 2;
                }
                // Add hIndex to filler.
                filler.add(hIndex);
            }
        }
        return sizeCount;
    }

    // int size(In sInToSize) {
    //     // Probably shouldn't do this:
    //     // for (int i = 0; fileToSizeUp.StdIn.hasNextLine(); i++) {
    //     // }
    //     int i = 0;
    //     while (sInToSize.hasNextLine()) {
    //         i = i + 1;
    //         System.out.println("Fix size method!");
    //     }
    //     return i;
    // }

    // Returns true if a WordNet contains noun.
    public boolean isNoun(String noun) {
        if (m.containsValue(noun)) {
            return true;
        }
        return false;
    }

    // Returns a set of all nouns in a WordNet.
    // Order does not matter.
    public Set<String> nouns() {
        // Collection<String> valCollection = m.values();
        // Returns all values in m--ie. all nouns in WordNet.
        // HOWEVER, m.values returns Collection<String>, but 
        //   this method requires Set<String> be returned...
        // Is the only alternative to iterate through the
        //   collection, reassigning each entry to a set?
        Set<String> interSet = new HashSet<String>(nodeNum);
        for (String v : m.values()) {
            interSet.add(v);
        }
        return interSet;
    }

    // Returns a set of all hyponyms of word in a WordNet.
    // If word belongs to many synsets, returns hyponyms of all
    //   such synsets.  See http://goo.gl/EGLoys for clarification.
    // Should NOT return the hyponyms word's synonyns.  
    // Order does not matter.
    public Set<String> hyponyms(String word) {
        Collection<Integer> sIDcol;
        Set<Integer> sIDset = new HashSet<Integer>(nodeNum);
        Set<Integer> hIDset;
        Collection<String> wordsToReturn;
        Set<String> sIDstoReturn = new HashSet<String>(nodeNum);
        
        // Look up synsetID#s corresponding to word.
        //   There may be multiple--store this as sIDcol.
        sIDcol = mRev.getCollection(word);
        // Convert Collection to Set:
        for (Integer i1 : sIDcol) {
            sIDset.add(i1);
        }
        // Find IDs of descendents of these IDs.
        hIDset = GraphHelper.descendants(d, sIDset);
        // For each descendant ID, get set of corresponding words.
        for (Integer i2 : hIDset) {
            wordsToReturn = m.getCollection(i2);
            // For each string collection, add each string to
            //   the set to return.
            for (String v : wordsToReturn) {
                sIDstoReturn.add(v);
            }
        }
        return sIDstoReturn;
    }

    // public static void main(String[] args) {

    // }
}

/*
Questions to ask:
- How does one determine the Digraph size?
- See public Set<String> nouns().
- What is supposed to happen in the main method?

        System.out.println("HelloD1");
        System.out.println("HelloD2");
        System.out.println("HelloD3");
        System.out.println("HelloD4");
        System.out.println("HelloD-Incby1");
        System.out.println("HelloD-Incby2");
        System.out.println("HelloD5");
        System.out.println("HelloD6");
*/
