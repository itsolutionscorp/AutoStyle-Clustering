from __future__ import division

import itertools
import math
import operator

def sieve_jump_array(primes):
    """
    sieve_jump_array(list of ints) -> (list of ints)

    Given the first consecutive primes in order, return the jump-list
    used by trial division.
    """

    primes, biggest_prime = primes[:-1], primes[-1]
    sieve_size = reduce(operator.mul, primes) + 1
    sieve = list((i + biggest_prime for i in range(sieve_size)))

    for prime in primes:
        for f in range(biggest_prime // prime,
                       (sieve_size + prime - 1)// prime):
            sieve[f * prime - biggest_prime] = 0

    compressed = [i for i in sieve if i != 0]
    jumps = [b - a for a, b in zip(compressed, compressed[1:])]
    return jumps


trial_division_precompute_primes = (2, 3, 5, 7, 11)
trial_division_jumps = sieve_jump_array(trial_division_precompute_primes)


def trial_division(number):
    """
    trial_division(int) -> int

    Return the smallest factor of number.
    """

    if number == 1:
        return 1

    for p in trial_division_precompute_primes:
        if not number % p:
            return p

    jumps = trial_division_jumps
    candidate = trial_division_precompute_primes[-1]
    check_until = int(math.sqrt(number))

    for jump in itertools.cycle(jumps):
        if not number % candidate:
            return candidate

        candidate += jump

        if candidate > check_until:
            break

    return number


def prime_factors(number):
    """
    prime_factors(int) -> list of ints

    Return the list of ordered prime factors starting with the smallest.
    Uses trial division.
    """

    factors = []
    while number > 1:
        factor = trial_division(number)

        while not number % factor:
            factors.append(factor)
            number //= factor

    return factors
