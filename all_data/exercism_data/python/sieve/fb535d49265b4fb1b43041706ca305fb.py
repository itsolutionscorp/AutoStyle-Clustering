def sieve(n):
    primes = list(range(2, n + 1))
    current_prime = 2
    while True:
        if current_prime >= primes[-1]:
            break
        for number in primes:
            if number > current_prime and number % current_prime == 0:
                primes.pop(primes.index(number))
        current_prime = min(x for x in primes if x > current_prime)

    return primes

    # K. We want to:
    # 1. Make a full range of numbers.
    # 2. Start with the first prime (2), and pop everything that it divides.
    # 3. Step to the next prime (until we're all the way through).
