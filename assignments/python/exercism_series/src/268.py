def slices(string, m):
    n = len(string)
    if m < 1 or n < m: raise ValueError
    return [map(int, string[c - m:c]) for c in range(m, n+1)]
