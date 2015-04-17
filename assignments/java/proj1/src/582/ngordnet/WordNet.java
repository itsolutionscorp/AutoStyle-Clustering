package ngordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import ngordnet.GraphHelper;
import java.util.*;

public class WordNet {
    private Digraph tree;
    private int count = 0;

    private Map<Integer, ArrayList<String>> table = new HashMap<Integer, ArrayList<String>>();
    private Map<String, ArrayList<Integer>> invTable = new HashMap<String, ArrayList<Integer>>();
    

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
    	In syn = new In(synsetFilename);
        In hypo = new In(hyponymFilename);
        String synline = syn.readLine();
        String hypoline = hypo.readLine();
        String[] synRawTokens = synline.split(",");
        String[] hypoRawTokens = hypoline.split(",");  

        while (synline != null) {
          count += 1; 
          String[] synonyms = synRawTokens[1].split(" ");
          if (synonyms.length > 1) {
             for (int i=0; i < synonyms.length; i++) {
                Integer number = Integer.parseInt(synRawTokens[0]);
                ArrayList array1 = new ArrayList();
                array1.add(synonyms[i]);
                ArrayList array2 = new ArrayList();
                array2.add(number);
                if (table.containsKey(number)) {
                    ArrayList<String> adder2 = table.get(number);
                    adder2.addAll(array1);
                    table.put(number, adder2);
                }else {
                    table.put(number, array1);
                }
                if (invTable.containsKey(synonyms[i])) {
                    ArrayList<Integer> adder = invTable.get(synonyms[i]);
                    adder.addAll(array2);
                    invTable.put(synonyms[i], adder);
                }else {
                    invTable.put(synonyms[i], array2);
                }
            }
            synline = syn.readLine(); 
            if (synline != null) {
                synRawTokens = synline.split(",");
            }
        }else {
            Integer number = Integer.parseInt(synRawTokens[0]);
            ArrayList array1 = new ArrayList();
            array1.add(synRawTokens[1]);
            ArrayList array2 = new ArrayList();
            array2.add(number);
            if (table.containsKey(number)) {
                ArrayList<String> adder2 = table.get(number);
                adder2.addAll(array1);
                table.put(number, adder2);
            }else {
                table.put(number, array1);
            }
            if (invTable.containsKey(synRawTokens[1])) {
                ArrayList<Integer> adder = invTable.get(synRawTokens[1]);
                adder.addAll(array2);
                invTable.put(synRawTokens[1], adder);
            }else {
                invTable.put(synRawTokens[1], array2); 
            }  
            synline = syn.readLine(); 
            if (synline != null) {
                synRawTokens = synline.split(",");
            }
        } 
    }
    tree = new Digraph(count);
    while (hypoline != null) {
        int wordCount = hypoRawTokens.length;
        for (int i=1; i < wordCount; i++) {
         Integer number1 = Integer.parseInt(hypoRawTokens[0]);
         Integer number2 = Integer.parseInt(hypoRawTokens[i]);
         tree.addEdge(number1, number2);
     }
     hypoline = hypo.readLine();
     if (hypoline != null) {
        hypoRawTokens = hypoline.split(",");
    }
}

}

/* Returns true if NOUN is a word in some synset. */
public boolean isNoun(String noun) {
    if (invTable.containsKey(noun)) {
        return true;
    }
    return false;
}

/* Returns the set of all nouns. */
public Set<String> nouns() {
   Set<String> listNouns = new HashSet();
   Set<Integer> values = table.keySet();
   for (Integer value:values) {
    for (String strings:table.get(value)){
        listNouns.add(strings);
    }
}
return listNouns;
}

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word) {
      ArrayList<String> synonyms = new ArrayList<String>();
      ArrayList<Integer> magicNumber = new ArrayList<Integer>();
      magicNumber = invTable.get(word);
      Set<Integer> number = new HashSet<Integer>(magicNumber);
      for (Integer digit:magicNumber) {
        synonyms.addAll(table.get(digit));
    }
    Set<Integer> lst = GraphHelper.descendants(tree, number);
    ArrayList hyponymGetter = new ArrayList();
    for (Integer digits:lst) {
        hyponymGetter.addAll(table.get(digits));
    }
    hyponymGetter.addAll(synonyms);
    Set<String> words = new HashSet<String>(hyponymGetter);
    return words;
}   
} 