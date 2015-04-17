package ngordnet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;



public class WordNet {
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    private WordHolder<Integer, Integer> hypSet = new WordHolder<Integer, Integer>();
    private WordHolder<Integer, String> intSet = new WordHolder<Integer, String>();
    private WordHolder<String, Integer> strSet = new WordHolder<String, Integer>();

    public WordNet(String synsetFilename, String hyponymFilename) {


        try {
            Scanner synReader = new Scanner(new File(synsetFilename));
            Scanner hypReader = new Scanner(new File(hyponymFilename));

            synReader.useDelimiter(",");
            hypReader.useDelimiter(",");

            Integer wordID;
            String wordNAME;
            Integer hypoID;
            String rest;

            while (synReader.hasNext()) {
              
                wordID = synReader.nextInt();
                wordNAME = synReader.next();
                
                String[] nameS = wordNAME.split("\\s+");
                
                for (String name: nameS) {
                    intSet.putSet(wordID, name);
                    strSet.putSet(name, wordID);
                }
                synReader.nextLine();
            }
            synReader.close();


            while (hypReader.hasNextInt()) {

                wordID = hypReader.nextInt();
                rest = hypReader.nextLine();

                Scanner restReader = new Scanner(rest);
                restReader.useDelimiter(",");

                while (restReader.hasNextInt()) {
                    hypoID = restReader.nextInt();

                    hypSet.putSet(wordID, hypoID);
                }
                restReader.close();
            }

            hypReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("*** FILE NOT FOUND *** - Raise " + e);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return strSet.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return strSet.keySet();
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
        
        HashSet<String> returnSet = new HashSet<String>();
        
        if (strSet.containsKey(word)) {

            TreeSet<Integer> synIDs = strSet.get(word);
            HashSet<Integer> temp = new HashSet<Integer>();

            for (Integer b: synIDs) {
                if (hypSet.containsKey(b)) {
                    temp.addAll(hyponymFinder(b));
                }
            }

            synIDs.addAll(temp);

            for (Integer a: synIDs) {
                if (intSet.containsKey(a)) {
                    TreeSet<String> synNames = intSet.get(a);
                    returnSet.addAll(synNames);
                }
            }
        }
        return returnSet;
    }

    private HashSet<Integer> hyponymFinder(Integer key) {
        
        HashSet<Integer> returnSet = new HashSet<Integer>();
        
        if (hypSet.containsKey(key)) {
            
            TreeSet<Integer> keys = hypSet.get(key);
            returnSet.addAll(keys);
            
            for (Integer k: keys) {
                returnSet.addAll(hyponymFinder(k));
            }
        }
        return returnSet;
    }
}




/*
Sources:

-Reading a csv or txt file in Java
http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
http://howtodoinjava.com/2013/05/27/parse-csv-files-in-java/
http://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html

-Using Java TreeMap
http://docs.oracle.com/javase/7/docs/api/java/util/TreeMap.html

-Using Java TreeSet
http://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html

-Using Java HashSet
http://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html
*/


