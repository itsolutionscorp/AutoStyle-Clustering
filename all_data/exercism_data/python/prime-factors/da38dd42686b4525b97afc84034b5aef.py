from itertools import cycle

def prime_factors(number):
    return list(generate_prime_factors(number))

def generate_prime_factors(number):
    for factor in generate_factor_candidates():
        if number == 1:
            return
        if factor ** 2 > number:
            yield number
            return
        while not number % factor:
            yield factor
            number /= factor

def generate_factor_candidates():
    yield 2
    yield 3
    yield 5
    candidate = 1
    for jump in cycle([6, 4, 2, 4, 2, 4, 6, 2]):
        candidate += jump
        yield candidate