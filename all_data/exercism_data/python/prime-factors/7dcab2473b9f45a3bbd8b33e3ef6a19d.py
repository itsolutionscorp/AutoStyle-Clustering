from collections import defaultdict

def prime_factors(number):
    return list(prime_factors_generator(number))

def prime_factors_generator(number):
    factor = 2
    while number > 1:
        while not number % factor:
            number /= factor
            yield factor
        factor += 1
