def word_count(s):
    d={}
    for w in s.split():
        if w in d:
            d[w] += 1
        else:
            d[w] = 1
    return d
