from math import sqrt

def sieve_fast(limit):
    """ Use Sieve of Eratosthenes to return all prime numbers until limit. """
    is_prime = [True] * (limit + 1)
    for number in xrange(2, int(sqrt(limit)) + 1):
        if is_prime[number]:
            for multiple in xrange(number ** 2, limit + 1, number):
                is_prime[multiple] = False
    return [n for n in xrange(2, limit + 1) if is_prime[n]]
