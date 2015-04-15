#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def slices(s, n):
    if n == 0 or n > len(s):
        raise ValueError("n=%s is a bad value against '%s'" % (n, s))
    else:
        ret = []
        for x in range(0, len(s)-n+1):
            ret.append([int(y) for y in list(s[x:x+n])])
        return ret

if __name__ == '__main__':
    print 'This script is not meant to be used this way.'
