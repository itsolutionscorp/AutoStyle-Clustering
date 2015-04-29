class Anagram(object):
    def __init__(self, word):
        self.word = word
        self.sword = sorted(list(word.lower()))
    
    def match(self, words):
        good = []
        for w in words:
            if w.lower() == self.word.lower():
                continue
            if self == Anagram(w):
                good.append(w)
        return good

    def __eq__(self, other):
        return self.sword == other.sword
                
