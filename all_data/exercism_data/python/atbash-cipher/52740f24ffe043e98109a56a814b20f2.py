#!/usr/bin/env python2
# -*- coding: utf-8 -*-

import string

FWD = list('abcdefghijklmnopqrstuvwxyz')
BKW = FWD[::-1]

def decode(s):
    var = list(''.join(s.split()))
    return ''.join([FWD[BKW.index(x)] for x in var])

def encode(s):
    var = list(''.join(s.lower().translate(None, string.punctuation).split()))
    tmp = ''.join([BKW[FWD.index(x)] if x in BKW else x for x in var])
    y = 0
    ret = ''
    while y < len(tmp)/5:
        ret += tmp[y*5:(y*5)+5]+' '
        y += 1
    if len(tmp) % 5 != 0:
        ret += tmp[y*5:]
    return ret.strip()

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
