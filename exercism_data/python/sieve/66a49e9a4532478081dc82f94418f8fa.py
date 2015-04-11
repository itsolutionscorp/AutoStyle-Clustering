from math import sqrt
from itertools import chain

def sieve(until):
    """
    Find all the primes from 2 up to a given number.
    """

    if until < 2:
        raise ValueError("Parameter 'until' must be greater than 1")

    is_composite = [False] * (until + 1)

    for number in xrange(3, int(sqrt(until)) + 1, 2):
        if is_composite[number]:
            continue

        for composite in xrange(number * number, until + 1, number):
            is_composite[composite] = True

    return [
        number for number in chain([2], xrange(3, until + 1, 2))
        if not is_composite[number]
    ]
