class Anagram(object):
    def __init__(self, word):
        self.word = word.lower()
        self.sig = sorted(self.word)

    def test(self, w):
        w = w.lower()
        return sorted(w) == self.sig and w != self.word

    def match(self, lst):
        return [ v for v in lst if self.test(v) ]
