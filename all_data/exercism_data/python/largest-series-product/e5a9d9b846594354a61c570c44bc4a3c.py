#!/usr/bin/env python

def slices(digits, n):
    if n > len(digits):
        raise_err()
    return [map(int, list(digits[i:i+n])) for i in range(len(digits)-(n-1))]
    
def largest_product(digits, n):
    if not digits:
        return 1
    if n > len(digits):
        raise_err()
    digits = [int(i) for i in digits]
    products = [reduce(lambda x,y: x*y, sub) for sub in slices(digits, n)]
    return max(products)
    
def raise_err():
    raise ValueError("Slice value larger than digit string")
    
