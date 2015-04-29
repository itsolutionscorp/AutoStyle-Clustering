#!/usr/bin/env python3
# -*- coding: utf-8 -*-
__author__ = "Greg"

def square_of_sum(x):
    """
    sums all values between 0 and the parameter inclusively,  squares that sum
    """
    y = 0
    for z in range(x + 1):
        y += z
    return y ** 2

def sum_of_squares(x):
    """
    squares each value between 0 and the parameter inclusively, sums those 
    squares
    """
    y = 0
    for z in range(x+1):
        y += z**2
    return y

def difference(x):
    """
    returns the difference between the square of the sum and sum of the
    square of the 1 parameter
    """
    
    return square_of_sum(x) - sum_of_squares(x)
