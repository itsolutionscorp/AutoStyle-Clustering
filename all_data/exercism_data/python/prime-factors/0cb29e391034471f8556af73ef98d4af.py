from __future__ import division, print_function
import math

def prime_factors(n):
    res = []
    while n % 2 == 0:
        res.append(2)
        n //= 2
    for p in xrange(3, n+1, 2):
        if n == 1:
            break
        while n % p == 0:
            res.append(p)
            n //= p
    return res
