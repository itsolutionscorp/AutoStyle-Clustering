# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 17:11:06 2014
"""

def difference(input_num):
    return square_of_sum(input_num) - sum_of_squares(input_num)

def square_of_sum(input_num):
    total = 0
    for i in range(input_num):
        total += i+1
    total = total**2
    return total

def sum_of_squares(input_num):
    total = 0
    for i in range(input_num):
        total += (i+1)**2
    return total

