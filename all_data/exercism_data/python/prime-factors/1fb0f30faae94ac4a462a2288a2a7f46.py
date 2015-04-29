__author__ = 'jimblackler'


def prime_factors(n):
    result = []
    x = 2
    while x <= n:
        while n % x == 0:
            result.append(x)
            n /= x
        x += 1
    return result
