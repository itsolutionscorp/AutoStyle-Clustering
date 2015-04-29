from collections import defaultdict


def sieve(upper):
    primes = []
    non_primes = defaultdict(lambda: True)
    for n in range(2, upper+1):
        if non_primes[n]:
            primes.append(n)
            for x in range((upper // (n) + 1)):
                non_primes[x * (n)] = False
    return primes
