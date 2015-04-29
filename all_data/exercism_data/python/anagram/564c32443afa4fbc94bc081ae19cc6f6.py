class Anagram:

    def __init__(self, w):
        self.word=w

    def match(self,cands):
         return [ m for m in cands if self._is_anagram(self.word, m) ]   

    def _is_anagram(self, s1, s2):
        if (len(s1)!=len(s2) or s1==s2):
            return False
        for s in s1:
            if( s1.lower().count(s) != s2.lower().count(s) ) :
                return False
        else:
            return True

def main():
    print(Anagram("listen").match(["inlets","google","listen","longword"]))

if __name__=="__main__":
    main()
