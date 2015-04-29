from sieve import sieve
import math

def nth_prime(n):
    assert n > 0
    bound = max([1, int(math.log(n) * n)])
    primes = sieve(bound)
    while len(primes) < n:
        bound *= 2
        primes = sieve(bound)
    return primes[n - 1]
