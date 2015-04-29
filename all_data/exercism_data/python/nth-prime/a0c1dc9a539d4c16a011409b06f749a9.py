import math


primes = [2, 3]


def is_prime(n):
    x = int(math.sqrt(n))

    for p in primes:
        if p > x:
            break

        if n % p == 0:
            return False

    return True

def nth_prime(n):
    current = primes[-1] + 2
    while len(primes) < n:
        if is_prime(current):
            primes.append(current)
        current += 2

    return primes[n - 1]
