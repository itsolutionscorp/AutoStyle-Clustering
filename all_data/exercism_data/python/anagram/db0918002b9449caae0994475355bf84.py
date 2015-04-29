class Anagram(object):
    """Calculate possible anagrams for given word."""
    def __init__(self, word):
        self._word = word.lower()
        self._word_alphagram = sorted(self._word)

    def match(self, word_seq):
        """Return sublist of possibe anagrams of word in given list."""
        return [word for word in word_seq if self._is_anagram(word)]

    def _is_anagram(self, raw_word):
        word = raw_word.lower()

        return sorted(word) == self._word_alphagram and word != self._word
