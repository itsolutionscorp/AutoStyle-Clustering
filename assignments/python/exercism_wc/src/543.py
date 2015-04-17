def word_count(phrase):
    words = phrase.split()
    d = {w : 0 for w in set(words)}
    for w in words:
        d[w] += 1
    return d
