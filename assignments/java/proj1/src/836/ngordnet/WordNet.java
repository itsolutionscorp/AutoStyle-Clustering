package ngordnet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.HashSet;

public class WordNet{
	private String synsetFilename, hyponymFilename, lines, lineh;
    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename){
		this.synsetFilename = synsetFilename;
		this.hyponymFilename = hyponymFilename;
	}

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun){
		for(String n: nouns()){
			if(n.equals(noun)){
				return true;
			}
		}
		return false;
	}

    /* Returns the set of all nouns. */
    public Set<String> nouns(){
		Set<String> panda = new HashSet<String>();
		In inS = new In(synsetFilename);
		boolean b = inS.hasNextLine();
		while(b){
			lines = inS.readLine();
			String[] rawTokens = lines.split(",");
            String[] tokens = rawTokens[1].split(" ");
			for(int i = 0; i < tokens.length; i++){
				if(!panda.contains(tokens[i])){
					panda.add(tokens[i]);
				}
			}
			b = inS.hasNextLine();
		}
		return panda;
	}

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
      * WORD. If WORD belongs to multiple synsets, return all hyponyms of
      * all of these synsets. See http://goo.gl/EGLoys for an example.
      * Do not include hyponyms of synonyms.
      */
    public Set<String> hyponyms(String word){
		Set<String> panda = new HashSet<String>();
		In inS = new In(synsetFilename);
		In inH = new In(hyponymFilename);
		String h;
		Set<String> synonyms = new HashSet<String>();
		boolean bH = inH.hasNextLine();
		boolean b = inS.hasNextLine();
		while(b){
			lines = inS.readLine();
			String[] rawTokens = lines.split(",");
			String[] tokens = rawTokens[1].split(" ");
			for(int i = 0; i < tokens.length; i++){
				if(tokens[i].equals(word)){
					h = rawTokens[0];
					synonyms.add(h);
					while(bH){
						lineh = inH.readLine();
						String[] tokenh = lineh.split(",");
						for(int j = 1; j < tokenh.length; j++){
							if(tokenh[0].equals(h) && !synonyms.contains(tokenh[j])){
								synonyms.add(tokenh[j]);
							}else if(tokenh[j].equals(h)){
								for(int k = j - 1; k > 0; k--){
									if(!synonyms.contains(tokenh[k])){
										synonyms.add(tokenh[k]);
									}
								}
								for(int l = j + 1; l < tokenh.length; l++){
									if(!synonyms.contains(tokenh[l])){
										synonyms.add(tokenh[l]);
									}
								}
							}
						}
						bH = inH.hasNextLine();
					}
				}
			}
			b = inS.hasNextLine();
		}
		inS = new In(synsetFilename);
		b = inS.hasNextLine();
		while(b){
			String lineS = inS.readLine();
			String[] rawTokenS = lineS.split(",");
            String[] tokenS = rawTokenS[1].split(" ");
			for(int m = 0; m < tokenS.length; m++){
				if(synonyms.contains(rawTokenS[0]) && !panda.contains(tokenS[m])){
					panda.add(tokenS[m]);
				}
			}
			b = inS.hasNextLine();
		}
		return panda;
	}
}