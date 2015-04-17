def word_count(s):
    l = {}
    for w in s.split():
        if w in l:
            l[w] += 1
        else:
            l[w] = 1
    return l
