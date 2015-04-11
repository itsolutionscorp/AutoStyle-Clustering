def nth_prime(n):
    primes = [ 2 ]
    num = 3

    while True:
        composite = False
        for prime in primes:
            if num % prime == 0:
                composite = True
                break

        if not composite:
            primes.append(num)

            if len(primes) >= n:
                return primes[n - 1]

        num += 2
