__author__ = 'emiller42'

import math


def prime_factors(target):

    found_prime_factors = []

    def get_prime_factors(value):
        if value > 1:
            for divisor in xrange(2, int(math.sqrt(value))+1):
                if value % divisor == 0:
                    get_prime_factors(divisor)
                    get_prime_factors(value / divisor)
                    return
            found_prime_factors.append(value)

    get_prime_factors(target)

    return found_prime_factors
