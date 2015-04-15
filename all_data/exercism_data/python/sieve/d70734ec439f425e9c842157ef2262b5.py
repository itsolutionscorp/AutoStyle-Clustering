def sieve(limit):
    numbers = range(2, limit + 1)
    not_primes = []
    for number in numbers:
        for multiple in xrange(number + number, limit +1, number):
            not_primes.append(multiple)
    return list(set(numbers) ^ set(not_primes))
