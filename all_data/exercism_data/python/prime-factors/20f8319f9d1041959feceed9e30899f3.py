def prime_factors(number):
    if not (number % 1 == 0 and number > 0):
        raise ValueError('input not natural number')
    else:
        prime_factors = []
        factor = 2
        while factor <= number:
            while number % factor == 0:
                prime_factors.append(factor)
                number /= factor
            factor += 1
        return prime_factors

