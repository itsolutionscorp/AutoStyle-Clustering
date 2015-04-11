import string
import re
from _ctypes import ArgumentError
import random

_alphabet =    "abcdefghijklmnopqrstuvwxyz"
_ceasarEncode = "defghijklmnopqrstuvwxyzabc"

class Caesar(object):
    def decode(self, toDecode):
        toDecode = toDecode.lower()
        regex = re.compile('[^a-zA-Z]')
        toDecode = regex.sub('', toDecode)
        return "".join([_alphabet[(_alphabet.index(l)-3)%26] for l in toDecode])

    def encode(self, toEncode):
        toEncode = toEncode.lower()
        regex = re.compile('[^a-zA-Z]')
        toEncode = regex.sub('', toEncode)
        return "".join([_alphabet[(_alphabet.index(l)+3)%26] for l in toEncode])

class Cipher(object):
    def __init__(self,key = ""):
        ## key is empty create new else use the given key 
        self.cipherKey = self.keyGenerator() if not key else key
        ## Check if string has upper or digits
        regex = re.compile('[^a-z]')
        ##
        if regex.sub('', key)!=key:
            raise ArgumentError("Only lower case alpha chars are permited!")
        self.cipherKey = key

    def decode(self,toDecode):
        # Used shifting formula
        # D(X) = P(X) - D(A,K) - I,    where
        #    I       = Index of letter in toEncode
        #    X       = Letter to encode
        #    P(X)    = Position of letter X in _alphabet
        #    D(A,K)  = Difference between position of letter K in cipherKey and position of letter A in _alphabet
        encoded = ""
        max= len(self.cipherKey)

        for i in range(len(toDecode)):
            if i>=max:
                encoded += toDecode[i:]
                break
            posInAlpha = _alphabet.index(toDecode[i])   # Position in alphabet of letter
            difInPos = (_alphabet.index(self.cipherKey[i])-i)%26    # Difference in positions
            encoded += _alphabet[(posInAlpha-difInPos-i)%26]
        return encoded

    def encode(self,toEncode):
        # Used shifting formula
        # E(X) = P(X) + D(A,K) + I,    where
        #    I       = Index of letter in toEncode
        #    X       = Letter to encode
        #    P(X)    = Position of letter X in _alphabet
        #    D(A,K)  = Difference between position of letter K in cipherKey and position of letter A in _alphabet
        decoded = ""
        max= len(self.cipherKey)

        for i in range(len(toEncode)):
            if i>=max:
                decoded += toEncode[i:]
                break
            posInAlpha = _alphabet.index(toEncode[i])
            difInPos = (_alphabet.index(self.cipherKey[i])-i)%26
            decoded += _alphabet[(difInPos+i+posInAlpha)%26]
        return decoded

    @staticmethod
    def keyGenerator():
        rang = random.randint(100,1000)
        return "".join([_alphabet[random.randint(0,25)] for i in range(rang)])
