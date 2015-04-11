from itertools import compress
from math import sqrt, ceil

def sieve(limit):
    """ Use Sieve of Eratosthenes to return all prime numbers until limit. """
    is_prime = [True] * (limit + 1)
    for number in xrange(2, int(sqrt(limit)) + 1):
        if is_prime[number]:
            num_multiples = int(ceil((limit + 1 - number ** 2) / number))
            is_prime[number ** 2::number] = [False] * num_multiples
    return list(compress(xrange(2, limit + 1), is_prime[2:]))
