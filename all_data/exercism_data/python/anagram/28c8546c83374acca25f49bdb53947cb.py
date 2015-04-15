class Anagram:

    def __init__(self, target_word):
        self.target_word = target_word.lower()

    def match(self, words):
        return filter(self._is_anagram, words)

    def _is_anagram(self, word):
        return self._has_same_letters(word.lower()) and self._is_different(word.lower())

    def _has_same_letters(self, word):
        return sorted(list(word)) == sorted(list(self.target_word))

    def _is_different(self, word):
        return word != self.target_word
