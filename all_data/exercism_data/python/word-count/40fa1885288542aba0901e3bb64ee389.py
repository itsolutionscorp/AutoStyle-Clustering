def word_count(text):
    c = dict()
    for w in text.split():
        if not w in c:
            c[w] = 0
        c[w] += 1
    return c
