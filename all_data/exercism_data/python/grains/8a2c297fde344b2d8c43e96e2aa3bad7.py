#!/usr/bin/env python

squares = dict.fromkeys(range(1, 65), 0)

def on_square(n):
    calculate_squares(n)
    return squares[n]

def total_after(n):
    calculate_squares(n)
    total = 0
    for i in xrange(1, n+1):
        total += squares[i]
    return total

def calculate_squares(n):
    for i in xrange(1, n+1):
        if i == 1:
            squares[1] = 1
        else:
            squares[i] = squares[i-1]*2
