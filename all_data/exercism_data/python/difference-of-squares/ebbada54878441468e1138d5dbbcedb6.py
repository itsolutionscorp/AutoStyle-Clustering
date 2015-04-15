#!/usr/bin/env python3
""" module difference of squares for exercism.io programming training"""

import numpy

def square_of_sum(last):
    return numpy.arange(last+1).sum()**2


def sum_of_squares(last):
    return (numpy.arange(last+1)**2).sum()
    

def difference(last):
    return square_of_sum(last) - sum_of_squares(last)
