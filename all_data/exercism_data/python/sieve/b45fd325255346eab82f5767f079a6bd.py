#!/usr/bin/env python3


def sieve(limit):

    a = [i*j for i in range(2, limit + 1)
         for j in range(2, limit + 1) if i*j <= limit]

    b = set(range(2, limit + 1))

    return list(b ^ set(a))
