#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Sieve
"""

__version__ = "0.0.1"
__all__ = ['__version__', 'sieve']


def sieve(number):
    """Sieve of Eratosthenes to find all the primes
    from 2 up to a given `number`.

    .. versionadded:: 0.0.1

    :param number: the max in our range
    """
    primes, candidates = [2], range(3, number + 1)

    def _sieve(prime):
        """Internal function that sieves the candidates
        based on the given `prime`
        """
        candidates[:] = [d for d in candidates if d % prime]
        primes.append(candidates.pop(0))

    while candidates:
        _sieve(primes[-1])

    return primes
