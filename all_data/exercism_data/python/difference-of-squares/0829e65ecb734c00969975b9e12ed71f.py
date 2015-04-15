# -*- coding: utf-8 -*-
# By Nico Gevers (@gevious)

def sum_of_squares(num):
    s = 0
    for i in range(1, num+1):
        s += i ** 2
    return s

def square_of_sum(num):
    s = 0
    for i in range(num+1):
        s += i
    return s ** 2

def difference(num):
    return square_of_sum(num) - sum_of_squares(num)
