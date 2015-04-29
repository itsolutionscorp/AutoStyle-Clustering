def nth_prime( N ):
    primes = []
    n = 2

    while len(primes) < N:
        isPrime = True
        for pr in primes:
            if n % pr == 0:
                isPrime = False
                break
        if isPrime:
            primes.append( n )
        n += 1

    return primes[-1]
