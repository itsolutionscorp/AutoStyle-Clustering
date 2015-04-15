from math import sqrt

def sieve(upper):
    """Finds primes from 0 to upper, exclusive."""
    
    if upper < 2:
        return []
    
    primes = range(2, upper)
    index = 0
    while index < len(primes) - 1 and primes[index] <= sqrt(primes[-1]):
        i = index + 1
        while i < len(primes):
            if primes[i] % primes[index] == 0:
                del primes[i]
            i += 1
        index += 1
    return primes
