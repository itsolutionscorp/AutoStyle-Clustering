def prime_factors(n):
    factors = []
    divisor = 2
    while divisor**2 <= n:
        while (n%divisor) == 0:
            factors.append(divisor)
            n /= divisor
        divisor += 1
    if n > 1:
        factors.append(n)
    return factors
