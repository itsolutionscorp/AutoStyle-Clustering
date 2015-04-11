
def prime_factors(number):
    factor = 2
    factors = []
    while factor * factor <= number:
        if number % factor:
            factor += 1
        else:
            number /= factor
            factors.append(factor)
    if number > 1:
        factors.append(number)
    return factors

