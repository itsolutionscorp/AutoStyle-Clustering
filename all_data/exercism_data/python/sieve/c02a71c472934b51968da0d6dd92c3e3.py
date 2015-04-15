# Python 3

"""The Sieve of Eratosthenes."""

from math import sqrt


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
    is_composite = bytearray(upto + 1)
    if 2 <= upto:
        yield 2
    if 3 <= upto:
        yield 3
    if 5 <= upto:
        yield 5
        for multiple in range(5 * 5, upto + 1, 5 * 6):
            is_composite[multiple] = 1
        for multiple in range(5 * 5 + 5 * 2, upto + 1, 5 * 6):
            is_composite[multiple] = 1
    # number indicates that [number, number + 6) has been processed most
    # recently.
    number = 0
    for number in range(6, multiple_of_6_lte(isqrt(upto)) + 6, 6):
        num = number + 1
        if not is_composite[num]:
            yield num
            for multiple in range(num * num, upto + 1, num * 6):
                is_composite[multiple] = 1
            for multiple in range(num * num + num * 4, upto + 1, num * 6):
                is_composite[multiple] = 1
        num = number + 5
        if not is_composite[num]:
            yield num
            for multiple in range(num * num, upto + 1, num * 6):
                is_composite[multiple] = 1
            for multiple in range(num * num + num * 2, upto + 1, num * 6):
                is_composite[multiple] = 1
    for number in range(number + 6, multiple_of_6_lte(upto), 6):
        num = number + 1
        if not is_composite[num]:
            yield num
        num = number + 5
        if not is_composite[num]:
            yield num
    for num in (number + 7, number + 11):
        if num <= upto and not is_composite[num]:
            yield num
    return


def isqrt(n):
    """Return the greatest integer less than or equal to the square root.

    :param n: a nonnegative integer (n >= 0)
    """
    try:
        ret = int(sqrt(n))
        ret += 1
        while ret * ret <= n: ret += 1
        ret -= 1
        while ret * ret > n: ret -= 1
        return ret
    except OverflowError:
        # Fall back on Newton's method
        q, r = divmod(n.bit_length(), 2)
        x = 2 ** (q + r)
        while True:
            y = (x + n // x) >> 1
            if y >= x:
                return x
            x = y


def multiple_of_6_lte(n):
    """Return the greatest multiple of 6 less than or equal to a given number.

    :param n: int.
    """
    return max(num for num in range(n, n - 6, -1) if num % 6 == 0)
