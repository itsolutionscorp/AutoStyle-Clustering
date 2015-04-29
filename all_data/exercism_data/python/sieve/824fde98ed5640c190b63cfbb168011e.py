from collections import deque


def sieve(n):
    # Using a deque for faster appends
    primes = deque()
    primes.append(2)

    # Instantiate the sieve. We flip it around
    # to make use of pop().
    sieve = list(reversed(range(3, n + 1, 2)))

    # Run this loop while we still have things in the sieve
    while sieve:
        # Grab the last element of the sieve, which is a prime
        primes.append(sieve.pop())

        # Run through our sieve and construct a new list out of it
        sieve = list(filter(lambda x: x % primes[-1] != 0, sieve))

    return list(primes)
