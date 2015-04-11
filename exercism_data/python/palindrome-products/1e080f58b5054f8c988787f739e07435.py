def is_palindrome(n):
    return list(str(n)) == list(reversed(str(n)))


def smallest_palindrome(max_factor, min_factor=0):
    current = 1e100
    factors = []

    for i in range(min_factor, max_factor + 1):
        for j in range(min_factor, max_factor + 1):
            p = i * j
            if is_palindrome(p):
                if p < current:
                    current = p
                    factors = (i, j)

    return current, factors


def largest_palindrome(max_factor, min_factor=0):
    current = 0
    factors = []

    for i in range(min_factor, max_factor + 1):
        for j in range(min_factor, max_factor + 1):
            p = i * j
            if is_palindrome(p):
                if p > current:
                    current = p
                    factors = (i, j)

    return current, factors
