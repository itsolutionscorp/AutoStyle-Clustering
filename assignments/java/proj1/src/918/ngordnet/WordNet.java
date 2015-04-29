package ngordnet;
import edu.princeton.cs.introcs.In;
import java.util.Set;
import java.util.Map;
import ngordnet.GraphHelper;
import edu.princeton.cs.algs4.Digraph;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

public class WordNet {
    private ArrayList<String[]> nounlist = new ArrayList<String[]>(); 
    private Map google = new HashMap<String,ArrayList<Integer>>();
    private Digraph rls;
    private int synetpos;
    private String[] synetword; 
    private String synetdef;
    private ArrayList<String> defls = new ArrayList<String>();
    private int hypopos;
    private In inputsyn;
    private In inputhyp;
    private int vernum = 0;

	public WordNet(String synsetFilename, String hyponymFilename){
		inputsyn = new In(synsetFilename);
		inputhyp = new In(hyponymFilename);
		String[] buf;
		String line;
		if (inputsyn != null){
			while (inputsyn.hasNextLine()){
				vernum = vernum + 1;
				line = inputsyn.readLine();
				buf = line.split(",");
				synetpos = Integer.parseInt(buf[0]);
				synetword = buf[1].split(" ");
				synetdef = buf[2];
				nounlist.add(synetpos,synetword);
				defls.add(synetpos,synetdef);
				// nounlist finished, start google,which works perfectly// 
				for(int i=0; i<synetword.length; i++){
					if (google.containsKey(synetword[i])){ 
						ArrayList<Integer> tempy = (ArrayList<Integer>)google.get(synetword[i]);
						tempy.add(synetpos);
						}
					else{
						ArrayList<Integer> temp = new ArrayList<Integer>();
						temp.add(synetpos);
						google.put(synetword[i],temp);
						}
            	}	
	    	}
		}
	    inputhyp.close();
	    inputhyp.close();
        //reread the whole file// 
	    inputsyn = new In(synsetFilename);
		inputhyp = new In(hyponymFilename);
	    //finished synsets realted, start hyponyms which works perfectly  // 
		rls = new Digraph(vernum);
		while (inputhyp.hasNextLine()){
			line = inputhyp.readLine();
			buf = line.split(",");
		    hypopos = Integer.parseInt(buf[0]);
			for(int i=1; i<buf.length; i++){
				rls.addEdge(hypopos, Integer.parseInt(buf[i]));
			}
		}
    }

	
	public Set<String> hyponyms(String word){
		ArrayList<Integer> num = (ArrayList<Integer>)google.get(word);
		Set<Integer> rel = new TreeSet<Integer>();
		for (Integer n: num){
			rel.add(n);
		}
		Set<Integer> reachable = new TreeSet<Integer>();
		if (rls != null){
			reachable = GraphHelper.descendants(rls, rel);
		}
		Set<String> fin =  new TreeSet<String>();
		for (Integer haha: reachable){
			String[] selected = nounlist.get(haha);
			for (String sel: selected){
				fin.add(sel);	
			}
		}
   		return fin;

	}

	public boolean isNoun(String noun){
		Set<String> ls = new TreeSet<String>();
	   for (String[] cal: nounlist){
			for (String cma: cal ){
				ls.add(cma);
			}
		}
		if (ls.contains(noun)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Set<String> nouns(){
		Set<String> ls = new TreeSet<String>();
	    for (String[] cal: nounlist){
			for (String cma: cal ){
				ls.add(cma);
			}
		}
	    return ls;
		
	}


}