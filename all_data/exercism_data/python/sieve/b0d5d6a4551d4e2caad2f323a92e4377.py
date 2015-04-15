def sieve(n):
    numbers = list(range(2, n + 1))
    primes = []

    while numbers:
        current = numbers.pop(0)
        primes.append(current)

        for i in range(2*current, n + 1, current):
            if i in numbers:
                numbers.remove(i)

    return primes
