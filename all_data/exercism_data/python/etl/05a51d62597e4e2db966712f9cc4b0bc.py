def transform(words):
    res = {}
    for k in words:
        for w in words[k]:
            res[w.lower()] = k
    return res
