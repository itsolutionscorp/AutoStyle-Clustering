from math import ceil

def primes(limit):
    """ Use Sieve of Eratosthenes to return all prime numbers until limit. """
    is_prime = [True] * (limit + 1)
    for number in xrange(2, int(limit ** 0.5) + 1):
        if is_prime[number]:
            for multiple in xrange(number ** 2, limit + 1, number):
                is_prime[multiple] = False
    return [n for n in xrange(2, limit + 1) if is_prime[n]]

def prime_factors(number):
    candidates = primes(int(ceil(number ** 0.5)) + 1)
    factors = []
    for prime in candidates:
        while number % prime == 0:
            factors.append(prime)
            number /= prime
    if number > 1:
        factors.append(number)
    return factors
