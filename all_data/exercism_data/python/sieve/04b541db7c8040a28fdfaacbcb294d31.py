"""
This module has one function, sieve, which generates
a sieve of eratosthenes to find primes efficiently
"""


def sieve(max_value):
    """
    This function creates a list of values up to max, with either
    True or False, telling whether or not that index is prime.
    It then returns another list of primes based on the first list.
    """
    primality = [True] * max_value
    primality[0] = primality[1] = False
    for factor in range(len(primality)):
        if primality[factor]:
            for non_prime in range(factor ** 2, max_value, factor):
                primality[non_prime] = False
    primes = list()
    for prime in range(len(primality)):
        if primality[prime]:
            primes.append(prime)
    return primes
