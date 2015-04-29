import sys
def prime_factors(val):
    """Calculate the prime factors of val and return them in a list"""
    primes = []
    test = 2
    while val > 1:
        while val%test == 0:
            primes.append(test)
            val/=test
        test+=1
    return primes
