def hamming(s1, s2):
    d = 0
    for (c1, c2) in zip(s1, s2):
        if c1 != c2:
            d += 1
    d += abs(len(s1)-len(s2))
    return d
