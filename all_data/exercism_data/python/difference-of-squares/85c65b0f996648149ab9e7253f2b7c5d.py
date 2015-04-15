# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 13:28:57 2014

@author: laegrim
"""

def difference(maximum):
    return square_of_sum(maximum) - sum_of_squares(maximum) 

def square_of_sum(maximum):
    return ((maximum + 1) * maximum / 2) ** 2

def sum_of_squares(maximum):
    return sum([i ** 2 for i in range(1, maximum + 1)])
