__author__ = 'Momo'


def prime_factors(number):

    limit = number
    factors = list()
    for i in range(2, limit + 1):
        while number % i == 0:
            number = number // i
            factors.append(i)
        if number <= 1:
            break
    return factors
