#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def square_of_sum(var):
    return sum([x for x in range(1, var+1)])**2

def sum_of_squares(var):
    return sum([x**2 for x in range(1, var+1)])

def difference(var):
    return square_of_sum(var) - sum_of_squares(var)

if __name__ == '__main__':
    print '%s' % ('This script is not meant to be used this way.')
