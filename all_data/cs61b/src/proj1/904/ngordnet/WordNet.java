package ngordnet;
import edu.princeton.cs.algs4.DirectedDFS;
import java.util.TreeSet;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;
import java.util.Scanner;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

//ALL OF THIS IS MISC COMMENTED OUT CODE THAT I AM CHOOSING TO KEEP HERE JUST SO I CAN LOOK AT IT LATER IF I NEED
  //= synset.readLine(); // set synsetString equal to the next line in the file;
    //String synsetString = synset.readAllStrings(); //Turn the synset file into a set of strings.
    

    // to do super fast could write as String synsetNouns = synsetFilename.split("\\.")[1]

/* String synsetString = synset.readLine();
      while (synsetString != null){
        synsetNouns = sply
      }
      String synsetNouns = 

      x = 1;
      String synsetNouns = splitSynString[x]; //set synsetNouns to be the 1st element of the splitSynString
      while (synsetNouns != null){ //while synsetNouns, the nouns part of the list isn't null
        for (String x: synsetNouns.split(" ")){
          if (x.contains(noun)){
            return true;
          }
        }
      }
        x = x + 3
      }
      return false;
      */




public class WordNet {

    //protected In synset = new In(synsetFilename); // Read synset file using In
    //protected In hyponyms = new In(hyponymFilename); // Read hyponyms file using In
    private Scanner scannedSynset; 
    private Scanner scannedHyponyms;
    private String synsetString; //Create some new String called synsetString
    private int vertices;
    private Digraph dig;
    private ArrayList<String> hashArray = new ArrayList<String>(); //when you make ish in a method it tells java only allow ish in that method.
    private HashMap<Integer, ArrayList<String>> hashieMap = new HashMap<Integer, ArrayList<String>>();
    private Set<Integer> ID = new HashSet<Integer>();
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename){
      In inSynset = new In(synsetFilename);
      //HashMap<Integer, ArrayList<String>> hashieMap = new HashMap<Integer, ArrayList<String>>();
      //ArrayList<String> hashArray = new ArrayList<String>();

      //Scanner scannedSynset = new Scanner(synsetFilename);
      //HashMap<Integer, ArrayList<String>> hashieMap = new HashMap<Integer, ArrayList<String>>();
      // ArrayList<String> hashArray = new ArrayList<String>();

      vertices = 0;
        //System.out.println(inSynset.hasNextLine());
        while(inSynset.hasNextLine() == true){
          //System.out.println(inSynset.nextLine().split(", "))


          String[] currLine = inSynset.readLine().split(",");
          //System.out.println(currLine.length);
          //System.out.println(currLine[1]);

          String[] wordLine = ((currLine[1]).split(" "));
          
          int z = 0;
          //System.out.println(wordLine.length);
          //System.out.println(wordLine[0]);
          while(z < wordLine.length){
            hashArray.add(wordLine[z]);
            System.out.println(hashArray);
            z ++;
          }

          hashieMap.put(Integer.parseInt(currLine[0]), hashArray);
          vertices += 1;
        }

        dig = new Digraph(vertices);
        In inHyponyms = new In(hyponymFilename);
        //scannedHyponyms = new Scanner(hyponymFilename);
        
        while(inHyponyms.hasNextLine()){
          String[] idLine = inHyponyms.readLine().split(",");
          int v = Integer.parseInt(idLine[0]);
          ArrayList<Integer> secondaries = new ArrayList<Integer>();
          int i = 1;
          while(i < idLine.length){ //idLine[i] != null){
            secondaries.add(Integer.parseInt(idLine[i])); i ++;
          }
          for(Integer x : secondaries){
            int j = x.intValue();
            dig.addEdge(v, j);
          }
        }

          
        //   } // On Second thought, really not sure if I even need this dig creation here!

          // counter = 0
          // while(secondaries.get(counter) != null){
          //   dig.addEdge(v, secondaries.get(counter)); counter ++
        }



    //   In hyponyms = new In(hyponymFilename);//import to an In file so I can iterate through the thing
    //   String hyponymString = hyponyms.readLine();//read in the first line and make a String hyponymString;
    //   while(hyponymString != null){ //check that the line exists
    //     String[] splitHypString = hyponymString.split("\\,"); //split up the line by commas.
    //     int v = (int)splitHypString[0]; //take the first element, typecast as an int, set it to int v. (master)
    //     //new ArrayList<String>() secondaries = new ArrayList<String>(); //create an ArrayList of type <E> called secondaries
    //     ArrayList<String> secondaries = new ArrayList<String>();
    //     i = 1; //i = 1 so we can start with the 1st element, 0th will always be = v = master = hypernym
    //     while(splitHypString[i] != null){ //check that 1st exists
    //       secondaries.add(splitHypString[i]); //add it to the array
    //       i ++; //iterate and keep going through all of them.
    //     }
    //   }
    //   counter = 0; //new counter to iterate through the array List secondaries
    //   while(secondaries.get(counter) != null){ //is there something there?
    //     diggie.addEdge(v,(int) secondaries.get(counter)); //yes there is, add an edge with given params
    //     counter ++; //iterage ya hofmdkafdajfkl;!!!!!! :D
    //   }
    // return diggie;
    // }

