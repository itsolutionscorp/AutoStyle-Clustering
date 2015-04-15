"""
sieve.py
"""
import math


def is_prime(number):
    """
    Determines whether a number is prime or not.
    """
    for i in range(2, int(math.sqrt(number) + 1)):
        if (number % i) == 0:
            return False
    return True


def sieve(sieve_size):
    """
    Loops through a range of numbers starting at 2 and ending at one
    above the sieve_size. Returns primes appended to list.
    """
    primes = []
    for i in range(2, sieve_size + 1):
        if is_prime(i):
            primes.append(i)

    return primes
