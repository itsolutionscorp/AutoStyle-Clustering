def word_count(s):
    l = s.split()
    d = dict()
    [d.update({i:0}) for i in set(l)]
    for i in set(l):
        for j in l:
            if j == i:
                d[i] += 1
    return d
