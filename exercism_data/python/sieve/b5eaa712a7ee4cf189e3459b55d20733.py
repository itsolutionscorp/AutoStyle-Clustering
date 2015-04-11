def sieve(upper):
    primes = [2]
    for x in range(3, upper):
        isprime = True
        for y in primes:
            if x % y == 0:
                isprime = False
        if isprime:
            primes.append(x)

    return primes
