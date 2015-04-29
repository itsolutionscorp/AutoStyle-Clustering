def slices(s, n):
    if len(s) < n:
        raise ValueError('len(s) < n')
    if n <= 0:
        raise ValueError('n <= 0')
    d = [int(c) for c in list(s)]
    return [d[a:b] for a, b in enumerate(range(n, len(s)+1))]
