from itertools import combinations_with_replacement
from collections import defaultdict

"""It seems like the only difference between these two functions
is going to be their order of iteration. I wonder if there's a way to
make that stuff portable between the two."""


def smallest_palindrome(max_factor=None, min_factor=None):
    return extremal_palindrome(min, max_factor, min_factor)


def largest_palindrome(max_factor=None, min_factor=None):
    return extremal_palindrome(max, max_factor, min_factor)


def extremal_palindrome(function, max_factor=None, min_factor=None):
    palindromes = find_palindromes(max_factor, min_factor)
    max_palindrome = function(palindromes.keys())
    return (max_palindrome, palindromes[max_palindrome])


def find_palindromes(max_factor=None, min_factor=None):
    palindromes = defaultdict(set)
    iter_range = set_range(max_factor, min_factor)
    for a, b in combinations_with_replacement(iter_range, 2):
        if is_palindrome(a * b):
            palindromes[a * b] = {a, b}

    return palindromes


def is_palindrome(number):
    string = str(number)
    return string == string[::-1]


def set_range(max_factor, min_factor):
    if min_factor:
        return range(min_factor, max_factor + 1)
    else:
        return range(1, max_factor + 1)
