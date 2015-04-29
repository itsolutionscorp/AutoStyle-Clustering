class Anagram(object):
    def __init__(self, word):
        self.word = word
    
    def match(self, words):
        good = []
        for w in words:
            if w == self.word or len(w) != len(self.word):
                continue
            wlist = list(w.lower())
            for c in self.word.lower():
                if c in wlist:
                    del wlist[wlist.index(c)]
            if not wlist:
                good.append(w)
        return good
                
