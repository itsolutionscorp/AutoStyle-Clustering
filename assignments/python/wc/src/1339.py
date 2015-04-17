def word_count(s):
    wc = {}
    for w in s.split():
        if w in wc:
            wc[w] += 1
        else:
            wc[w] = 1
    return wc
