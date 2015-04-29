class Anagram(object):
  
    def __init__(self, word):
        self.word = word

    def match(self, anagram_candidates):
        return [(candidate) for candidate in anagram_candidates 
            if FormattedWord(candidate).is_anagram_of(FormattedWord(self.word))]


class FormattedWord(object):
  
    def __init__(self, word):
        self._standardized = self.standardize(word)
        self._sorted = self.sort(self._standardized)

    def is_anagram_of(self, other_word):
        return self._standardized != other_word._standardized and self._sorted == other_word._sorted

    def standardize(self, string):
        return string.lower()

    def sort(self, string):
        return "".join(sorted(string))
