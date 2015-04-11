#!/usr/bin/env python
# encoding=utf-8


class Anagram(object):
    """Anagram class"""

    def __init__(self, testword):
        self.testword = testword.lower()

    def match(self, wordlist):
        """Return a list with words from wordlist, that are anagrams."""
        anagrams = [word for word in wordlist
        if sorted(self.testword) == sorted(word.lower())
        and self.testword != word.lower()]
        return anagrams
