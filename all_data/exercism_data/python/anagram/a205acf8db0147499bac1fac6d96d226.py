#!/usr/bin/python

class Anagram(object):
    def __init__(self, word):
        self.src_hash = self.hash(word)
        self.word = word

    def hash(self, word):
        val = 0
        for char in word:
            val = val + ord(char.lower())
        return val

    def match(self, words):
        result = []
        if not(words):
            return result
        for word in words:
            if self.src_hash == self.hash(word) and self.word.lower() != word:
                result.append(word)
        return result
