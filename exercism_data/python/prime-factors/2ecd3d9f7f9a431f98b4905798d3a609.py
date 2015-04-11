from itertools import count


def prime_factors(n):
    ps = []
    for p in count(2):
        while n % p == 0:
            ps.append(p)
            n /= p
        if n == 1:
            return ps
