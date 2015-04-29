"""The Sieve of Eratosthenes."""

from math import sqrt
import sys
if sys.version_info[0] >= 3: xrange = range


def sieve(upto):
    """Return a list of prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    :returns: a list of prime numbers up to a given number.
    :rtype: list of int.
    """
    return list(prime_numbers(upto))


def prime_numbers(upto):
    """Generate prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    """
    is_prime = [True] * (upto + 1)
    if 2 <= upto:
        yield 2
    if 3 <= upto:
        yield 3
        # See the comment below for the step argument, 3 * 2.
        for multiple in xrange(3 ** 2, upto + 1, 3 * 2):
            is_prime[multiple] = False
    # `number` records a number that has been processed most recently.
    number = 3
    for number in xrange(5, isqrt(upto) + 1, 2):
        if is_prime[number]:
            yield number
            # number * 2, number * 3, ..., number * (number - 1) have already
            # been marked as composite numbers because they are multiples of 2,
            # 3, ..., number - 1.
            #
            # number ** 2 + number, number ** 2 + number * 3, ... are even
            # (i.e. multiples of 2) because number is odd.
            for multiple in xrange(number ** 2, upto + 1, number * 2):
                is_prime[multiple] = False
    for number in xrange(number + 2, upto + 1, 2):
        if is_prime[number]:
            yield number
    return


def isqrt(n):
    """Return the greatest integer less than or equal to the square root.

    :param n: a nonnegative integer (n >= 0)
    """
    ret = int(sqrt(n))
    ret += 1
    while ret * ret <= n: ret += 1
    ret -= 1
    while ret * ret > n: ret -= 1
    return ret
