from math import ceil


def smallest_palindrome(max_factor, min_factor=0):
    return min(_palindromes(max_factor, min_factor))


def largest_palindrome(max_factor, min_factor=0):
    return max(_palindromes(max_factor, min_factor))


def _palindromes(max_factor, min_factor):
    for a in range(min_factor, max_factor + 1):
        for b in range(min_factor, max_factor + 1):
            if _is_palindrome(a * b):
                yield (a * b, (a, b))


def _is_palindrome(n):
    s = str(n)
    l = ceil(len(s) / 2)
    return s[:l] == s[-1:-l - 1:-1]
