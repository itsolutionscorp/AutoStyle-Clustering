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
    if 5 <= upto:
        yield 5
        for multiple in xrange(5 ** 2, upto + 1, 5 * 2):
            is_prime[multiple] = False
    # number indicates that [number, number + 6) has been processed so far.
    number = 0
    for number in xrange(6, multiple_of_6_lte(isqrt(upto)) + 6, 6):
        # number, number + 2, number + 3, number + 4 are multiples of 2 or 3.
        # number + 1, number + 5 are neither multiples of 2 nor multiples of 3.
        for num in (number + 1, number + 5):
            if is_prime[num]:
                yield num
                # 2 * num, 3 * num, ..., (num - 1) * num have already been
                # marked as composite numbers because they are multiples of 2,
                # 3, ..., num - 1.
                for multiple in xrange(num ** 2, upto + 1, num * 2):
                    is_prime[multiple] = False
    for number in xrange(number + 6, multiple_of_6_lte(upto), 6):
        for num in (number + 1, number + 5):
            if is_prime[num]:
                yield num
    for num in (number + 7, number + 11):
        if num <= upto and is_prime[num]:
            yield num
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


def multiple_of_6_lte(n):
    """Return the greatest multiple of 6 less than or equal to a given number.

    :param n: int.
    """
    return max(num for num in xrange(n, n - 6, -1) if num % 6 == 0)
