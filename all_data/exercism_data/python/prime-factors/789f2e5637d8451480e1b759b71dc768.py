from collections import defaultdict
from itertools import cycle

def prime_factors(number):
    return list(prime_factors_generator(number))

def prime_factors_generator(number):
    factor_generator = factor_candidate_generator()
    while number > 1:
        factor = factor_generator.next()
        while not number % factor:
            number /= factor
            yield factor

def factor_candidate_generator():
    yield 2
    yield 3
    yield 5
    yield 7
    candidate = 1
    for jump in cycle([6, 4, 2, 4, 2, 4, 6, 2]):
        candidate += jump
        yield candidate
