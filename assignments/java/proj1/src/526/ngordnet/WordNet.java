package ngordnet;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.StringTokenizer;
import edu.princeton.cs.introcs.In;

public class WordNet {
    private ArrayList<ArrayList<String>> map;
    
    public WordNet(String one, String two) {
        map = new ArrayList<ArrayList<String>>();
        In ins = new In(one);
        In inh = new In(two);
        String temp = ins.readLine();
        while (temp != null) {
            StringTokenizer s = new StringTokenizer(temp, ",");
            String s1 = s.nextToken();
            String s2 = s.nextToken();
            map.add(Integer.parseInt(s1), new ArrayList<String>());
            map.get(Integer.parseInt(s1)).add(null);
            map.get(Integer.parseInt(s1)).set(0, s2);
            temp = ins.readLine();
        }
        temp = inh.readLine();
        while (temp != null) {
            StringTokenizer s = new StringTokenizer(temp, ",");
            String key = s.nextToken();
            while (s.hasMoreTokens()) {
                String s1 = s.nextToken();
                String s2 = map.get(Integer.parseInt(s1)).get(0);
                map.get(Integer.parseInt(key)).add(s2);
            }
            temp = inh.readLine();
        }
    }
    public boolean isNoun(String name) {
        for (ArrayList<String> blah : map) {
            StringTokenizer s = new StringTokenizer(blah.get(0), " ");
            while (s.hasMoreTokens()) {
                if (name.equals(s.nextToken())) {
                    return true;
                }
            }
        }
        return false;
    }
    public java.util.Set<String> nouns() {
        java.util.Set<String> ret = new TreeSet<String>();
        for (ArrayList<String> blah : map) {
            //System.out.println(blah);
            StringTokenizer s = new StringTokenizer(blah.get(0), " ");
            while (s.hasMoreTokens()) {
                ret.add(s.nextToken());
            }
        }
        return ret;
    }
    public java.util.Set<String> hyponyms(String name) {
        java.util.Set<String> ret = new TreeSet<String>();
        int counter = 0;
        ArrayList<String> hypocheck = new ArrayList<String>();
        for (ArrayList<String> lst : map) {
            int pass = 0;
            StringTokenizer s = new StringTokenizer(lst.get(0), " ");
            while (s.hasMoreTokens()) {
                if (s.nextToken().equals(name)) {
                    pass = 1;
                }
            }
            //list all hyponyms of current list
            if (pass == 1) {
                int count = 0;
                for (String str : lst) {
                    StringTokenizer w = new StringTokenizer(str, " ");
                    while (w.hasMoreTokens()) {
                        String reu = w.nextToken();
                        if (count == 0) {
                            ret.add(reu);
                        } else if (w.hasMoreTokens()) {
                            ret.add(reu);
                        } else {
                            ret.add(reu);
                            java.util.Set<String> tex = hyponyms(reu);
                            ret.addAll(tex);
                        }
                    }
                    count = 1;
                }
            }
        }
        return ret;
    }
}
