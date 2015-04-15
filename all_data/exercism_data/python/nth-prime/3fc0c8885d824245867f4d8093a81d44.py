import math

def is_prime(n):
    result = True
    for i in range(2, int(math.sqrt(n)) + 1):
        if n % i == 0:
            result = False
            break
    return result

def nth_prime(n):
    primes = []
    num = 2
    while len(primes) < n:
        if is_prime(num):
            primes.append(num)
        num += 1
    return primes[-1]
