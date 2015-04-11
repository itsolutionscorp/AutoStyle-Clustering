class Anagram(object):
    def __init__(self, word):
        self.word = Word(word)
    def match(self, words):
        return [w for w in words if self.word.has_anagram(w)]


class Word(object):
    def __init__(self, word):
        self.original = word.lower()
        self.normalized = self.normalize(word)

    def is_same(self, other):
        return self.original == other.lower()

    def has_anagram(self, other):
        is_different = not self.is_same(other)
        is_anagram = self.normalize(other) == self.normalized
        return is_different and is_anagram

    @staticmethod
    def normalize(word):
        return ''.join(sorted(word.lower()))
