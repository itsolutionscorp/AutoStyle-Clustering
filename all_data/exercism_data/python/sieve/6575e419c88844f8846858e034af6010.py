#!/usr/bin/env python
"""Implements the features requested in the README.md.
"""
def sieve(limit):
    """Implements The Sieve of Eratosthenes.
    """
    index=0
    start=2
    prime_range = range(start, limit+1)
    print "list length = " + str(len(prime_range))

    while index < (len(prime_range) - 1):
        for x in prime_range[index+1:]:
            if x % prime_range[index] == 0:
                prime_range.remove(x)
        index += 1
    return prime_range
