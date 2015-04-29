package ngordnet;

import java.util.HashSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.Set;

public class WordNet {
    private int count = 0;
    private int line1 = 0;
    private final int xx = 100000;
    private final int yy = 51;
    private String[][] hy = new String[xx][3];
    private String[][] cy = new String[xx][yy];

    public WordNet(String synsetFilename, String hyponymFilename) {
        File file = new File(synsetFilename);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                // System.out.println("line " + count + ": " + tempString);
                hy[count] = tempString.split(",");
                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        File file1 = new File(hyponymFilename);
        BufferedReader reader1 = null;
        try {
            reader1 = new BufferedReader(new FileReader(file1));
            String tempString1 = null;

            while ((tempString1 = reader1.readLine()) != null) {
                // System.out.println("line " + line1 + ": " + tempString1);
                cy[line1] = tempString1.split(",");
                line1++;
            }
            reader1.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader1 != null) {
                try {
                    reader1.close();
                } catch (IOException e1) {
                }
            }
        }

    }

    public boolean isNoun(String noun) {
        String dy[];
        for (int i = 0; i < count; i++) {
            dy = hy[i][1].split(" ");
            for (String j : dy){
                if (j.equals(noun))
                    return true;
                }
        }

        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        String dy[];
        Set<String> x = new HashSet<String>();
        for (int i = 0; i < count; i++) {
            dy = hy[i][1].split(" ");
            for (String j : dy)
                x.add(j);
        }
        return x;
    }

    /**
     * Returns the set of all hyponyms of WORD as well as all synonyms of WORD.
     * If WORD belongs to multiple synsets, return all hyponyms of all of these
     * synsets. See http://goo.gl/EGLoys for an example. Do not include hyponyms
     * of synonyms.
     */
    public Set<String> hyponyms(String word) {
        Set<String> x = new HashSet<String>();
        Set<Integer> z = new HashSet<Integer>();
        Set<Integer> y = new HashSet<Integer>();
        int age;
        String dy[];
        for (int i = 0; i < count; i++) {
            dy = hy[i][1].split(" ");
            for (String k : dy)
                if (k.equals(word)) {
                    z.add(i);
                    // System.out.println("z:is"+i);
                }
        }

        Digraph xiaowang = new Digraph(count);
        // System.out.println(line1);
        for (int j = 0; j < line1; j++)
            for (int i = 0; i < cy[j].length; i++) {

                if (cy[j][i] != null)
                    xiaowang.addEdge(Integer.parseInt(cy[j][0]),
                            Integer.parseInt(cy[j][i]));

            }
        y = GraphHelper.descendants(xiaowang, z);
        for (Integer z1 : y) {
            // System.out.println("Y:is"+z1);
            dy = hy[z1][1].split(" ");
            for (String k : dy)
                x.add(k);
        }
        for (Integer z1 : z) {
            // System.out.println("Y:is"+z1);
            dy = hy[z1][1].split(" ");
            for (String k : dy)
                x.add(k);
        }
        return x;
    }
}
