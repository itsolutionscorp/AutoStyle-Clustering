import itertools

def prime_factors(n):
    factors = []
    candidate = 2
    while n != 1:
        while n % candidate == 0:
            n /= candidate
            factors.append(candidate)
        candidate += 1
    return factors
