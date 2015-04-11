# -*- coding: utf-8 -*-
"""
Created on Fri Oct 03 12:20:14 2014

@author: Dan
"""

def square_of_sum(number):
    square = sum(range(number + 1))
    return square**2

def sum_of_squares(number):
    square = sum(x**2 for x in range(number+1))
    return square
    
def difference(number):
    return  square_of_sum(number)  - sum_of_squares(number)      
