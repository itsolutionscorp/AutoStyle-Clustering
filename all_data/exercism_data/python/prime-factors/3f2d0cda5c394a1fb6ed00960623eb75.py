__author__ = 'Momo'


def prime_factors(number):
    limit = number
    factors = list()

    for i in range(2, limit + 1):
        for f, number in _gen_factor(i, number):
            factors.append(f)
        if number <= 1:
            break
    return factors


def _gen_factor(i: int, number: int):

    while number % i == 0:
        number //= i
        yield (i, number)
