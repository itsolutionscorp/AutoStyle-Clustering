def reduce_by_smallest_factor(n):
    return next(((f, int(n / f)) for f in range(2, n) if n % f == 0),
                (n, 1))


def prime_factor_generator(n):
    while n > 1:
        x, n = reduce_by_smallest_factor(n)
        yield x


def prime_factors(n):
    return list(prime_factor_generator(n))
