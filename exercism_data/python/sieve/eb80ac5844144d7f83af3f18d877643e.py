def sieve(max):
    primes = []
    is_prime = [True] * (max + 1)
    for x in range(2, max + 1):
        if is_prime[x]:
            primes.append(x)
            for y in range(x+1, max + 1):
                if (y % x == 0):
                    is_prime[y] = False
    return primes
