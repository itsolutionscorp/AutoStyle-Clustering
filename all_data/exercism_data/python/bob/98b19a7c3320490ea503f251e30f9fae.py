#!/usr/bin/env python
# -*- coding: utf-8 -*-


class Bob(object):
    def __init__(self):
        pass

    def hey(self, sentence):
        self.sentence = Sentence(sentence)
        if self.sentence.isempty():
            return "Fine. Be that way!"
        if self.sentence.isscreaming():
            return "Woah, chill out!"
        if self.sentence.isquestion():
            return "Sure."
        return "Whatever."


class Sentence(object):
    def __init__(self, sentence):
        self.sentence = sentence

    def isempty(self):
        if self.sentence is None:
            return True
        if all(char == " " for char in self.sentence):
            return True
        return False

    def isscreaming(self):
        if self.sentence.upper() == self.sentence:
            return True

    def isquestion(self):
        if self.sentence.endswith("?"):
            return True
