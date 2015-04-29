def word_count(s):
    l = {}
    for w in s.split():
        l[w] = s.split().count(w)
    return l
