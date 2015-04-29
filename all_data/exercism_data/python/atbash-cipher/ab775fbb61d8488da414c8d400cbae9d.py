#!/usr/bin/env python3
""" module atbash cipher for exercism.io programming training"""

def endecode(x):
    table = str.maketrans('abcdefghijklmnopqrstuvwxyz0123456789', 
                          'zyxwvutsrqponmlkjihgfedcba0123456789', 
                          ' .,')
    return x.lower().translate(table)


def encode(input):
    """ encodes input """
    out = endecode(input)
    # for every 5 symbols, insert a ' '
    out = ''.join([out[l] if (l+1)%5!=0 else ''.join([out[l], ' '])  for l in range(len(out))]).strip()
    return out 


def decode(input):
    """ decodes input """
    return endecode(input)
