__author__ = 'tracyrohlin'

from utils import isprime

import math

def prime_factors(number):
    prime_list =[]
    for x in xrange(2, number+1):
        while number % x == 0:
            if isprime(x):
                prime_list.append(x)
                number = number / x

    return prime_list
