#!/usr/bin/env python2
# -*- coding: utf-8 -*-

from string import ascii_lowercase as ail
from string import punctuation as pct
from string import whitespace as wht
from string import digits as dig
from string import maketrans as mt
import random as rnd
from uuid import uuid4 as seed

class Caesar(object):

    __alpha = ail
    __enc = __alpha[3:]+__alpha[0:3]

    @classmethod
    def encode(cls, s):
        self = cls
        tmp = s.lower().translate(None, pct+wht+dig)
        mtt = mt(self.__alpha, self.__enc)
        return tmp.translate(mtt)

    @classmethod
    def decode(cls, s):
        self = cls
        mtt = mt(self.__enc, self.__alpha)
        return s.translate(mtt)

class Cipher(object):

    __key = None
    __key_is_generated = False
    __dct = {0 : ail}

    def __init__(self, key=None):
        if key is None or len(key) == 0:
            self.__key = self.__key_gen()
            self.__key_is_generated = True
        else:
            if key == key.lower().translate(None, pct+wht+dig):
                self.__key = key
            else:
                raise TypeError("Bad key.")

    @property
    def key(self):
        if self.__key_is_generated:
            return self.__key
        else:
            raise Exception("You provided the key.")

    def __key_gen(self):
        lst = []
        if self.__key is None:
            for x in range(0, 4):
                nil = list(ail)
                rnd.seed(seed())
                rnd.shuffle(nil)
                lst += nil
                rnd.seed(seed())
                rnd.shuffle(lst)
            return ''.join(lst[0:100])
        else:
            raise Exception("Key already provided.")

    def __dct_gen(self):
        if len(self.__dct.keys()) == 1:
            for x in range(1, len(self.__dct[0])):
                self.__dct[x] = self.__dct[0][x:]+self.__dct[0][0:x]

    def __ed_key(self, s):
        if len(self.__key) < len(s):
            return self.__key*((len(s)/len(self.__key))+1)
        else:
            return self.__key

    def encode(self, s):
        self.__dct_gen()
        tmp = s.lower().translate(None, pct+wht+dig)
        tkey = self.__ed_key(tmp)
        tlst = list(tmp)
        for x in range(0, len(tlst)):
            dkey = self.__dct[0].index(tkey[x])
            tlst[x] = self.__dct[dkey][self.__dct[0].index(tlst[x])]
        return ''.join(tlst)

    def decode(self, s):
        self.__dct_gen()
        tkey = self.__ed_key(s)
        tlst = list(s)
        for x in range(0, len(tlst)):
            dkey = self.__dct[0].index(tkey[x])
            tlst[x] = self.__dct[0][self.__dct[dkey].index(tlst[x])]
        return ''.join(tlst)

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
