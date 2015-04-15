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
"""
    def test_square_of_sum_5(self):
        self.assertEqual(225, square_of_sum(5))

    def test_sum_of_squares_5(self):
        self.assertEqual(55, sum_of_squares(5))

    def test_difference_5(self):
        self.assertEqual(170, difference(5))
"""
