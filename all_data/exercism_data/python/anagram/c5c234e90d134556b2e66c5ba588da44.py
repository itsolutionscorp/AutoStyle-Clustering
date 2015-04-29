from collections import Counter


class Anagram(object):
    def __init__(self, word):
        self.source_word = word.lower()
        self.source_matcher = Counter(self.source_word)

    def match(self, prospects):
        """
        Compare the source word against the list of prospects and return found anagrams
        """
        anagrams = []
        for candidate in prospects:
            if candidate.lower() == self.source_word:
                continue
            if Counter(candidate.lower()) == self.source_matcher:
                anagrams.append(candidate)
        return anagrams
