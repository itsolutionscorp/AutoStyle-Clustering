__author__ = 'jos'
from collections import Counter

class Anagram():
    def __init__(self, word):
        self.word = word


    def match(self, possible_anagrams):
        assert isinstance(possible_anagrams, list)

        #
        if self.word in possible_anagrams:
            possible_anagrams.remove(self.word)

        letter_count = Counter(list(self.word.lower()))

        anagram_list = []
        for anagram in possible_anagrams:
            if letter_count == Counter(list(anagram.lower())):
                anagram_list.append(anagram)

        return anagram_list
