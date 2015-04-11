from itertools import permutations


class Anagram(object):
    """Calculate possible anagrams for given word."""
    def __init__(self, word):
        self._word = word.lower()

    def match(self, word_seq):
        """Returns sublist of possibe anagrams of word in given list."""
        anagram_set = {''.join(chars) for chars in permutations(self._word)}
        anagram_set.remove(self._word)

        return [word for word in word_seq if word.lower() in anagram_set]
