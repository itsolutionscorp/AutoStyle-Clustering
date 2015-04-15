from collections import defaultdict


class Anagram(object):

    """Class that find anagrams from given string."""

    def __init__(self, word):
        self._word = word
        self._word_len = len(word)

    def match(self, words):
        letters_count = defaultdict(int)
        for letter in list(self._word):
            letters_count[letter.lower()] += 1

        anagrams = []
        for word in words:
            anagram_found = True
            #Original word is stored because in some cases there are upcase
            #chars that are consider equals
            original_word = word
            word = word.lower()

            if len(word) == self._word_len:
                for letter in list(word):
                    count = word.count(letter)
                    if count != letters_count[letter]:
                        anagram_found = False
                if anagram_found and word != self._word:
                    anagrams.append(original_word)
        return anagrams
