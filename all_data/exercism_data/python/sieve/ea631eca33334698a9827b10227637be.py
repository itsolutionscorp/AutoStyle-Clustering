__author__ = 'emiller42'

import math


def sieve(max_value):
    """
    implementation based on pseudocode found at
    http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Implementation
    """

    is_prime = {}

    for i in range(2, max_value+1):
        is_prime[i] = True

    for i in range(2, int(math.sqrt(max_value))+1):
        if is_prime[i]:
            j = i**2
            while j <= max_value:
                is_prime[j] = False
                j += i

    primes = [value for value in is_prime if is_prime[value]]

    return primes
