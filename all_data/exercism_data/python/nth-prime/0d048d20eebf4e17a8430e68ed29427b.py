from math import log, floor
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


def nth_prime(n):
    # Get the upper bound using our nice function
    upper_bound = upper_bound_on_nth_prime(n)

    # Sieve on everything up to the upper bound
    primes = sieve(upper_bound)

    # Grab the nth prime, which is n-1 on the list
    return primes[n - 1]


def upper_bound_on_nth_prime(n):
    """ Comes from wikipedia:
    http://en.wikipedia.org/wiki/Prime-counting_function#Inequalities
    """
    if n <= 6:
        return 15
    else:
        return floor(n * log(n * log(n)))
