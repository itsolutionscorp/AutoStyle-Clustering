
from itertools import combinations_with_replacement as combinations_wr

def palindromes(max_factor, min_factor=0):
    return ((a*b, (a, b))
            for a,b in combinations_wr(range(min_factor, max_factor+1), r=2)
            if str(a*b) == str(a*b)[::-1])

def smallest_palindrome(max_factor, min_factor=0):
    return min(palindromes(max_factor, min_factor))

def largest_palindrome(max_factor, min_factor=0):
    return max(palindromes(max_factor, min_factor))
