from collections import defaultdict
from math import sqrt

def sieve(max):
    """ Use Sieve of Eratosthenes to return all prime numbers until max. """
    is_prime = defaultdict(lambda: True)
    for number in xrange(2, int(sqrt(max)) + 1):
        if is_prime[number]:
            for multiple in xrange(number ** 2, max + 1, number):
                is_prime[multiple] = False
    return [n for n in xrange(2, max + 1) if is_prime[n]]
