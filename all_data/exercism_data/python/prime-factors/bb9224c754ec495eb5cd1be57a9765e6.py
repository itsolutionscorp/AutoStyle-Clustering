def prime_factors(n):
    factors = []
    i = 2

    while i <= n:
        while n % i == 0:
            factors.append(i)
            n = n / i
        i += 1
    return factors
