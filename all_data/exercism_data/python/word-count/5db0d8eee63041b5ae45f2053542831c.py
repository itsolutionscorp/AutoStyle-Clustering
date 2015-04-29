#!/usr/bin/python

class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase.lower()
        self.struct = dict()

    def word_count(self):
        # Strip punctuations
        letters = [x for x in self.phrase if x not in "!@#$%^&*(),.:;"]
        self.phrase = ''.join(letters)
        # Split to words
        words = self.phrase.split()
        # Count words
        for word in words:
            if self.struct.has_key(word):
                self.struct[word] = self.struct[word] + 1
            else:
                self.struct[word] = 1
        return self.struct
