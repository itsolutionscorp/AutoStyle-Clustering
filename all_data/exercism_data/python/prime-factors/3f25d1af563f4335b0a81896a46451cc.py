"""Prime factorization."""


def divisible(x, y):
    """Return true if the 1st argument is divisible by the 2nd argument."""
    return x % y == 0


def prime_factors(number):
    """Return a list of prime factors of a number."""
    factors = []
    for n in xrange(2, number + 1):
        while divisible(number, n):
            factors.append(n)
            number /= n
        if number == 1:
            break
    return factors
