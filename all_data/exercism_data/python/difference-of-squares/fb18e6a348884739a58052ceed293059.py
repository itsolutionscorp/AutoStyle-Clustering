# -*- coding: utf-8 -*-
"""Implements the rules in the README.md"""
def sum_of_squares(n):
    """Calculates and returns the sum of squares"""
    return n*(n+1)*(2*n+1)/6

def square_of_sum(n):
    """Calculates and returns square of sums"""
    return ((1+n)*n/2)**2

def difference(n):
    """Calculates and returns the difference between the square of sums and
    the sum of squares for a number N"""
    return square_of_sum(n) - sum_of_squares(n)
