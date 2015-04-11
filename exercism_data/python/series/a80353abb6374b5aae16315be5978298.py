def slices(s, n):
    if n == 0:
        raise ValueError

    if n > len(s):
        raise ValueError

    return [map(int, s[i:i+n]) for i in range(len(s) - n + 1)]
