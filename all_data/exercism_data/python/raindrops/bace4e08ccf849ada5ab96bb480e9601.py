#!/usr/bin/env python


def prime_factors(number):
    """Returns a list of prime factors of the given number"""
    primes = []
    divisor = 2
    while number > 1:
        if number % divisor == 0:
            number /= divisor
            primes.append(divisor)
        else:
            divisor += 1
    return primes


def raindrops(number):
    primes = prime_factors(number)
    text = ""
    if 3 in primes:
        text += "Pling"
    if 5 in primes:
        text += "Plang"
    if 7 in primes:
        text += "Plong"
    if text == "":
        return str(number)
    else:
        return text
