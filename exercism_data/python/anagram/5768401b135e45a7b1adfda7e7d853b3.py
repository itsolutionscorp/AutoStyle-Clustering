from pyramid.decorator import reify

def alphagram(word):
    return sorted(word.lower())

class Anagram(object):

    def __init__(self, word):
        self.word = word

    def match(self, candidates):
        return [word for word in candidates if self._is_anagram(word)]

    @reify
    def _lowercase(self):
        return self.word.lower()

    @reify
    def _alphagram(self):
        return alphagram(self.word)

    def _is_anagram(self, other):
        return self._matches(other) and not self._same_word(other)

    def _matches(self, other):
        return self._alphagram == alphagram(other)

    def _same_word(self, other):
        return self._lowercase == other.lower()
