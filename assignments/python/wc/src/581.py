def word_count(inp):
    res = {}
    for w in inp.split():
        if w in res:
            res[w] += 1
        else:
            res[w] = 1
    return res
