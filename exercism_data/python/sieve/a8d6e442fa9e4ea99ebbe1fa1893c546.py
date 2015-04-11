def sieve(n):

    numbers = [x for x in range(2, n+1)]
    primes = []

    while numbers:
        i = numbers.pop(0)
        primes.append(i)
        for j in range(2*i, n+1, i):
            try:
                numbers.remove(j)
            except ValueError:
                pass

    return primes
