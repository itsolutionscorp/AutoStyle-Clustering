# -*- coding: utf-8 -*-

def square_of_sum(value):
    return sum(range(value+1))**2

def difference(value):
    return abs(sum_of_squares(value) - square_of_sum(value))

def sum_of_squares(value):
    return sum([x**2 for x in range(value+1)])
