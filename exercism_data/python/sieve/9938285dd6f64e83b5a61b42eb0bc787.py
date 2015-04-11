def sieve(upper):
    primes = range(2, upper)

    i = 0
    while i < len(primes):
        for d in primes[:i]:
            if primes[i] % d == 0:
                del primes[i]
                i -= 1
                break
        i += 1

    return primes
