package ngordnet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.HashMap;
import java.util.Stack;
import java.util.Set;
import java.util.HashSet;




public class WordNet {
    private Digraph g;
    private HashMap<Integer, String[]> vertexWord;
    private HashMap<Integer, int[]> vertexVertex;


    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        In synsetInput = new In(synsetFilename); /* "./wordnet/synsets11.txt"*/
        In hyponymInput1 = new In(hyponymFilename); /*"./wordnet/hyponyms11.txt" */
        In hyponymInput2 = new In(hyponymFilename);
        In hyponymInput3 = new In(hyponymFilename);
        String delims = "[,]";
        int max = 0;
        while (hyponymInput1.hasNextLine()) {
            String line = hyponymInput1.readLine();
            String[] tokens = line.split(delims);
            for (int i = 1; i < tokens.length; i++) {
                if (Integer.parseInt(tokens[i]) > max) {
                    max = Integer.parseInt(tokens[i]);
                }
            }
        }
        g = new Digraph(max + 1);
        while (hyponymInput2.hasNextLine()) {
            String line = hyponymInput2.readLine();
            String[] tokens = line.split(delims);
            int firstVertex = Integer.parseInt(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                g.addEdge(firstVertex, Integer.parseInt(tokens[i]));
            }
        }
        vertexWord = new HashMap<Integer, String[]>();
        while (synsetInput.hasNextLine()) {
            String line = synsetInput.readLine();
            String[] tokens = line.split(delims);
            int number = Integer.parseInt(tokens[0]);
            String[] word = tokens[1].split("[ ]");
            vertexWord.put(number, word);
        }
        vertexVertex = new HashMap<Integer, int[]>();
        while (hyponymInput3.hasNextLine()) {
            String line = hyponymInput3.readLine();
            String[] tokens = line.split(delims);
            Integer firstNumber = Integer.parseInt(tokens[0]);
            
            int[] restNumbers = new int[tokens.length - 1];
            for (int i = 0; i < tokens.length - 1; i += 1) {
                restNumbers[i] = Integer.parseInt(tokens[i + 1]);
            }
            if (vertexVertex.containsKey(firstNumber)) {
                // int[] concat = ArrayUtils.addAll(restNumbers, vertexVertex.get(firstNumber));
                int[] concat = new int[restNumbers.length + vertexVertex.get(firstNumber).length];
                int[] val = vertexVertex.get(firstNumber);
                System.arraycopy(val, 0, concat, 0, val.length);
                System.arraycopy(restNumbers, 0, concat, val.length, restNumbers.length);
                vertexVertex.put(firstNumber, concat);
            } else {
                vertexVertex.put(firstNumber, restNumbers);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        boolean test = false;
        for (Integer key: vertexWord.keySet()) {
            for (int count = 0; count < vertexWord.get(key).length; count += 1) {
                if (vertexWord.get(key)[count].equals(noun)) {
                    test = true;
                }
            }
        }
        return test;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        HashSet<String> all = new HashSet<String>();
        for (Integer key: vertexWord.keySet()) {
            for (int count = 0; count < vertexWord.get(key).length; count++) {
                all.add(vertexWord.get(key)[count]);
            }
        }
        return all;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself. */
    public Set<String> hyponyms(String word) {
        Stack<Integer> realStack = new Stack<Integer>();
        Stack<Integer> sideStack = new Stack<Integer>();
        HashSet<String> total = new HashSet<String>();
        

        for (Integer key: vertexWord.keySet()) {
            for (int count = 0; count < vertexWord.get(key).length; count++) {
                if (word.equals(vertexWord.get(key)[count])) {
                    realStack.push(key);
                    if (vertexVertex.get(key) != null) {
                        for (int j = 0; j < vertexVertex.get(key).length; j++) {
                            sideStack.push(vertexVertex.get(key)[j]);
                        }
                    }
                    

                }
            }
        }
        while (!sideStack.empty()) {
            int placeHolder = sideStack.pop();
            realStack.push(placeHolder);
            if (vertexVertex.get(placeHolder) != null) {
                for (int k = 0; k < vertexVertex.get(placeHolder).length; k++) {
                    sideStack.push(vertexVertex.get(placeHolder)[k]);
                }
            }
        }
        while (!realStack.empty()) {
            int placeHolder = realStack.pop();
            for (int j = 0; j < vertexWord.get(placeHolder).length; j++) {
                total.add(vertexWord.get(placeHolder)[j]);
            }
        }
        return total;
    }

    // public static void main(String[] args) {
    //     WordNet wn = new WordNet("./wordnet/synsets14.txt", "./wordnet/hyponyms14.txt");
    //      // These should all print true. 
    //     // System.out.println("Hypnoyms of event:");

    //     // wn.hyponyms("event");
    //     for (String noun : wn.hyponyms("event")) {
    //         System.out.println(noun);
    //     }   
        
        // In synset_input = new In("./wordnet/synsets11.txt");
        // In hyponym_input1 = new In("./wordnet/hyponyms11.txt");
        // In hyponym_input2 = new In("./wordnet/hyponyms11.txt");
        // In hyponym_input3 = new In("./wordnet/hyponyms11.txt");
        
        // String delims = "[,]";
        // int max = 0;
        // while (hyponym_input1.hasNextLine()) {
        //     String line = hyponym_input1.readLine();
        //     String[] tokens = line.split(delims);
        //     for (int i = 1; i < tokens.length; i++) {
        //         if (Integer.parseInt(tokens[i]) > max) {
        //             max = Integer.parseInt(tokens[i]);
        //         }
        //     }
     
        // }
     
        
        // Digraph g = new Digraph(max + 1);
        
        // while (hyponym_input2.hasNextLine()) {
        //     String line = hyponym_input2.readLine();
        //     String[] tokens = line.split(delims);
        //     int first_vertex = Integer.parseInt(tokens[0]);
        //     for (int i = 1; i < tokens.length; i++) {
        //         g.addEdge(first_vertex, Integer.parseInt(tokens[i]));
        //     }
     
        // }
        // HashMap<Integer, String[]> vertex_word = new HashMap<Integer, String[]>();
        // while (synset_input.hasNextLine()) {
        //     String line = synset_input.readLine();
        //     String[] tokens = line.split(delims);
        //     int number = Integer.parseInt(tokens[0]);
        //     String[] word = tokens[1].split("[ ]");
        //     vertex_word.put(number, word);

        // }
        
        // String keyword = "increase";
        // boolean result = false;
        // for (Integer key: vertex_word.keySet()) {
        //     for (int count = 0; count < vertex_word.get(key).length; count++) {
        //         if (vertex_word.get(key)[count].equals(keyword)) {
        //             result = true;
        //         }
        //     }
        // }
        // System.out.println(result);

        // HashMap<Integer, int[]> vertex_vertex = new HashMap<Integer, int[]>();
        // while (hyponym_input3.hasNextLine()) {
        //     String line = hyponym_input3.readLine();
        //     String[] tokens = line.split(delims);
        //     Integer first_number = Integer.parseInt(tokens[0]);
        //     int[] rest_numbers = new int[tokens.length - 1];
        //     for (int i = 0; i < tokens.length-1; i ++) {
        //         rest_numbers[i] = Integer.parseInt(tokens[i+1]);
        //     }
        //     vertex_vertex.put(first_number, rest_numbers);

        // }

    //     // Stack<Integer> real_stack = new Stack<Integer>();
    //     // Stack<Integer> side_stack = new Stack<Integer>();
    //     Stack<Integer> real_stack = new Stack<Integer>();
    //     Stack<Integer> side_stack = new Stack<Integer>();
    //     HashSet<String> total = new HashSet<String>();
        

    //     for (Integer key: vertex_word.keySet()) {
    //         for (int count = 0; count < vertex_word.get(key).length; count++) {
    //             if (keyword.equals(vertex_word.get(key)[count])) {
    //                 real_stack.push(key);
    //                 for (int j = 0; j < vertex_vertex.get(key).length; j++) {
    //                     side_stack.push(vertex_vertex.get(key)[j]);
    //                 }

    //             }
    //         }
    //     }
    //     while (!side_stack.empty()) {
    //         int place_holder = side_stack.pop();
    //         real_stack.push(place_holder);
    //         if (vertex_vertex.get(place_holder) != null) {
    //             for (int k = 0; k < vertex_vertex.get(place_holder).length; k++) {
    //                     real_stack.push(vertex_vertex.get(place_holder)[k]);
    //             }
    //         }
    //     }
    //     while (!real_stack.empty()) {
    //         int place_holder = real_stack.pop();
    //         for (int j = 0; j < vertex_word.get(place_holder).length; j++) {
    //             total.add(vertex_word.get(place_holder)[j]);
    //         }
    //     }

    //     System.out.println(total.size());
    //     System.out.println(total.contains("increase"));
    //     System.out.println(total.contains("jump"));
    //     System.out.println(total.contains("leap"));
    //     System.out.println(total.contains("augmentation"));
        
        
           
    // } 
       
}
