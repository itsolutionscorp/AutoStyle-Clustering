def distance(s, t):
    return sum(1 for x in range(0, len(s)) if s[x] != t[x])
