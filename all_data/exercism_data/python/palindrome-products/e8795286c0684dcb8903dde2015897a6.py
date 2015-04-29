def smallest_palindrome(max_factor, min_factor=0):
    return _palindrome(max_factor, min_factor, min)


def largest_palindrome(max_factor, min_factor=0):
    return _palindrome(max_factor, min_factor, max)


def _palindrome(max_factor, min_factor, func):
    d = {}
    for x in range(min_factor, max_factor + 1):
        for y in range(x, max_factor + 1):
            p = x * y
            if str(p) == str(p)[::-1]:
                d[p] = {x, y}
    value = func(d)
    return value, d[value]
