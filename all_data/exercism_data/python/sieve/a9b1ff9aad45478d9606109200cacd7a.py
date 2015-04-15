from math import sqrt, ceil


def sieve(n):
    if n < 2:
        return None

    a = range(0, n + 1)
    for i in range(2, int(ceil(sqrt(n + 1)))):
        if a[i]:
            for j in range(i ** 2, n + 1, i):
                a[j] = 0

    return [x for x in a if 2 <= x]
