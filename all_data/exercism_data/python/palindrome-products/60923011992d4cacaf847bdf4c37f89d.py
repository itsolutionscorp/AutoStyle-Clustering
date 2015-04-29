"""
Notes regarding the implementation of smallest_palindrome and
largest_palindrome:

Both functions must take two keyword arguments:
    max_factor -- int
    min_factor -- int, default 0

Their return value must be a tuple (value, factors) where value is the
palindrome itself, and factors is an iterable containing both factors of the
palindrome in arbitrary order.
"""


def ispalindrome(n):
    return str(n) == str(n)[::-1]

def palindromes(max_factor, min_factor=0):
    """Rather slow if range is wide."""
    return {(a*b,(a,b))
            for a in range(min_factor, max_factor+1)
            for b in range(a,max_factor+1)
            if ispalindrome(a*b)}

def smallest_palindrome(max_factor, min_factor=0):
    return min(palindromes(max_factor,min_factor), key = lambda t:t[0])

def largest_palindrome(max_factor, min_factor=0):
    return max(palindromes(max_factor,min_factor), key = lambda t:t[0])
