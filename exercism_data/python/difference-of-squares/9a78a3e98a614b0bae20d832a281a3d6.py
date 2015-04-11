# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
def sum_of_squares(n):
    """Calculates and returns the sum of squares"""
    return sum(x**2
                for x in range(1, n+1))

def square_of_sum(n):
    """Calculates and returns square of sums"""
    return sum(range(1, n+1))**2

def difference(n):
    """Calculates and returns the difference between the square of sums and
    the sum of squares for a number N"""
    return square_of_sum(n) - sum_of_squares(n)
