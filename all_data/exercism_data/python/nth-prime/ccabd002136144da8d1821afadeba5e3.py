from itertools import takewhile
from math import sqrt, ceil


def le(n):
    return lambda x: x <= n

def nth_prime(n):
    primes = [2]
    c = 3
    while len(primes) < n:
        if all(c%p for p in takewhile(le(ceil(sqrt(c))), primes)):
            primes.append(c)
        c += 2
    return primes[-1]
