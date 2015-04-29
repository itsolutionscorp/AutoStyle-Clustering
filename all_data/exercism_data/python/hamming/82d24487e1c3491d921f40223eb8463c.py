def distance(f,t):
    d = i = 0
    while i < len(f):
        if f[i] != t[i]:
            d += 1
        i += 1
    return d