// private int countEdges(Scanner file){         <------- This method is no longer needed, taken care of in constructor
//       while(file.hasNextLine() == true){
//         vertices += 1;
//       }
//     }

    // .readLine() -----> Reads the Next Line!

    /* Returns true if NOUN is a word in some synset. */

    public boolean isNoun(String noun){
      
      return (hashArray.contains(noun)); }//can I just do this. Instead of everything below


//       In synset = new In(synsetFilename); //Set synsetString = the first line of the synsetFile
//       String synsetString = synset.readLine();
//       while (synsetString != null){ //make sure that you're still looking at a synset data set and that it is not null
//         String[] splitSynString = synsetString.split("\\,"); // Split the synset line into three parts separated by commas: ID, nouns, definition
//         String synsetNouns = splitSynString[1]; // take the nouns part fron the splitSynString
//         for (String x: synsetNouns.split(" ")){ //split up the nouns by each space bar - so each individual word
//           if(x.contains(noun)){  //check if the noun you are looking for can be found in the nouns list!
//             return true; //yeah it's there? Sweet, return true
//           }
//         }
//         synsetString = synset.readLine; //A form of iteration, read the next line and set equal to sysnetString 
//       }
//       return false; //Damn, went through the entire file and couldn't find it in any line in the nouns section, then it must not be a noun...
//     } 
// // I think this all works as a back upf for checking if something isNoun but let me try doing it with just the HashMap

    /* Returns the set of all nouns. */

    public Set<String> nouns(){

      Set<String> x = new HashSet<String>();
      for(String noun : hashArray){
        x.add(noun);
      }
      return x;
    }


    //    String synsetString = synset.readLine(); //Set synsetString = the first line of the synsetFile
    //   while (synsetString != null){ //make sure that you're still looking at a synset data set and that it is not null
    //     String[] splitSynString = synsetString.split("\\,"); // Split the synset line into three parts separated by commas: ID, nouns, definition
    //     String synsetNouns = splitSynString[1]; // take the nouns part fron the splitSynString
    //     for (String x: synsetNouns.split(" ")){ //split up the nouns by each space bar - so each individual word
    //         return x; //return every noun
    //       }
    //     synsetString = synset.readLine; //A form of iteration, read the next line and set equal to sysnetString 
    //   }
    // }

    // private Digraph makeDigraph(int x){ //will obvs call it with the number of edges;
    //   Digraph diggie = new Digraph(x); //where x is da number of edges;
    //   In hyponyms = new In(hyponymFilename);//import to an In file so I can iterate through the thing
    //   String hyponymString = hyponyms.readLine();//read in the first line and make a String hyponymString;
    //   while(hyponymString != null){ //check that the line exists
    //     String[] splitHypString = hyponymString.split("\\,"); //split up the line by commas.
    //     int v = (int)splitHypString[0]; //take the first element, typecast as an int, set it to int v. (master)
    //     //new ArrayList<String>() secondaries = new ArrayList<String>(); //create an ArrayList of type <E> called secondaries
    //     ArrayList<String> secondaries = new ArrayList<String>();
    //     i = 1; //i = 1 so we can start with the 1st element, 0th will always be = v = master = hypernym
    //     while(splitHypString[i] != null){ //check that 1st exists
    //       secondaries.add(splitHypString[i]); //add it to the array
    //       i ++; //iterate and keep going through all of them.
    //     }
    //   }
    //   counter = 0; //new counter to iterate through the array List secondaries
    //   while(secondaries.get(counter) != null){ //is there something there?
    //     diggie.addEdge(v,(int) secondaries.get(counter)); //yes there is, add an edge with given params
    //     counter ++; //iterage ya hofmdkafdajfkl;!!!!!! :D
    //   }
    // return diggie;
    // }//At this point what have we created?
    //A digraph with the correct number of boxes, and arrows going from one box to the other.
    // So we have an empty digraph with numbers but no words.


//I DONT THINK I NEED THIS ANYMORE AFKL:FDJKLD:?            // while (synsetString != null){ //make sure that you're still looking at a synset data set and that it is not null
            //   String[] splitSynString = synsetString.split("\\,"); // Split the synset line into three parts separated by commas: ID, nouns, definition
            //   String synsetNouns = splitSynString[1]; // take the nouns part fron the splitSynString
            //   for (String x: synsetNouns.split(" ")){ //split up the nouns by each space bar - so each individual word
            //       return x; //return every noun
            //     }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */

    public Set<String> hyponyms(String word){
      Set<Integer> hypSet = new HashSet<Integer>();

      for (int x : hashieMap.keySet()){
       
        if (hashieMap.get(x).contains(word) == true) {
          ID.add(x);
        }
      }
    
    //Parse through the hashmap and get every key that contains the word

       Set<Integer> followers = new TreeSet<Integer>();
        int i = 0;
        
        //while(ID.get(i) != null){
          hypSet.addAll(GraphHelper.descendants(dig, ID));
          //i ++;
        //}
//add all of the ID numbers to followers which is a set of Integers, necessary for using descendants
        
        Set<String> finished = new HashSet<String>();

        for(Integer x : hypSet) {
          finished.addAll(hashieMap.get(x));
        }
        return finished;
      }

       
      


    //Ok so I know how to check if a word exists in a file


    // Check if the word exists in the synsetFilename;
    // if it does exist, find its ID number.
    // Using that ID number, find the same ID number in the hyponym file
    // once found in hyponym file, get all of the corresponding ID numbers.
    // from corresponding ID numbers, return all of the words from synsetFilename.
}







