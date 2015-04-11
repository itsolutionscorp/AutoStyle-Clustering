def prime_factors(number):
    factor = 2
    factors = []
    while number > 1:
        while number % factor == 0:
            factors.append(factor)
            number /= factor
        factor += 1
    return factors
