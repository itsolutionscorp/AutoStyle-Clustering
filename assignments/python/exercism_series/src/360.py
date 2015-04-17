def slices(s, n):
    if n == 0 or n > len(s):
        raise ValueError
    return [map(int,s[i:i+n]) for i in range(len(s)) if i+n <= len(s)]
