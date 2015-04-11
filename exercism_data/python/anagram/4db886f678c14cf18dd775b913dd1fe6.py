class Anagram(object):
  
    def __init__(self, word):
        self.word = word

    def match(self, anagram_candidates):
        return [(candidate) for candidate in anagram_candidates 
            if FormattedWord(candidate).is_anagram_of(FormattedWord(self.word))]


class FormattedWord(object):
  
    def __init__(self, word):
        self.value = self.standardize(word)

    def is_anagram_of(self, other_word):
        return self.value != other_word.value and self.sort(self.value) == self.sort(other_word.value)

    def standardize(self, string):
        return string.lower()

    def sort(self, string):
        return "".join(sorted(string))
