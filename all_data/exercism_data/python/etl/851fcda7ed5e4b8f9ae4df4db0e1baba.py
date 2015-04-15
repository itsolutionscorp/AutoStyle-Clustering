#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def transform(dct):
    outp = {}
    for x in dct.keys():
        if type(dct[x]) == str:
            dct[x] = list(dct[x])
        for item in dct[x]:
            outp[item.lower()] = x
    return outp

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
