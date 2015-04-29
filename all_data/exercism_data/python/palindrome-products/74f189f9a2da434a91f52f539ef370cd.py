def palindromes(max_factor, min_factor=1):
    for i in range(min_factor, max_factor + 1):
        for j in range(min_factor, max_factor + 1):
            if ispalindrome(i * j):
                yield (i * j, (i, j))


def smallest_palindrome(max_factor, min_factor=1):
    return min(palindromes(max_factor, min_factor))


def largest_palindrome(max_factor, min_factor=1):
    return max(palindromes(max_factor, min_factor))


def ispalindrome(n):
    s = str(n)
    return s == s[::-1]
