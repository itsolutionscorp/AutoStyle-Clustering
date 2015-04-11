"""Prime factorization."""


def divisible(x, y):
    """Return true if the 1st argument is divisible by the 2nd argument."""
    return x % y == 0


def divmul(x, y):
    """divmul(x, y) = (q, m) where x == q * (y ** m).

    x is divisible by y m times and then the quotient is q.
    """
    mul = 0
    while divisible(x, y):
        mul += 1
        x /= y
    return (x, mul)


def prime_factors(number):
    """Return a list of prime factors of a number.

    Args:
        number (int): A positive integer.
    """
    factors = []

    # factor == 2
    number, mul = divmul(number, 2)
    factors.extend([2] * mul)

    # factor >= 3
    for fac in xrange(3, number + 1, 2):
        number, mul = divmul(number, fac)
        factors.extend([fac] * mul)
        if number == 1:
            break

    return factors
