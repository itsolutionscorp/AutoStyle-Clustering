"""Anagram selector.

An anagram is a word formed by rearranging the letters of a word (except the
original word itself).
"""

from collections import Counter


class Anagram(object):
    """Anagram selector."""

    def __init__(self, subject):
        """Create an anagram selector.

        :param subject: The original word.
        :type subject: str.
        """
        self.subject = subject
        self.subject_lowered = self.subject.lower()
        self.subject_counter = Counter(self.subject_lowered)

    def match(self, words):
        """Select out anagrams from a list of words.

        :param words: A list of candidate words.
        :type words: list.
        :returns: list -- a list of anagrams among candidate words.
        """
        anagrams = []
        for word in words:
            word_lowered = word.lower()
            word_counter = Counter(word_lowered)
            if word_counter == self.subject_counter and \
               word_lowered != self.subject_lowered:
                anagrams.append(word)
        return anagrams
