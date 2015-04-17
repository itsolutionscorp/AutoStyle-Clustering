def word_count(s):
    d = {}
    for v in s.split():
        if v not in d:
            d[v] = 1
        else:
            d[v] += 1
    return d
