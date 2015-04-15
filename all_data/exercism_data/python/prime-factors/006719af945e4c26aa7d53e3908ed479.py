#!/usr/bin/env python


def prime_factors(number):
    primes = []
    divisor = 2
    while number > 1:
        if number % divisor == 0:
            number /= divisor
            primes.append(divisor)
        else:
            divisor += 1
    return primes
