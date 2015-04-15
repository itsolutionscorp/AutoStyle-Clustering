#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def sieve(n):
    nums = [x for x in range(2,n+1)]
    y = 2
    while y < ((n/2)+1):
        red = [x for x in range(y*2, n+1, y)]
        nums = sorted(list(set(nums)-set(red)))
        y += 1
    return nums

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
