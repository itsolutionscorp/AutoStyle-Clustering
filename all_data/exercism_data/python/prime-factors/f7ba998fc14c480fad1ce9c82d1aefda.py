def prime_factors(n):
    primes = []
    factor = 2
    while n != 1:
        if n % factor == 0:
            n = n / factor
            primes.append(factor)
        else:
            factor += 1

    return primes
