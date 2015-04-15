# -*- coding: utf-8 -*-

from __future__ import division

def sieve(n):
    """
    sieve(int) -> list of int

    Return all primes in [2,n) using the Sieve of Eratosthenes.
    """

    #could cut the sieve in half and add a corner case for 2
    primes = []
    sieve = [0] * n

    for i in range(2, n):
        if sieve[i] == 0:
            primes.append(i)

            for j in range(i*i, n, i):
                sieve[j] = 1

    return primes
