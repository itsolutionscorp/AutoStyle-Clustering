def prime_factors(num):
    factors = []
    factor = 2

    while num > 1:
        if num % factor == 0:
            factors.append(factor)
            num /= factor
        else:
            factor += 1

    return factors
