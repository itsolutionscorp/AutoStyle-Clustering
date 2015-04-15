from string import ascii_lowercase,ascii_uppercase,digits,whitespace,punctuation
from random import randrange
from math import ceil

class Caesar(object):
    def encode(self,t):
        return t.lower().translate(
            str.maketrans(ascii_lowercase,
                          ascii_lowercase[3:]+'abc',
                          digits + punctuation + whitespace))
    def decode(self,t):
        return t.translate(
            str.maketrans(ascii_lowercase[3:]+'abc',
                          ascii_lowercase))

class Cipher(object):
    def __init__(self,key=None):
        if key:
            for character in key:
                if (character in ascii_uppercase or
                    character in digits or
                    character in whitespace):
                    raise Exception('Key should be alphabetical and lowercase.')
            self.key = key
        else:
            self.key = ''
            for i in range(0,100):
                self.key += ascii_lowercase[randrange(0,26)]
            
    def encode(self,text):
        if len(self.key) < len(text):
            self.key *= ceil(len(text) / len(self.key))
        result = ''
        for i in range(len(text)):
            result += (2*ascii_lowercase)[ascii_lowercase.index(text[i]) +
                                          ascii_lowercase.index(self.key[i])]
        return result

    def decode(self,text):
        if len(self.key) < len(text):
            self.key *= ceil(len(text) / len(self.key))
        result = ''
        for i in range(len(text)):
            result += (ascii_lowercase)[ascii_lowercase.index(text[i]) -
                                        ascii_lowercase.index(self.key[i])]
        return result

