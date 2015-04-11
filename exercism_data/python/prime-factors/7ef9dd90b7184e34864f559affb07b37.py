'''exer prime_factors'''

import math


def prime_factors(num):
    '''return all prime factors of num'''
    factors = []
    for prime in primes(2, num):
        while num % prime == 0:
            factors.append(prime)
            num /= prime
        if prime > num:
            break
    return factors


def is_prime(num):
    '''is num prime?'''
    divisor = 2
    incr = 1        # test 2, then incr + 1 to 3, then incr by 2 afterwards
    while divisor > int(math.sqrt(num)) + 1:
        if (num % divisor) == 0:
            return False
        divisor += incr
        incr = 2            # skip to next odd
    return True

def primes(start, stop):
    '''generator of primes between start and stop'''
    if start <= 2:
        yield 2
    this_int = start                            # optimized xrange out
    while this_int <= stop:
        if this_int > 2:
            if is_prime(this_int):
                yield this_int
        if this_int % 2 != 0:                   # optimize - if odd, go by 2
            this_int += 2
        else:                                   # inc to the next odd
            this_int += 1
