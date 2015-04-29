#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Sieve
"""

__version__ = "0.0.2"
__all__ = ['__version__', 'sieve']


def sieve(limit):
    """Sieve of Eratosthenes to find all the primes
    from 2 up to a given `limit`.

    .. versionadded:: 0.0.1
    .. versionchanged:: 0.0.2

    :param limit: the max in our range
    """
    candidates = [False, False] + [True]*(limit-2)

    def _sieve():
        for idx, prime in enumerate(candidates):
            if prime: 
                yield idx
                for _ in xrange(idx*idx, limit, idx): 
                    candidates[_] = False

    return [p for p in _sieve()]
