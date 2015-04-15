class Anagram(object):
  
    def __init__(self, word):
        self.word = word

    def match(self, anagram_candidates):
        formatted_word = FormattedWord(self.word)
        return [candidate for candidate in anagram_candidates 
            if FormattedWord(candidate).is_anagram_of(formatted_word)]


class FormattedWord(object):
  
    def __init__(self, word):
        self._standardized = word.lower();
        self._sorted = "".join(sorted(self._standardized))

    def is_anagram_of(self, other_word):
        return self._standardized != other_word._standardized and self._sorted == other_word._sorted
