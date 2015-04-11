def prime_factors(number):
    result = []
    factor_test = 2
    while number > 1:
        if number % factor_test == 0:
            result.append(factor_test)
            number /= factor_test
        else:
            factor_test += 1
    return result
