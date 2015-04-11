def sieve(limit):
    limitn = limit+1
    not_prime = []
    primes = []

    for i in range(2, limitn):
        if i in not_prime:
            continue

        for f in range(i*i, limitn, i):
            not_prime.append(f)

        primes.append(i)

    return primes
