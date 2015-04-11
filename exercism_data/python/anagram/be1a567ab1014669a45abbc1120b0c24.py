class Anagram(object):
    def __init__(self, word):
        self.word = word
        self.sword = sorted(list(word.lower()))
    
    def match(self, words):
        good = []
        for w in words:
            if w.lower() == self.word.lower():
                continue
            if sorted(list(w.lower())) == self.sword:
                good.append(w)
        return good
                
