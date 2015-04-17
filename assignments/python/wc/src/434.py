def word_count(phrase):
    d = dict()
    words = phrase.split()
    for word in words:
        if word in d:
            d[word] += 1
        else:
            d[word] = 1
    return d
