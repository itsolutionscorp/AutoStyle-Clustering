# -*- coding: utf-8 -*-

from __future__ import division

import itertools

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

            if i*i < n:
                sieve[i*i:n:i] = itertools.repeat(1, (n - i*i + i - 1) // i)

    return primes
