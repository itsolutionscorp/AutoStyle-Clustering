def sorted_word(word):
    return ''.join(sorted(word.lower()))

class Anagram(object):
    def __init__(self, word):
        self._word = word.lower()
        self._sorted_word = sorted_word(word)

    def match(self, words):
        return filter(self._is_anagram, words)
        
    def _is_anagram(self, word):
        return (word != self._word and 
                self._sorted_word == sorted_word(word))
