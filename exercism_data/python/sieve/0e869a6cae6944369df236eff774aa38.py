"""The Sieve of Eratosthenes."""

import math


def prime_numbers(upto):
    """ Generate prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    """
    is_prime = [True] * (upto + 1)
    # 2 <= number < isqrt(upto) + 1
    for number in xrange(2, isqrt(upto) + 1):
        if is_prime[number]:
            yield number
            # 2 * number, 3 * number, ..., (number - 1) * number have already
            # been marked as non-prime numbers because they are multiples of 2,
            # 3, ..., number - 1.
            multiples = xrange(number ** 2, upto + 1, number)
            for multiple in multiples:
                is_prime[multiple] = False
    # isqrt(upto) + 1 <= number < upto + 1
    for number in xrange(number + 1, upto + 1):
        if is_prime[number]:
            yield number
    return


def sieve(upto):
    """ Return a list of prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    :returns: a list of prime numbers up to a given number.
    :rtype: list of int.
    """
    return list(prime_numbers(upto))


def isqrt(n):
    """Return the greatest integer less than or equal to the square root.

    :param n: a nonnegative integer (n >= 0)
    """
    ret = int(math.sqrt(n))
    ret += 1
    while ret * ret <= n: ret += 1
    ret -= 1
    while ret * ret > n: ret -= 1
    return ret
