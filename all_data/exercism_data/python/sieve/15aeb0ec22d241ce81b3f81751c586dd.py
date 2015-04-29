"""
sieve - a module for the Sieve of Eratosthenes.
"""

from math import ceil, sqrt


def sieve(n):
    """
    Create a list of all primes up to and including n.
    """

    # Traditional method.
    prime = [True] * (n + 1)
    prime[0:2] = False, False
    for i in xrange(2, int(ceil(sqrt(n)))):
        if prime[i]:
            for j in xrange(i ** 2, n + 1, i):
                prime[j] = False
    return [i for i, primality in enumerate(prime) if primality]
