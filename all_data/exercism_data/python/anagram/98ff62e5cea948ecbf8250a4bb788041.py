from collections import Counter

class Anagram:
    def __init__(self, word):
        self.word = word.lower()

    def match(self, words):
        return [word for word in words if word != self.word and
                Counter(word.lower()) == Counter(self.word)]
