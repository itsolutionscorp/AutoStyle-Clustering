#!/usr/bin/env python3
""" module sieve for exercism.io programming training"""


def sieve(top):
    """ returns all primes up to top """
    population = [i for i in range(2, top)]
    for i in range(2, top//2):
        population = [j for j in population if (j % i != 0) or (j == i)]
    return population
