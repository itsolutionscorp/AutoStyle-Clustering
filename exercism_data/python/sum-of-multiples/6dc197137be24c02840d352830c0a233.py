# -*- coding: utf-8 -*-

from __future__ import division

import itertools
import operator
import fractions


def lcm(*args):
    """
    lcm(int, int, [int, ...]) -> int

    Return the least common multiple for at least two positive ints.
    """

    if len(args) == 1:
        return args[0]
    elif len(args) == 2:
        return (args[0] * args[1]) // fractions.gcd(*args)
    else:
        return reduce(lcm, args)


def multiples(n, factor):
    """
    multiples(int n, int factor) -> int

    Return the sum of factor + factor*1 + factor*2 + ... + n-1.
    """

    n = (n - 1) // factor
    return (n * (n + 1) // 2) * factor


class SumOfMultiples:
    """
    A class to calculate the sum of multiples of a set of numbers up to n.

    The Result of ```SumOfMultiples(*args).to(n)``` is equivalent to
    sum((i for i in range(n)
           if any((i % c == 0 for c in args)))).

    However, this version uses inclusion-exclusion and should be quite a bit
    faster for large n.
    """

    def __init__(self, *args):
        if args:
            self.factors = list(SumOfMultiples._clean_factors(args))
        else:
            self.factors = [3, 5]

    @staticmethod
    def _clean_factors(factors):
        factors = sorted(factors)

        for i, candidate in enumerate(factors):
            if all(candidate % f for f in factors[:i]):
                yield candidate

    def to(self, n):
        steps = zip(range(1, len(self.factors) + 1),
                    itertools.cycle([+1, -1]))

        s = 0
        for i, sign in steps:
            s += sign * sum(multiples(n, lcm(*comb))
                            for comb in itertools.combinations(
                                self.factors, i))

        return s
