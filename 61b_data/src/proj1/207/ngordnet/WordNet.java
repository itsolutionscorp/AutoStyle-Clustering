package ngordnet;
import ngordnet.GraphHelper;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.*;
import java.io.*;



public class WordNet {
  private Map<Integer, String> synsetMap = new HashMap<Integer, String>();
  private Set setnouns = new TreeSet<String>();
  private Set sethyponyms = new TreeSet<String>();  
  private int synsize;
  private int hypsize;
  private Digraph dig;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename){

      // read synset and make map. read hyponym and make digraph
      In synlines = new In(synsetFilename);
      In synlines2 = new In(synsetFilename);
      int synlinecount = 0;
      while(synlines.readLine() != null){
        synlinecount++;
        synsize++;
      }
      for (int k = 0; k < synlinecount; k++){
        String[] syndata = synlines2.readLine().split(",");
        synsetMap.put(Integer.parseInt(syndata[0]), syndata[1]);

      } //works up to here
      
      Digraph dig = new Digraph(synsize);

      //make digraph
      In hyplines = new In(hyponymFilename);
      In hyplines2 = new In(hyponymFilename);
      int hyplinecount = 0;
      while(hyplines.readLine() != null){
        hyplinecount++;
        hypsize++;
      }
      for (int i = 0; i < hyplinecount; i++){
        String[] hypdata = hyplines2.readLine().split(",");

        for (int j = 1; j < hypdata.length; j++){


          dig.addEdge(Integer.parseInt(hypdata[0]), Integer.parseInt(hypdata[j]));
        //check dig
        }
      }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun){

      //can use key and size for loop.
      for (int i = 0; i < synsize-1; i++){
        if (synsetMap.get(i).contains(noun)){
          return true;
        }
      }
      return false;

    }

    /* Returns the set of all nouns. */
    public Set<String> nouns(){
      //read synsetMap and make Set (iteration) of keys
      for (int i = 0; i < synsize-1; i++){
        setnouns.add(synsetMap.get(i));
      }
      return setnouns;
    }

    /* Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word){
      //create synsetID's
      Set<Integer> synsetIDs = new TreeSet<Integer>();
      Set<String> hyponyms = new TreeSet<String>(); 
      Set<Integer> five = new TreeSet<Integer>();
      



      for (int i = 0; i < synsize; i++){
        if ((synsetMap.get(i)).contains(word)){
          synsetIDs.add(i);
        }
      }
      Object[] x = synsetIDs.toArray();
      five.add((Integer) x[0]);
      System.out.println(dig.getClass().getName());
      sethyponyms = GraphHelper.descendants(dig, five);
 //ID's only //

      return sethyponyms;
          //search digraph

      }
    }

      //find "word" in digraph and return stuff?

