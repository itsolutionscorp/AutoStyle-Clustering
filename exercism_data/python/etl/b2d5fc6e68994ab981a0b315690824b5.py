def transform(d):
    n = {}
    for k in d.keys():
        l = d[k]
        for i in l:
            n[i.lower()] = k
    return n
