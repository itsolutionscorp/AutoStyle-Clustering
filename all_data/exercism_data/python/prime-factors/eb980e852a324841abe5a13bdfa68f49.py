__author__ = 'emiller42'

import math


def prime_factors(target):

    found_prime_factors = []

    def get_prime_factors(value):
        if value > 1:
            divisor = 2
            while divisor < int(math.sqrt(value))+1:
                if value % divisor == 0:
                    get_prime_factors(divisor)
                    get_prime_factors(value / divisor)
                    return
                divisor += 1
            found_prime_factors.append(value)

    get_prime_factors(target)

    return found_prime_factors
