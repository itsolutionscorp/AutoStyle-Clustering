from collections import Counter

class Anagram(object):
    def __init__(self, word):
        self._word = word

    def match(self, words):
        anagrams = []
        word_frequencies = Counter(self._word.lower())
        for word in words:
            other_frequencies = Counter(word.lower())
            if word_frequencies == other_frequencies and word != self._word:
                anagrams.append(word)
        return anagrams
