"""The n-th prime number."""

import itertools
from math import sqrt


#: an ordered list of consecutive prime numbers that have been known so far.
primes = [2, 3]


def nth_prime(nth):
    """Return the nth prime number."""
    assert nth > 0

    def is_next_prime(number):
        """Return true if the next prime, given the previous primes.

        :param number: ``number`` should be less than two times the next prime.
        """
        number_sqrt = int(sqrt(number) + 0.1)
        for prime in primes:
            if prime > number_sqrt:
                return True
            if number % prime == 0:
                return False
        return True

    if nth - 1 < len(primes):
        return primes[nth - 1]
    nth -= len(primes)
    for number in primelike_after(primes[-1]):
        if is_next_prime(number):
            primes.append(number)
            nth -= 1
            if nth == 0:
                return number


def primelike_after(number):
    """Generate prime-like numbers after a given number."""
    assert number > 0

    if number < 2:
        yield 2
    if number < 3:
        yield 3

    # 6n - 2, 6n, 6n + 2, and 6n + 3 cannot be prime numbers because they are
    # multiples of 2 or 3.
    multiple_of_6 = multiple_of_6_after(number)
    if multiple_of_6 - 1 > number:
        yield multiple_of_6 - 1
    yield multiple_of_6 + 1
    multiples_of_6 = itertools.count(multiple_of_6 + 6, 6)
    for multiple_of_6 in multiples_of_6:
        yield multiple_of_6 - 1
        yield multiple_of_6 + 1


def multiple_of_6_after(number):
    """Return the multiple of 6 after a given number."""
    for n in xrange(number + 1, number + 7):
        if n % 6 == 0:
            return n
