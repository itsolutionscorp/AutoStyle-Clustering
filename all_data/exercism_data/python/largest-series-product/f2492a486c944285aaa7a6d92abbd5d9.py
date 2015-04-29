'''
series.py

Find the largest product of a series of n digits in a string of digits
'''
from operator import mul

def largest_product(series, n):
    '''
    Find the largest product in the given series
    @param series: string of digits
    @param n: number of digits to multiply
    @returns: largest product of n consecutive digits in the given series
    @raises ValueError: if n is larger than the length of series
    '''
    # append [1] in the case of empty strings
    s = slices(series, n) + [[1]]

    return max([reduce(mul, l, 1) for l in s])

def slices(series, n):
    '''
    Generate slices of length n from the given series
    @param series: string of digits
    @param n: number of digits per slice
    '''
    if len(series) < n:
        raise ValueError

    s = []
    for i in range(len(series) - n + 1):
        s.append(map(int, series[i:i+n]))

    return s
