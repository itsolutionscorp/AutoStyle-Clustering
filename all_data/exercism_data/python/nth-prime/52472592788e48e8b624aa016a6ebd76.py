from math import sqrt, ceil
primes = [2, 3, 5, 7]

def nth_prime(n):
    current = primes[len(primes) - 1]
    while len(primes) < n:
        current += 2
        prime = True
        for i in filter(lambda x: x <= ceil(sqrt(current)), primes):
            if current % i == 0:
                prime = False
                break
        if prime:
            primes.append(current)
    return primes[n - 1]
