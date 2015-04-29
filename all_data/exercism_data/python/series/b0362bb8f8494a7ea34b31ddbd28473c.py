#!/usr/bin/env python

def slices(digits, n):
    if n > len(digits) or n == 0:
        raise ValueError
    return [map(int, list(digits[i:i+n])) for i in range(len(digits)-(n-1))]
