def sieve(n):
    primes = []
    candidates = list(range(2, n + 1))
    while candidates:
        primes.append(candidates.pop(0))
        candidates = [num for num in candidates if num % primes[-1] != 0]
    return primes
