def is_palyndrome(n):
    s = str(n)
    return s == s[::-1]


def smallest_palindrome(min_factor = 0, max_factor = None):
    for v1 in xrange(min_factor, max_factor):
        for v2 in xrange(v1, max_factor):
            m = v1*v2
            if is_palyndrome(m):
                return (m, [v1, v2])


def largest_palindrome(min_factor = 0, max_factor = None):
    largest = 0
    factors = None
    for v1 in xrange(min_factor, max_factor+1):
        for v2 in xrange(v1, max_factor+1):
            m = v1*v2
            if is_palyndrome(m):
                if m > largest:
                    largest = m
                    factors = (v1, v2)
    return (largest, factors)
