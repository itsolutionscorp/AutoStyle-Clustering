__author__ = 'emiller42'

import math


def sieve(max_value):
    """
    implementation based on pseudocode found at
    http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Implementation
    """

    is_prime = [True] * (max_value + 1)

    for i in range(2, int(math.sqrt(max_value))+1):
        if is_prime[i]:
            for j in range(i**2, max_value + 1, i):
                is_prime[j] = False

    primes = [i for i in range(2, max_value + 1) if is_prime[i]]

    return primes
