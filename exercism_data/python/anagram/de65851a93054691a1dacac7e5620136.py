class Anagram(str):
    def key(self, s):
        return ''.join(sorted(s.lower()))

    def is_anagram(self, word):
        return self.key(word) == self.key(self) and word != self

    def match(self, words):
        return filter(self.is_anagram, words)
