__author__ = 'grdunn'

import math


def sieve(end_of_range):
    """
    Sieve of Eratosthenes


    :type end_of_range: int
    :param end_of_range:
    """
    sieved = [i for i in range(2, end_of_range + 1)]

    for i in range(2, int(math.ceil(math.sqrt(end_of_range)))):
        sieved = filter(lambda x: x == i or x % i, sieved)

    return sieved
