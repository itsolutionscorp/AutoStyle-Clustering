# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 12:49:14 2014

@author: johann
"""

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
    
def square_of_sum(num):
    return sum(range(num+1))**2
  
def sum_of_squares(num):
    return sum(i**2 for i in range(num+1))

    
