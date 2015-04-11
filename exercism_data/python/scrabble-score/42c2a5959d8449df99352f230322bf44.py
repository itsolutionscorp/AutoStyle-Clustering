#!/usr/bin/env python

VALUES = {"AEIOULNRST": 1,
          "DG": 2,
          "BCMP": 3,
          "FHVWY": 4,
          "K": 5,
          "JX": 8,
          "QZ": 10}

class Word:
    def __init__(self, word):
        self.word = word.upper()
    
    def score(self):
        counter = 0
        for letter in self.word:
            for group, value in VALUES.items():
                if letter in group:
                    counter += value
        return counter
