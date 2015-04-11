class Anagram(object):
    def __init__(self, word):
        self.word = word
        self.letters = sorted(word.lower())

    def match(self, others):
        anagrams = map(Anagram, others)
        return [a.word for a in filter(self.same, anagrams)]

    def same(self, other):
        not_same_word = self.word.lower() != other.word.lower()
        same_letters = self.letters == other.letters
        return not_same_word and same_letters
