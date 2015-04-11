class Anagram:
    def __init__(self, word):
        self.word = word.lower()

    def match(self, candidates):
        return [c for c in candidates if self.is_anagram(c.lower())]

    def is_anagram(self, test_word):
        return test_word != self.word and sorted(test_word) == sorted(self.word)
