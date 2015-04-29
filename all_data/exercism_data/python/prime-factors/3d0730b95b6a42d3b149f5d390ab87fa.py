def prime_factors(num):

    factors = []
    for factor in xrange(2,num+1):
        while num % factor == 0:
            factors.append(factor)
            num /= factor
        if num == 1:
            break

    return factors
