#!/usr/bin/env python

squares = dict.fromkeys(range(1, 65), 1)

def on_square(n):
    calculate_squares(n)
    return squares[n]

def total_after(n):
    calculate_squares(n)
    total = 1
    for i in xrange(2, n+1):
        total += squares[i]
    return total

def calculate_squares(n):
    for i in xrange(2, n+1):
        squares[i] = squares[i-1]*2
