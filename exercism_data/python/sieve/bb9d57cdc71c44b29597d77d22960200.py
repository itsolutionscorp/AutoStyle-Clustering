def sieve(max):
    primes = []
    values = list(range(max, 1, -1))

    while values:
        prime = values.pop()
        primes.append(prime)
        values = [v for v in values if v % prime != 0]

    return primes
