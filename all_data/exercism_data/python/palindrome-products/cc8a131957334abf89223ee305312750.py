from itertools import combinations


def smallest_palindrome(max_factor, min_factor=0):
    value_factors = get_factors(max_factor, min_factor)
    value = min(value_factors.keys())
    return value, value_factors[value]


def largest_palindrome(max_factor, min_factor=0):
    value_factors = get_factors(max_factor, min_factor)
    value = max(value_factors.keys())
    return value, value_factors[value]


def get_factors(max_factor, min_factor):
    return {i*j: {i, j} for i, j in combinations(range(min_factor, max_factor + 1), 2)
            if is_palindrome(i * j)}


def is_palindrome(n):
    return str(n) == str(n)[::-1]
