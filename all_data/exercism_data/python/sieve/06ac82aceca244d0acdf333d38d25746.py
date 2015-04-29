def sieve(number):
    primes = []
    multiples = set()
    for i in range(2, number+1):
        if i not in multiples:
            primes.append(i)
            multiples.update(range(i**2, number+1, i))
    return primes
