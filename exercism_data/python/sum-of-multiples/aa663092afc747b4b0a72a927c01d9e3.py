# -*- coding: utf-8 -*-
import itertools

def sum_of_multiples(given_number, factors=[3, 5]):
    ans = []
    for factor in factors:
        if factor != 0:
            multiples = itertools.takewhile(lambda x: x < given_number, itertools.count(0, factor))
            ans += multiples
    return sum(set(ans))
