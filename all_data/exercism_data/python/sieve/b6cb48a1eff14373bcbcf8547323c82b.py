from collections import defaultdict
from itertools import chain
from math import sqrt

def sieve(until):
    """
    Find all the primes from 2 up to a given number.
    """

    if until < 2:
        raise ValueError("Parameter 'until' must be greater than 1")

    is_composite = defaultdict(bool)
    do_flag_composites = True

    for number in xrange(3, int(sqrt(until)) + 1, 2):
        if is_composite[number]:
            continue

        if not do_flag_composites:
            continue
        flag_composite_starting_from = number * number
        if flag_composite_starting_from > until:
            do_flag_composites = False
            continue
        for composite in xrange(flag_composite_starting_from, until + 1, number):
            is_composite[composite] = True

    return [
        number for number in chain([2], xrange(3, until + 1, 2))
        if not is_composite[number]
    ]
