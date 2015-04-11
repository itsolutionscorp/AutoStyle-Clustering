# -*- coding: utf-8 -*-

from __future__ import division

import itertools
import operator

def gcd(a, *args):
    """
    gdc(int, int, [int, ...]) -> int

    Return the greatest common divisor for at least two positive ints.
    """

    for b in args:
        while b > 0:
            a, b = b, a % b
    return a


def lcm(a, *args):
    """
    lcm(int, int, [int, ...]) -> int

    Return the least common multiple for at least two positive ints.
    """

    if len(args) == 0:
        return a
    elif len(args) == 1:
        return a * args[0] // gcd(a, args[0])
    else:
        return lcm(a, reduce(lcm, args))


def multiples(n, factor):
    """
    multiples(int n, int factor) -> int

    Return the sum of factor + factor*1 + factor*2 + ... + n-1.
    """

    n = (n - 1)//factor
    return (n*(n+1)//2) * factor


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
            self.factors = args
        else:
            self.factors = [3, 5]

    def to(self, n):
        s = 0

        steps = zip(range(1, len(self.factors) + 1),
                    itertools.cycle([1,-1]))

        for i, sign in steps:
            for comb in itertools.combinations(self.factors, i):
                s += sign * multiples(n, lcm(*comb))

        return s
