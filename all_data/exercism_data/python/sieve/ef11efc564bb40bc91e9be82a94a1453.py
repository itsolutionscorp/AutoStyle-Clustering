__author__ = 'emiller42'

import math


def sieve(max_value):
    """
    implementation based on pseudocode found at
    http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes#Implementation
    """
    prime_list = []

    value_dict = {}

    for i in range(2, max_value+1):
        value_dict[i] = True

    for i in range(2, int(math.sqrt(max_value))+1):
        if value_dict[i]:
            j = i**2
            while j <= max_value:
                value_dict[j] = False
                j += i

    [prime_list.append(value) for value in value_dict if value_dict[value]]

    return prime_list
