from itertools import takewhile
from math import sqrt, ceil


def le(n):
    return lambda x: x <= n

def nth_prime(n):
    primes = [2]
    c = 2
    while len(primes) < n:
        c += 1
        if all(c%p for p in takewhile(le(ceil(sqrt(c))), primes)):
            primes.append(c)
    return c
