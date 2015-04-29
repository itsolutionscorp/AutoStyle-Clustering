from __future__ import division
import math

def nth_prime(prime_nr, sieve_size=None):
    sieve_size = sieve_size or (prime_nr
                                * int(math.log(prime_nr) * 1.30 + 10) + 1)
    
    return sieve(sieve_size, prime_nr)[-1]

##copied straight from sieve.py and modified a bit to add max_count

def slice_length(start, stop, step):
    """Returns the length of a slice"""
    return (stop - start + step - 1) // step

def sieve(n, max_count=-1):
    """
    sieve(int) -> list of int

    Return all primes in [2,n) using the Sieve of Eratosthenes.
    """
    if max_count == -1:
        max_count = n

    primes = []
    sieve = [0] * n

    for i in range(2, n):
        if sieve[i] == 0:
            primes.append(i)
            if len(primes) >= max_count:
                break
            sieve[i*i:n:i] = [1] * slice_length(i*i, n, i)
    else:
        raise ValueError("Sieve to small. Found %d of %d primes"%(len(primes), max_count))

    return primes
