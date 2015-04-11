#!/usr/bin/env python

def difference(num):
    return (square_of_sum(num) - sum_of_squares(num))


def square_of_sum(num):
    return((sum(range(1,num+1)))**2)

def sum_of_squares(num):
    squares = []
    for i in range(1, num+1):
        squares.append(i**2)
    return sum(squares)
