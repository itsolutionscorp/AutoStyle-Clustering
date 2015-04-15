def word_count(s):
    ret = dict()
    for w in s.split():
        ret[w] = ret.get(w, 0) + 1
    return ret
