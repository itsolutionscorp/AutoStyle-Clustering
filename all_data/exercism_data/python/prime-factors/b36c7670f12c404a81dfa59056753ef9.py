from sieve import sieve
import math

def prime_factors(n):
    primes = sieve(math.ceil(math.sqrt(n)))
    result = []
    for p in primes:
        while n % p == 0:
            result.append(p)
            n /= p
    if n > 1:
        result.append(n)
    return result
