class Anagram:

    def __init__(self, target_word):
        self.target_word = target_word.lower()

    def match(self, words):
        anagrams = []
        for word in words:
            if self._is_anagram(word.lower()):
                anagrams.append(word)
        return anagrams

    def _is_anagram(self, word):
        return self._has_same_letters(word) and self._is_different(word)

    def _has_same_letters(self, word):
        return sorted(list(word)) == sorted(list(self.target_word))

    def _is_different(self, word):
        return word != self.target_word
