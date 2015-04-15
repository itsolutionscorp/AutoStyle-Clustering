def sieve(upper):
    primes = []
    non_primes = [True for i in range(upper + 1)]
    for i, n in enumerate(non_primes):
        if i < 2:
            continue
        if non_primes[i]:
            primes.append(i)
            for x in range((upper // (i) + 1)):
                non_primes[x * (i)] = False
    return primes
