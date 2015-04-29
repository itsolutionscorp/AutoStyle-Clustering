def sieve(n):
    r = dict.fromkeys(range(2, n + 1))
    for i in sorted(r.keys()):
        if r[i] is None:
            for j in range(i + 1, n + 1):
                if j % i == 0:
                    r[j] = False

    primes = []
    for i in sorted(r.keys()):
        if r[i] is None: primes.append(i)
    return primes
