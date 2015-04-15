from operator import mul


def largest_product(s, n):
    return max(reduce(mul, seq, 1) for seq in slices(s, n))


def slices(s, n):
    N = len(s)
    if len(s) < n:
        raise ValueError("Requested substring longer than parent.")
    return [map(int, s[i:i + n]) for i in range(N - n + 1)]
