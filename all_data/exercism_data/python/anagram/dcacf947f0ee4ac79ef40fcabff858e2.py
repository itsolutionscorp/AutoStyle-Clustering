from collections import Counter

class Word(str):
    def __init__(self, word):
        super(Word, self).__init__(word)
        self.chars = Counter(self.lower())
    
    def same(self, word):
        return self.lower() == word.lower()
    
    def same_chars(self, word):
        return self.chars == word.chars


class Anagram(Word):
    def match(self, words):
        words = map(Word, words)
        return [ w for w in words if not self.same(w) and self.same_chars(w) ]
