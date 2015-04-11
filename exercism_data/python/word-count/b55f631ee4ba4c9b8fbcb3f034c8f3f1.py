def word_count(inp):
    res = {}
    words = inp.split()
    for w in words:
        if w in res:
            res[w] += 1
        else:
            res[w] = 1
    return res
