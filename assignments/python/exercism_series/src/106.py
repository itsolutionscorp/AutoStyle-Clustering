def slices(s, n):
    d = [int(c) for c in list(s)]
    got = [d[a:b] for a, b in enumerate(range(n, len(s)+1))]
    if n <= 0 or n > len(s):
        raise ValueError(str(got))
    return got
