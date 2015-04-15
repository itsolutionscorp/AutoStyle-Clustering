__author__ = 'emiller42'

import itertools


def __is_prime(primes, number):
    for prime in itertools.takewhile(lambda x: x <= number**0.5, primes):
        if number % prime == 0:
            return False
    return True


def nth_prime(prime_ord):
    if prime_ord == 1:
        return 2

    primes = [2]
    count = 1
    curr_candidate = 1

    while count < prime_ord:
        curr_candidate += 2
        if __is_prime(primes, curr_candidate):
            primes.append(curr_candidate)
            count += 1

    return curr_candidate
