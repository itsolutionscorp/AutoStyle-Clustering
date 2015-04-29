# -*- coding: utf-8 -*-

from __future__ import division


def slice_length(start, stop, step):
    """Returns the length of a slice"""
    return (stop - start + step - 1) // step

def sieve(n):
    """
    sieve(int) -> list of int

    Return all primes in [2,n) using the Sieve of Eratosthenes.
    """

    primes = []
    sieve = [0] * n

    for i in range(2, n):
        if sieve[i] == 0:
            primes.append(i)
            sieve[i*i:n:i] = [1] * slice_length(i*i, n, i)

    return primes
