def prime_factors(n):
    factors = []
    cprime = 2

    while n > 1:
        q,r = divmod(n,cprime)
        if not r:
            factors.append(cprime)
            n = q
        else:
            cprime += 1

    return factors
