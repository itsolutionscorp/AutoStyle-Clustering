package ngordnet;
import edu.princeton.cs.algs4.Digraph;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;



import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.io.IOException;

public class WordNet {
    private static Digraph dg;
    private static int[][] H;
    private static Integer[] id;
    private static String[][] S;
//
//

     /*public static void main(String[] args) throws FileNotFoundException, IOException {
        readHyponyms("wordnet/hyponyms14.txt");
        readSynsets("wordnet/synsets14.txt");
        //System.out.println(dg.outdegree(4));
        Set<Integer> is = getIndexesofWord("alteration");
        System.out.println(is);

    }*/



    private static void readHyponyms(String hyponymFilename) throws
                               FileNotFoundException, IOException {
        File file = new File(hyponymFilename);
        Scanner hyps = new Scanner(file);
        int lineCount = 0;
        while (hyps.hasNextLine()) {
            hyps.nextLine();
            lineCount += 1;
        }

        id = new Integer[lineCount];
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        int lc = 0;
        int max = 0;
        for (String line : lines) {
            String[] array = line.split(",");
            int size = array.length;
            for (int i = 0; i < size; i++) {
                if (Integer.parseInt(array[i]) > max) {
                    max = Integer.parseInt(array[i]);
                }
            }
            if (lc < lineCount) {
                id[lc] = Integer.parseInt(array[0]);
            }
            lc++;
        }
        dg = new Digraph(max + 1);
        int maxEntries = 0;
        for (String line : lines) {
            String[] array = line.split(",");
            int size = array.length;
            if (size > maxEntries) {
                maxEntries = size;
            }
        }
        H = new int[max + 1][maxEntries + 1];
        //System.out.println(max + " MAXENTRIES--> " +maxEntries);
        //set all entries to be negative initially to allow for easy lookup once filled in
        for (int x = 0; x <= max; x++) {
            for (int y = 0; y <= maxEntries; y++) {
                H[x][y] = -1;
            }
        }
        for (int i : id) {
            H[i][0] = i;
        }

        for (String line : lines) {
            String[] array = line.split(",");
            int size = array.length;
            int first = Integer.parseInt(array[0]);
           // System.out.println(size);
            for (int e = 1; e < size; e++) {
                dg.addEdge(first, Integer.parseInt(array[e]));
            }
        }
        /*for (int x = 0; x <= max; x++) {
            if (H[x][0] >= 0) {
                for (int y = 1; y < maxEntries; y++) {
                    if (H[x][y] >= 0) {
                        dg.addEdge(H[x][0], H[x][y]);
                    }
                }
            }
        }*/
    }

    private static void readSynsets(String synsetFilename) throws 
                            FileNotFoundException, IOException {
        File file = new File(synsetFilename);
        Scanner syns = new Scanner(file);
        int lineCount = 0;
        while (syns.hasNextLine()) {
            syns.nextLine();
            //System.out.println(syns.next());
            lineCount += 1;

        } 
        S = new String[lineCount][2];
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        int lc = 0;
        for (String line : lines) {
            String[] array = line.split(",");
            S[lc][0] = array[1];
            S[lc][1] = array[2];
            lc++;
        }
    }


   
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            readHyponyms(hyponymFilename);
            readSynsets(synsetFilename);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e1) {
            System.out.println(e1);
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < S.length; i++) {
            String[] possible = S[i][0].split(" ");
            for (String word : possible) {
                if (word.equals(noun)) {
                    return true;
                }
            }
            
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> set = new TreeSet<String>();
        for (int i = 0; i < S.length; i++) {
            String[] allWords = S[i][0].split(" ");
            for (String word : allWords) {
                if (isNoun(word)) {
                    set.add(word);
                }
            }
        }
        return set;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. if WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    private Set<String> wordsAtindex(int x) {
        Set<String> set = new TreeSet<String>();
        String[] allWords = S[x][0].split(" ");
        for (String word : allWords) {
            set.add(word);
        }
        return set;
    }

    private Set<String> getShallow(String word) {
        Set<String> set = new TreeSet<String>();
        for (int i = 0; i < S.length; i++) {
            if (S[i][0].contains(word)) {
                for (String og : wordsAtindex(i)) {
                    set.add(og);
                }
                for (int edge : dg.adj(i)) {
                    for (String h: wordsAtindex(edge)) {
                        set.add(h);
                    }
                }
                set.add(word);
            }
        }
        return set;
    }

    private Set<Integer> getIndexesofWord(String word) {
        Set<Integer> is = new TreeSet<Integer>();
        for (int i = 0; i < S.length; i++) {
            String[] words = S[i][0].split(" ");
            for (String w : words) {
                if (w.equals(word)) {
                    is.add(i);
                }
            }
        }
        return is;
    }



    public Set<String> hyponyms(String word) {
        Set<Integer> r = new TreeSet<Integer>();
        Set<Integer> occurances = getIndexesofWord(word);
        for (int j : GraphHelper.descendants(dg, occurances)) {
            r.add(j);
        }
        Set<String> all = new TreeSet<String>();
        /*for (int i : GraphHelper.descendants(dg, r)) {
            r.add(i);
        }*/
        for (int i : r) {
            Set<String> words = wordsAtindex(i);
            for (String s : words) {
                all.add(s);
            }  
        }
        return all;
    }

                



//end class    
}
                    
                
     

                

    











