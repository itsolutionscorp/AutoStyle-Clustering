def prime_factors(n):
    factors = []
    candidate = 2

    while n > 1:
        if n % candidate == 0:
            n = n / candidate
            factors.append(candidate)
        else:
            candidate += 1
    return factors
