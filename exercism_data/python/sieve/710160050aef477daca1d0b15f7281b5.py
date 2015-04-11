"""The Sieve of Eratosthenes."""


def prime_numbers(upto):
    """ Generate prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    """
    sieve = [True] * (upto + 1)
    for number in xrange(2, upto + 1):
        if not sieve[number]:
            continue
        yield number
        for multiple in xrange(number ** 2, upto + 1, number):
            sieve[multiple] = False
    return


def sieve(upto):
    """ Return a list of prime numbers up to a given number.

    :param upto: a positive integer of moderate size.
    :type upto: int.
    :returns: a list of prime numbers up to a given number.
    :rtype: list of int.
    """
    return list(prime_numbers(upto))
