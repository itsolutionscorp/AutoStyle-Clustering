from collections import OrderedDict

def sieve(number):
    """Write a program that uses the Sieve of Eratosthenes to find all the primes from 2 up to a given number."""

    all_numbers = OrderedDict()

    for i in range(2, number+1):
        all_numbers[i] = 'unmarked'

    for key in all_numbers:
        if all_numbers[key] == 'unmarked':
            mark(key, all_numbers)

    return [key
            for key in all_numbers
            if all_numbers[key] == 'unmarked']


def mark(prime, dict_of_numbers):
    """Utility functions that marks all numbers in a dictionary of numbers if they are divisible by a given prime"""
    
    for key in range(0, len(dict_of_numbers)):
        if (dict_of_numbers.keys()[key] % prime == 0) and dict_of_numbers.keys()[key] != prime:
            dict_of_numbers[dict_of_numbers.keys()[key]] = 'marked'

    return dict_of_numbers
