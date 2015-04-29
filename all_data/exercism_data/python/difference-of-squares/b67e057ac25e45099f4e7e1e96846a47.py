# -*- coding: utf-8 -*-
"""
Created on Fri Sep 26 09:59:28 2014

@author: Blair
"""

def sum_of_squares(digit):
    result = 0
    for number in range(0, digit+1):
        result += number**2
    return result
        
def square_of_sum(digit):
    result = 0
    for number in range(0, digit+1):
        result += number
    return result**2
    
def difference(digit):
    return square_of_sum(digit) - sum_of_squares(digit)
    
