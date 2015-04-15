#!/usr/bin/env python2
# -*- coding: utf-8 -*-

LTRS = {'A, E, I, O, U, L, N, R, S, T' : 1,
        'D, G' : 2, 'B, C, M, P' : 3,
        'F, H, V, W, Y' : 4, 'K' : 5,
        'J, X' : 8, 'Q, Z' : 10, '' : 0}

def score(wrd):
    if wrd.strip(' \t\r\n') == '':
        return 0
    dct = {}
    for x in LTRS.keys():
        c = x.translate(None, " ").split(',')
        for y in c:
            dct[y.upper()] = LTRS[x]
    return sum([dct[x.strip()] for x in list(wrd.upper().strip(' \t\r\n'))])

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
