def slices(s, n):
    if not (0 < n <= len(s)):
        raise ValueError

    return [map(int, s[i:i+n]) for i in range(len(s) - n + 1)]
