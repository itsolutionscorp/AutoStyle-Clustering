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
            j = i**2
            while j <= max_value:
                is_prime[j] = False
                j += i

    primes = [i for i in range(2, max_value) if is_prime[i]]

    return primes
