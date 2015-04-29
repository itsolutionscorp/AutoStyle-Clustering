from functools import lru_cache

def largest_palindrome(max_factor,  min_factor=0):
    return max(_generate_palindromes(max_factor, min_factor))


def smallest_palindrome(max_factor, min_factor=0):
    return min(_generate_palindromes(max_factor, min_factor))


@lru_cache(maxsize=None)
def _generate_palindromes(max_factor, min_factor=0):
    return [[n1 * n2, {n1, n2}]
            for n1 in range(min_factor, max_factor + 1)
            for n2 in range(min_factor, max_factor + 1)
            if str(n1 * n2) == str(n1 * n2)[::-1]]
