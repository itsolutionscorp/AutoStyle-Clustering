class Anagram(object):
    '''
    Given a word and a list of possible anagrams, returns
    a list of all matching anagrams.
    '''
    def __init__(self, word):
        self.word = word

    def match(self, anagrams):
        word_alphagram = self._alphagram(self.word)
        return [anagram for anagram in anagrams if anagram != self.word
                and self._alphagram(anagram) == word_alphagram]

    def _alphagram(self, word):
        return sorted(list(word.lower()))
