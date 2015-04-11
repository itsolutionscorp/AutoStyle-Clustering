def prime_factors(number):
    factors = []
    for x in xrange(2, number+1):
        while number % x == 0:
            number = number // x
            factors.append(x)
            if number == 1:
                return factors
    return factors
