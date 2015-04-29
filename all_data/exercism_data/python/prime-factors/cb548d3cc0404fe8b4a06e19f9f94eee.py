def prime_factors(n):
    """return a sorted list of prime factors for n"""
    roots = list()
    pp = 1  # will be the primes product for found factors
    x = 2  # x is the candidate factor
    y = n
    while pp != n:  # iterate until reaching given number
        while y % x == 0:
            roots.append(x)
            y /= x  # update the remainder
            pp *= x  # update the product
        x += 1
    return roots

