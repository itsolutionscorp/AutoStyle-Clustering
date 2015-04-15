#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def shard(var):
    return (x for x in xrange(1, var+1))

def square_of_sum(var):
    tot = 0
    for y in shard(var):
        tot += y
    return tot**2

def sum_of_squares(var):
    tot = 0
    for y in shard(var):
        tot += y**2
    return tot

def difference(var):
    return square_of_sum(var) - sum_of_squares(var)

if __name__ == '__main__':
    print 'This script is not meant to be used this way.'
