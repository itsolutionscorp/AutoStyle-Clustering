class Anagram:
    def __init__(self, word):
        self.word = word

    def match(self, x):
        return [a for a in x if self.anagrams(a, self.word)]

    def anagrams(self, a, b):
        sort = lambda x: ''.join(sorted(x.lower()))
        return a != b and sort(a) == sort(b)
