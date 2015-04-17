def word_count(w):
    w = w.split()
    ret = {}
    for x in w:
        ret[x] = w.count(x)
    return ret
