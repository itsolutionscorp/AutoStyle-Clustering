from fractions import gcd


def primitive_triplets(b):
    if b % 4:
        raise ValueError("b must be divisible by 4")
    ans = set()
    for m in range(2, 10000):
        for n in range(1, m):
            if (m - n) % 2 == 0 or gcd(m, n) != 1:
                continue
            triple = [
                m * m - n * n,
                2 * m * n,
                m * m + n * n
            ]
            triple.sort()
            if triple[0] > b:
                break
            if b in triple:
                ans.add(tuple(triple))
    return ans


def triplets_in_range(low, high):
    ans = set()
    for a in range(low, high):
        for b in range(a, high):
            for c in range(b, high + 1):
                triplet = (a, b, c)
                if is_triplet(triplet):
                    ans.add(triplet)
    return ans


def is_triplet(triplet):
    a, b, c = sorted(triplet)
    return a * a + b * b == c * c
