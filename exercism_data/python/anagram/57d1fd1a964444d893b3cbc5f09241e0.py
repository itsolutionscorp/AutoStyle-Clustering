class Word(str):
    def same(self, word):
        return self.lower() == word.lower()
    
    def same_chars(self, word):
        return self.sorted_chars() == word.sorted_chars()
    
    def sorted_chars(self):
        return sorted(list(self.lower()))


class Anagram(Word):
    def match(self, words):
        words = map(Word, words)
        return [ w for w in words if not self.same(w) and self.same_chars(w) ]
