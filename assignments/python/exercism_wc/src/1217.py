def word_count(word):
    d = {}
    for w in word.split():
        if w in d:
            d[w] += 1
        else:
            d[w] = 1
    return d
