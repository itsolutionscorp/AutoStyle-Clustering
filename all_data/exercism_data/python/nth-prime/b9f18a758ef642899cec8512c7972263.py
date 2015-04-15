from itertools import count
from math import sqrt

def nth_prime(n):
    primes = []
    for pp in _mostly_primes():
        if any(pp % p == 0
               for p in primes):
            continue
        primes.append(pp)
        if len(primes) == n:
            return pp

def _mostly_primes():
    yield 2
    yield 3
    for n in count(6, 6):
        yield n - 1
        yield n + 1
