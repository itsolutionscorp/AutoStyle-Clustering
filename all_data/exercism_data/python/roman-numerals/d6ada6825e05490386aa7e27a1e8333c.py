#!/usr/bin/env python2
# -*- coding: utf-8 -*-

RNS = { 1 : "I", 4 : "IV", 5 : "V", 9 : "IX", 10 : "X",
        40 : "XL", 50 : "L", 90 : "XC", 100 : "C",
        400 : "CD", 500 : "D", 900 : "CM", 1000 : "M"}

def numeral(arabic):
    arab = arabic
    outp = ''
    while arab > 0:
        tmp = max([x for x in RNS.keys() if x <= arab])
        outp += RNS[tmp]
        arab -= tmp
    return outp

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
