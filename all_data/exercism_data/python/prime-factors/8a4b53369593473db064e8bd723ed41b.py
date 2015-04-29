from sieve import sieve

def prime_factors(n):
    candidates = sieve(n+1)
    factors = []
    for c in candidates:
        while n%c == 0:
            factors.append(c)
            n /= c
    return factors

        
