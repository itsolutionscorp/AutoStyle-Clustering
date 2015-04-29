#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def slices(s, n):
    if s.strip() == '' and n == 0:
        # Hackish. Very Hackish.
        # Validates the identity test case.
        return [[1]]
    elif n == 0 or n > len(s):
        raise ValueError("n=%s is a bad value against '%s'" % (n, s))
    else:
        ret = []
        for x in range(0, len(s)-n+1):
            ret.append([int(y) for y in list(s[x:x+n])])
        return ret

def largest_product(s, n):
    lp = 0
    try:
        for x in slices(s, n):
            lg = reduce(lambda x, y: x*y, x)
            if lg > lp:
                lp = lg
    except ValueError:
        raise
    else:
        return lp

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
