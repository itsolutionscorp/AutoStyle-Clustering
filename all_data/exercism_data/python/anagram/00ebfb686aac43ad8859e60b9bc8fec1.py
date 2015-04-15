from collections import Counter


class Anagram(object):
    def __init__(self, word):
        self.word = word
        self.word_signature = self.get_signature(word)

    def match(self, candidates):
        return [c for c in candidates if self.is_anagram(c)]

    def is_anagram(self, candidate):
        if candidate == self.word:
            # Word is not anagram to itself
            return False

        return self.word_signature == self.get_signature(candidate)

    def get_signature(self, word):
        return Counter(list(word.lower()))
