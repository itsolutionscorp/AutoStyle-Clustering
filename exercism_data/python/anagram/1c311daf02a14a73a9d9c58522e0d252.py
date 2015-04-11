class Anagram:
    def __init__(self,w):
        self.word=w

    def match(self,cands):
        
         matches = filter(lambda x: len(x)==len(self.word) and x!=self.word, cands)
         return [match for match in matches if is_anagram(self.word,match)]   

def is_anagram(s1,s2):
    """Assumes words equal length but not identical"""
    for s in s1:
        if(s1.lower().count(s)!=s2.lower().count(s)):
            return False
    else:
        return True

def main():
    print(Anagram("listen").match(["inlets","google","listen","longword"]))

if __name__=="__main__":
    main()
