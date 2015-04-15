class Anagram(object):
    def __init__(self, word):
        self.word = Word(word)
    def match(self, words):
        return [w for w in words if self.word.is_anagram(Word(w))]


class Word(object):
    def __init__(self, word):
        self.original = word.lower()
        self.normalized = self.normalize(word)

    def __eq__(self, other):
        return self.original == other.original

    def __ne__(self, other):
        return self.original != other.original

    def is_anagram(self, other):
        return self != other and self.normalized == other.normalized

    @staticmethod
    def normalize(word):
        return ''.join(sorted(word.lower()))
