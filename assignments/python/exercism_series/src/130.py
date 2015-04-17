def slices(s, l):
    if l > len(s) or l <= 0:
        raise ValueError
    return [map(int, s[x:x+l]) for x in range(len(s)-l+1)]
