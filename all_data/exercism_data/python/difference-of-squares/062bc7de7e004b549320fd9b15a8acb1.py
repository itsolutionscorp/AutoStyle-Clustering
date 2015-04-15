# -*- coding: utf-8 -*-
"""
Created on Sun Oct  5 14:19:12 2014

@author: napopa
"""

def difference(n):
    return (square_of_sum(n) - sum_of_squares(n))
    
def square_of_sum(n):
    return (n*(n+1)/2)**2
    
    
def sum_of_squares(n):
    return n*(n+1)*(2*n+1)/6
