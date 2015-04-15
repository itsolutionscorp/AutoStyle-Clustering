#!/usr/bin/env python3
#-*- coding: utf-8 -*-

__author__ = "Greg"

def sieve(max):
    not_primes = set()
    primes = []
    for x in range(2, max):
        if x not in not_primes:
            for y in range(x, int(max*.5)):
                not_primes.add(x*y)
            primes.append(x)
    return primes
