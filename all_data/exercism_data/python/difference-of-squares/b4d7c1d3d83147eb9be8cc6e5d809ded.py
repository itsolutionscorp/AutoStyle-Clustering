# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
def sum_of_squares(N):
    """Calculates and returns the sum of squares"""
    sum = 0
    for num in range(1, N+1):
        sum += num**2
    return sum

def square_of_sum(N):
    """Calculates and returns square of sums"""
    sum = 0
    for num in range(1, N+1):
        sum += num
    return sum**2

def difference(N):
    """Calculates and returns the difference between the square of sums and
    the sum of squares for a number N"""
    return square_of_sum(N) - sum_of_squares(N)
