__author__ = 'emiller42'


def prime_factors(value, factors=None):
    if factors is None:
        factors = []
    if value > 1:
        for divisor in xrange(2, int(value ** 0.5)+1):
            if value % divisor == 0:
                factors.append(divisor)
                return prime_factors(value / divisor, factors)
        factors.append(value)
    return factors
