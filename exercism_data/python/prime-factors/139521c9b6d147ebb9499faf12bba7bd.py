def prime_factors(number):
    factors = []
    for p in xrange(2, number + 1):
        while number % p == 0:
            factors.append(p)
            number /= p
        if number == 1:
            break
    return factors
