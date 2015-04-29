class Anagram(object):
  
    def __init__(self, word):
        self.word = word

    def match(self, anagram_candidates):
        return [(candidate) for candidate in anagram_candidates 
            if FormattedWord(candidate).is_anagram_of(self.word)]


class FormattedWord(object):
  
    def __init__(self, word):
        self.word = self.standardize(word)

    def is_anagram_of(self, other_word):
        other_word = self.standardize(other_word)
        return self.word != other_word and self.sort(self.word) == self.sort(other_word)

    def standardize(self, string):
        return string.lower()

    def sort(self, string):
        return "".join(sorted(string))
