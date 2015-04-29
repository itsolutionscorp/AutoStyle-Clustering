__author__ = 'emiller42'

import itertools


def __is_prime(known_primes, potential_prime):
    # A non-prime number will have a smaller prime as a factor.
    # Iterate through known primes up to the square root of the potential prime
    # if the known prime is a factor of the potential, return False,
    # otherwise, return true.
    for prime in itertools.takewhile(lambda x: x <= potential_prime**0.5, known_primes):
        if potential_prime % prime == 0:
            return False
    return True


def nth_prime(target_prime):

    # We know 2 is prime, so add it to the collection and start checking with 3
    primes = [2]
    candidate = 3

    # Check if the candidate is prime, if so, add it to the collection of primes
    # and increment by 2.  (We only need to check odd values)
    # repeat until the desired number of primes is found.
    while len(primes) < target_prime:
        if __is_prime(primes, candidate):
            primes.append(candidate)
        candidate += 2

    return primes[-1]
