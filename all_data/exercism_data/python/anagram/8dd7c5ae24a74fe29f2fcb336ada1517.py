class Anagram(object):
    def __init__(self, word):
        self.word = word.lower()

    def match(self, words):
        anagram_condition = lambda x: x.lower() != self.word and sorted(self.word) == sorted(x.lower())
        return filter(anagram_condition, words)
