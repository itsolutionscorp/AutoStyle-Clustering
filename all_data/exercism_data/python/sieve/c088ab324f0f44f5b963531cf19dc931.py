def sieve(upper_bound):
    primes = []
    numbers = range(2, upper_bound + 1)
    while len(numbers):
        primes.append(numbers[0])
        numbers = [i for i in numbers if i % primes[len(primes) - 1]]
    return primes
