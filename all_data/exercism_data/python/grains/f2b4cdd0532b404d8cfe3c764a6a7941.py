#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def on_square(sq):
    return 2**(sq-1)

def total_after(sq):
    return sum([on_square(x) for x in range(1,sq+1)])

if __name__ == '__main__':
    print '\n\tThis script is not meant to be used this way.\n'
