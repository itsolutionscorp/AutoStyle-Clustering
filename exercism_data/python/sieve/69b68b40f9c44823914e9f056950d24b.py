def sieve(n):
    primes = []
    not_primes = set()
    for p in range(2, n+1):
        if p not in not_primes:
            primes.append(p)
            multiple = p
            while multiple <= n:
                multiple = multiple + p
                if multiple <= n:
                    not_primes.add(multiple)
    return primes
